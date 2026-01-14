package com.sindhi.urdu.english.keybad.sindhikeyboard.sindiEditor.stickerview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.NonNull;

import com.sindhi.urdu.english.keybad.R;
import com.sindhi.urdu.english.keybad.sindhikeyboard.sindiEditor.editorClasses.TextEditorView;

import java.util.ArrayList;
import java.util.List;

public abstract class StickerView extends FrameLayout {
    private TextEditorView textEditorView;
    private boolean enableBorder = true;
    public static final List<StickerView> allStickers = new ArrayList<>();
    protected abstract void updateXY();
    protected abstract void updateSize();
    public static final String TAG = StickerView.class.getSimpleName();
    private BorderView iv_border;
    private ImageView iv_scale;
    private ImageView iv_delete;
    private ImageView iv_flip;
    private ImageView iv_rotate;

    private float scale_orgX = -1, scale_orgY = -1;
    private double scale_orgWidth = -1, scale_orgHeight = -1;
    private float rotate_orgX = -1, rotate_orgY = -1, rotate_newX = -1, rotate_newY = -1;
    private float move_orgX = -1, move_orgY = -1;

    private double centerX, centerY;

    private final static int BUTTON_SIZE_DP = 30;
    private final static int SELF_SIZE_DP = 100;
    private String owner_id;

    public void setOwnerStickerViewId(String owner_id) {
        this.owner_id = owner_id;
    }

    public String getOwnerStickerViewId() {
        return this.owner_id;
    }

    public StickerView(Context context, TextEditorView textEditorView) {
        super(context);
        this.textEditorView = textEditorView;
        deselectAllOthers();
        if (textEditorView != null) {
            textEditorView.resetSelectedState();
        }
        init(context);
    }

