package com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.domain.keyboard_classes

import androidx.compose.ui.graphics.Color

data class CustomTheme(
    val name: String,
    val category: String? = null,
    val isAutoTheme: Boolean = false,
    val backgroundColor: Color,
    val backgroundColor2: Color? = null
)
