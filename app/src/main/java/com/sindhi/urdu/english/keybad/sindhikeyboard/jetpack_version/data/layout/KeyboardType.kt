package com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.data.layout

import androidx.compose.runtime.mutableStateOf
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.domain.keyboard_classes.LayoutState

object KeyboardState {
    var currentLayout = mutableStateOf(LayoutState.Main)
}