    public StickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public StickerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void init(Context context) {
        this.iv_border = new BorderView(context);
        this.iv_scale = new ImageView(context);
        this.iv_delete = new ImageView(context);
        this.iv_flip = new ImageView(context);
        this.iv_rotate = new ImageView(context);

        this.iv_scale.setImageResource(R.drawable.ic_edit_resize);
        this.iv_delete.setImageResource(R.drawable.ic_edit_close);
        this.iv_flip.setImageResource(R.drawable.iv_flip);
        this.iv_rotate.setImageResource(R.drawable.ic_edit_rotate);

        this.setTag("DraggableViewGroup");
        this.iv_border.setTag("iv_border");
        this.iv_scale.setTag("iv_scale");
        this.iv_delete.setTag("iv_delete");
        this.iv_flip.setTag("iv_flip");
        this.iv_rotate.setTag("iv_rotate");

        int margin = convertDpToPixel(BUTTON_SIZE_DP, getContext()) / 2;
        int size = convertDpToPixel(SELF_SIZE_DP, getContext());

        LayoutParams this_params = new LayoutParams(size, size);
        this_params.gravity = Gravity.CENTER;

        LayoutParams iv_main_params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        iv_main_params.setMargins(margin, margin, margin, margin);

        LayoutParams iv_border_params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        iv_border_params.setMargins(margin, margin, margin, margin);

        LayoutParams iv_scale_params = new LayoutParams(convertDpToPixel(BUTTON_SIZE_DP, getContext()), convertDpToPixel(BUTTON_SIZE_DP, getContext()));
        iv_scale_params.gravity = Gravity.BOTTOM | Gravity.END;

        LayoutParams iv_delete_params = new LayoutParams(convertDpToPixel(BUTTON_SIZE_DP, getContext()), convertDpToPixel(BUTTON_SIZE_DP, getContext()));
        iv_delete_params.gravity = Gravity.TOP | Gravity.END;

        LayoutParams iv_flip_params = new LayoutParams(convertDpToPixel(BUTTON_SIZE_DP, getContext()), convertDpToPixel(BUTTON_SIZE_DP, getContext()));
        iv_flip_params.gravity = Gravity.TOP | Gravity.START;

        LayoutParams iv_rotate_params = new LayoutParams(convertDpToPixel(BUTTON_SIZE_DP, getContext()), convertDpToPixel(BUTTON_SIZE_DP, getContext()));
        iv_rotate_params.gravity = Gravity.BOTTOM | Gravity.START;

        this.setLayoutParams(this_params);
        this.addView(getMainView(), iv_main_params);
        this.addView(iv_border, iv_border_params);
        this.addView(iv_scale, iv_scale_params);
        this.addView(iv_delete, iv_delete_params);
        this.addView(iv_flip, iv_flip_params);
        this.addView(iv_rotate, iv_rotate_params);
        this.setOnTouchListener(mTouchListener);
        this.iv_scale.setOnTouchListener(mTouchListener);
        this.iv_delete.setOnClickListener(view -> {
            if (StickerView.this.getParent() != null) {
                ViewGroup myCanvas = ((ViewGroup) StickerView.this.getParent());
                myCanvas.removeView(StickerView.this);
                allStickers.remove(StickerView.this);
            }
        });
        this.iv_flip.setOnClickListener(view -> {
            Log.v(TAG, "flip the view");
            View mainView = getMainView();
            mainView.setRotationY(mainView.getRotationY() == -180f ? 0f : -180f);
            mainView.invalidate();
            requestLayout();
        });

        this.iv_rotate.setOnTouchListener((view, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    rotate_orgX = event.getRawX();
                    rotate_orgY = event.getRawY();
                    centerX = StickerView.this.getX() + (double) StickerView.this.getWidth() / 2;
                    centerY = StickerView.this.getY() + (double) StickerView.this.getHeight() / 2;
                    break;

                case MotionEvent.ACTION_MOVE:
                    rotate_newX = event.getRawX();
                    rotate_newY = event.getRawY();
                    double initialAngle = Math.toDegrees(Math.atan2(rotate_orgY - centerY, rotate_orgX - centerX));
                    double currentAngle = Math.toDegrees(Math.atan2(rotate_newY - centerY, rotate_newX - centerX));
                    double angleDiff = currentAngle - initialAngle;
                    if (angleDiff > 180) angleDiff -= 360;
                    if (angleDiff < -180) angleDiff += 360;
                    StickerView.this.setRotation(StickerView.this.getRotation() + (float) angleDiff);
                    rotate_orgX = rotate_newX;
                    rotate_orgY = rotate_newY;
                    break;

                case MotionEvent.ACTION_UP:
                    break;
            }
            return true;
        });
        allStickers.add(this);
    }

    public void deselectAllOthers() {
        for (StickerView sticker : allStickers) {
            if (sticker != StickerView.this) {
                sticker.select(false);
            }
        }
    }

    protected abstract View getMainView();

    private final OnTouchListener mTouchListener = new OnTouchListener() {
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View view, MotionEvent event) {
                if ("DraggableViewGroup".equals(view.getTag())) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            deselectAllOthers();
                            select(true);
                            if (textEditorView != null) {
                                textEditorView.resetSelectedState();
                            }
                            Log.i(TAG, "onTouch: "+StickerView.this.getOwnerStickerViewId());
                            move_orgX = event.getRawX();
                            move_orgY = event.getRawY();
                            break;
                        case MotionEvent.ACTION_MOVE:
                            float offsetX = event.getRawX() - move_orgX;
                            float offsetY = event.getRawY() - move_orgY;
                            StickerView.this.setX(StickerView.this.getX() + offsetX);
                            StickerView.this.setY(StickerView.this.getY() + offsetY);
                            move_orgX = event.getRawX();
                            move_orgY = event.getRawY();
                            break;
                        case MotionEvent.ACTION_UP:
                            updateXY();
                            updateSize();
                            break;
                    }
                } else if ("iv_scale".equals(view.getTag())) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            scale_orgX = event.getRawX();
                            scale_orgY = event.getRawY();
                            scale_orgWidth = StickerView.this.getLayoutParams().width;
                            scale_orgHeight = StickerView.this.getLayoutParams().height;

                            centerX = StickerView.this.getX() + (double) StickerView.this.getWidth() / 2;
                            centerY = StickerView.this.getY() + (double) StickerView.this.getHeight() / 2;
                            break;

                        case MotionEvent.ACTION_MOVE:

                            double distanceStart = getLength(centerX, centerY, scale_orgX, scale_orgY);
                            double distanceCurrent = getLength(centerX, centerY, event.getRawX(), event.getRawY());

                            float scaleFactor = (float) (distanceCurrent / distanceStart);

                            int newWidth = Math.max(200, (int) (scale_orgWidth * scaleFactor));
                            int newHeight = Math.max(200, (int) (scale_orgHeight * scaleFactor));

                            LayoutParams params = (LayoutParams) StickerView.this.getLayoutParams();
                            if (params != null) {
                                params.width = newWidth;
                                params.height = newHeight;
                                StickerView.this.setLayoutParams(params);
                            }
                            break;

                        case MotionEvent.ACTION_UP:
                            updateSize();
                            break;
                    }
                }
            return true;
        }
    };

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
    }

    private double getLength(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(y2 - y1, 2) + Math.pow(x2 - x1, 2));
    }

    public void setControlItemsHidden(boolean isHidden) {
        if (isHidden) {
            iv_border.setVisibility(View.INVISIBLE);
            iv_scale.setVisibility(View.INVISIBLE);
            iv_delete.setVisibility(View.INVISIBLE);
            iv_flip.setVisibility(View.INVISIBLE);
            iv_rotate.setVisibility(View.INVISIBLE);
        } else {
            iv_border.setVisibility(View.VISIBLE);
            iv_scale.setVisibility(View.VISIBLE);
            iv_delete.setVisibility(View.VISIBLE);
            iv_flip.setVisibility(View.VISIBLE);
            iv_rotate.setVisibility(View.VISIBLE);
        }
    }

    public void select(boolean b) {
        enableBorder = b;
        setControlItemsHidden(!b);
        invalidate();
    }

    public void setVisible(boolean b) {
        if (b) {
            setVisibility(VISIBLE);
        } else {
            setVisibility(INVISIBLE);
        }
    }

    private static int convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return (int) px;
    }

    private class BorderView extends View {

        private final Rect border = new Rect();

        private final Paint borderPaint = new Paint();

        public BorderView(Context context) {
            super(context);
        }

        public BorderView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public BorderView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
        }
        @Override
        protected void onDraw(@NonNull Canvas canvas) {
            super.onDraw(canvas);
            if (enableBorder) {
                LayoutParams params = (LayoutParams) this.getLayoutParams();
                Log.v(TAG, "params.leftMargin: " + params.leftMargin);
                border.left = this.getLeft() - params.leftMargin;
                border.top = this.getTop() - params.topMargin;
                border.right = this.getRight() - params.rightMargin;
                border.bottom = this.getBottom() - params.bottomMargin;
                borderPaint.setStrokeWidth(6);
                borderPaint.setColor(StickerView.this.getContext().getColor(R.color.txt_border));
                borderPaint.setStyle(Paint.Style.STROKE);
                canvas.drawRect(border, borderPaint);

                updateXY();
                updateSize();
            }
        }

    }
}