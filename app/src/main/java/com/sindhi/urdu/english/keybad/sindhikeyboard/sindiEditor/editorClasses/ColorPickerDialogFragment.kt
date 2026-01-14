package com.sindhi.urdu.english.keybad.sindhikeyboard.sindiEditor.editorClasses

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import com.flask.colorpicker.ColorPickerView
import com.flask.colorpicker.OnColorSelectedListener
import com.sindhi.urdu.english.keybad.R

class ColorPickerDialogFragment(
    context: Context,
    initialColor: Int,
    private val onColorSelected: (Int) -> Unit,
    private val onDismiss: () -> Unit)
    : Dialog(context) {

    private val recentColors = mutableListOf<Int>()
    private val preferences: SharedPreferences = context.getSharedPreferences("ColorPickerPrefs", Context.MODE_PRIVATE)
    private var colorPickerView: ColorPickerView
    private var selectedColorPreview: View
    private var recentColorsContainer: LinearLayout

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_color_picker, null)
        setContentView(view)
        setCancelable(false)
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        colorPickerView = view.findViewById(R.id.color_picker_view)
        selectedColorPreview = view.findViewById(R.id.selected_color_preview)
        recentColorsContainer = view.findViewById(R.id.recent_colors_container)
        selectedColorPreview.setBackgroundColor(initialColor)

        loadRecentColors()
        displayRecentColors()

        colorPickerView.addOnColorSelectedListener(object : OnColorSelectedListener {
            override fun onColorSelected(color: Int) {
                selectedColorPreview.setBackgroundColor(color)
            }
        })

        view.findViewById<Button>(R.id.btn_cancel).setOnClickListener {
            onDismiss.invoke()
            dismiss()
        }

        view.findViewById<Button>(R.id.btn_ok).setOnClickListener {
            val selectedColor = colorPickerView.selectedColor
            saveColorToRecent(selectedColor)
            onColorSelected(selectedColor)
            dismiss()
        }
    }

    private fun loadRecentColors() {
        recentColors.clear()
        val recentColorsSet = preferences.getStringSet("recentColors", emptySet()) ?: emptySet()
        for (colorHex in recentColorsSet) {
            recentColors.add(Color.parseColor(colorHex))
        }
    }

    private fun saveColorToRecent(color: Int) {
        String.format("#%06X", 0xFFFFFF and color)
        if (!recentColors.contains(color)) {
            if (recentColors.size >= 6) {
                recentColors.removeAt(0)
            }
            recentColors.add(color)
            preferences.edit().putStringSet("recentColors", recentColors.map { String.format("#%06X", 0xFFFFFF and it) }.toSet()).apply()
        }
    }

    private fun displayRecentColors() {
        recentColorsContainer.removeAllViews()
        for (color in recentColors) {
            val colorView = ImageView(context)
            colorView.setBackgroundColor(color)
            colorView.layoutParams = LinearLayout.LayoutParams(100, 100).apply {
                setMargins(8, 0, 8, 0)
            }
            colorView.setOnClickListener {
                selectedColorPreview.setBackgroundColor(color)
                colorPickerView.setInitialColor(color,true)
            }
            recentColorsContainer.addView(colorView)
        }
    }
}