package com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.keyboardLayout

import android.content.Context
import android.os.Vibrator
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.data.layout.husneguftar
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.domain.keyboard_classes.Key
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.service.CustomImeService

@Composable
fun HusnEguftarLayout(
    keyboardHeight: Dp = 208.dp,
    specialKeys: Array<Array<Array<Key>>>? = husneguftar,
    imeService: CustomImeService? = null,
    context: Context? = null,
    vibrator: Vibrator? = null,
    bg: Brush? = null,
    color: Color? = null,
    onGuftarClick: (String) -> Unit
) {

    var isSelectionEnabled by remember { mutableStateOf(false) }

    val phrases = arrayListOf(
        "ماشاءاللہ",
        "جزاك اللهُ",
        "سبحان الله",
        "الحَمْدُ ِلله",
        "ان شاء اللہ",
        "أَسْتَغْفِرُ اللّٰه",
        "﷽",
        "الله أكبر",
        "ﷻ",
        "مُحَمَّد",
        "ﷺ",
        "بے شک",
        "اَلسَلامُ عَلَيْكُم وَرَحْمَةُ اَللهِ وَبَرَكاتُهُ",
        "وَعَلَيْكُم السَّلَام وَرَحْمَةُ اَللهِ وَبَرَكاتُهُ",
        "الله حافظ",
        "اللہ نگہبان",
        "في أمان الله",
        "آمین",
        "کہاں ھو؟",
        "گھرکب آناھے؟",
        "کیسےھو؟",
        "مجھےآپ سے پیار ھے",
        "رستے میں ھوں ابھی",
        "ھمیشہ خوشں رھو",
        "آپکا شکریہ",
        "اپنا خیال رکھنا",
        "میں میٹنگ میں ھوں",
        "بعد میں بات کرتے ھیں",
        "اللہ آپکو صحت دے",
        "میں پہنچ گیاھوں",
        "إِنَّا لِلّهِ وَإِنَّـا إِلَيْهِ رَاجِعونَ",
        "سوری یار!",
        "آپ خیریت سے ہیں",
        "اللہ کا شکر ہے",
        "موسم کیسا ہے",
        "آج بہت گرمی ہے",
        "آج بہت سردی ہے",
        "میں آ رہا ھوں",
        "میں مصروف ھوں",
        "آپ کب آ رہے ھو",
        "جمعہ مبارک",
        "صبح بخیر",
        "شب بخیر",
        "پاکستان زندہ باد",
        "جلدی آؤ",
        "نماز کا وقت ھو گیا ھے",
        "پھرملتے ھیں"
    )

    /*val urduFontFamily = FontFamily(Font(R.font.mehr_nastaliq))*/
    /*Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)) {*/

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally) {
            phrases.forEachIndexed { phraseIndex, phrase ->
                Text(
                    text = phrase,
                    fontSize = 16.sp,
                    color = Color.White,
                    /*fontFamily = urduFontFamily,*/
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(13.dp)
                        .fillMaxWidth()
                        .clickable {
                            onGuftarClick(phrase)
                        }
                )
                if (phraseIndex != phrases.size - 1) {
                    DrawLineWithCanvas()
                }
            }
        }
        /*specialKeys?.forEachIndexed { rowIndex, row ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 6.dp)
            ) {
                row.forEachIndexed { _, keys ->
                    Column(

                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        keys.forEachIndexed { _, key ->
                            CustomKey(
                                key = key,
                                onKeyPressed = {
                                    if (key.labelMain == LABEL_DELETE) {
                                        imeService?.doSomethingWith(key, false)
                                    } else {
                                        imeService?.doEditText(key = key, longPressed = false)
                                    }
                                    isSelectionEnabled = imeService?.isSelectionModeActive == true
                                },
                                onDragGestureToSelect = { dragAmount ->
                                    if (key.labelMain == LABEL_DELETE) {
                                        imeService?.selectTextGesture(dragAmount)
                                    }
                                    isSelectionEnabled = imeService?.isSelectionModeActive == true
                                },
                                deleteTextAfterDrag = {
                                    if (key.labelMain == LABEL_DELETE) {
                                        imeService?.deleteText()
                                    }
                                },
                                onLongKeyPressed = {
                                    if (key.labelMain == LABEL_DELETE) {
                                        imeService?.doSomethingWith(key, true)
                                    } else {
                                        imeService?.doEditText(key = key, longPressed = true)
                                    }
                                    isSelectionEnabled = imeService?.isSelectionModeActive == true
                                },
                                onLongKeyPressedEnd = {
                                    imeService?.longPressedStops(key)
                                },
                                modifier = Modifier
                                    .weight(key.weight)
                                    .padding(4.dp),
                                isSelectionEnabled = isSelectionEnabled,
                                imeService = imeService,
                                onLayoutSwitchClick = {},
                                onExtendedSymbolsSwitchClick = {},
                                onNumbersSwitchClick = {},
                                onSymbolsLayoutSwitchClick = {},
                                onSymbolsUrduLayoutSwitchClick = {},
                                onCapsClick = {},
                                onLanguageSwitchClick = {},
                                onCapsClickToLock = {},
                                context = context,
                                vibrator = vibrator
                            )
                        }
                    }
                }
            }
        }
    }*/
}

@Composable
fun DrawLineWithCanvas() {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
    ) {
        val startX = size.width * 0.1f
        val endX = size.width * 0.9f
        drawLine(
            color = Color.White,
            start = Offset(startX, 0f),
            end = Offset(endX, 0f),
            strokeWidth = 1.dp.toPx(),
            alpha = 0.6f,
            cap = StrokeCap.Round
        )
    }
}