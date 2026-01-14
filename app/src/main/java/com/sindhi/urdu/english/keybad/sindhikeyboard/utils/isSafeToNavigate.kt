package com.sindhi.urdu.english.keybad.sindhikeyboard.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle

fun Fragment.isSafeToNavigate(): Boolean {
    return isAdded &&
            !isDetached &&
            view != null &&
            viewLifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)
}