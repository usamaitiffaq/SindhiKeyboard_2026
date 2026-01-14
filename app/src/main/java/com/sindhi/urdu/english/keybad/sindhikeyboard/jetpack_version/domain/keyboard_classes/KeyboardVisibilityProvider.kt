package com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.domain.keyboard_classes

import android.view.ViewTreeObserver
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalView

@Composable
fun KeyboardVisibilityProvider(onKeyboardVisibilityChanged: (Boolean) -> Unit) {
    var isKeyboardVisible by remember { mutableStateOf(false) }

    val rootView = LocalView.current

    DisposableEffect(rootView) {
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            val rect = android.graphics.Rect()
            rootView.getWindowVisibleDisplayFrame(rect)
            val screenHeight = rootView.height
            val keypadHeight = screenHeight - rect.bottom
            isKeyboardVisible = keypadHeight > screenHeight * 0.15
            onKeyboardVisibilityChanged(isKeyboardVisible)
        }

        rootView.viewTreeObserver.addOnGlobalLayoutListener(listener)

        onDispose {
            rootView.viewTreeObserver.removeOnGlobalLayoutListener(listener)
        }
    }
}