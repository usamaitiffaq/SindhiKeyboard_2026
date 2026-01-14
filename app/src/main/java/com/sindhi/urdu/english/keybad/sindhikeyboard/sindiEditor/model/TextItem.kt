package com.sindhi.urdu.english.keybad.sindhikeyboard.sindiEditor.model

import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Typeface

data class TextItem(
    var text: String,
    var x: Float,
    var y: Float,
    var textColor: Int,
    var textSize: Float,
    var rotation: Float,
    var typeface: Typeface? = null,
    var alignment: Paint.Align = Paint.Align.CENTER,
    var fontName: String = "",
    var rect: RectF = RectF(),
    var isSelected: Boolean = false
)
