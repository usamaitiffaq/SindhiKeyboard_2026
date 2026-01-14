package com.sindhi.urdu.english.keybad.sindhikeyboard.utils

import android.app.Activity
import android.app.KeyguardManager
import android.content.ComponentName
import android.content.Context
import android.os.Build
import android.os.UserManager
import android.provider.Settings
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.service.CustomImeService

fun Activity.isInputMethodEnabled(): Boolean {
    return ComponentName(this,CustomImeService::class.java) == ComponentName.unflattenFromString(
        Settings.Secure.getString(contentResolver, "default_input_method")
    )
}

fun isDirectBootEnabled(context: Context): Boolean {
    val userManager = context.getSystemService(Context.USER_SERVICE) as UserManager
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        userManager.isUserUnlocked
    } else {
        true
    }
}

fun isDeviceLockedOrInDirectBootMode(context: Context): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        context.isDeviceProtectedStorage || !isUserUnlocked(context)
    } else {
        false
    }
}

private fun isUserUnlocked(context: Context): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        val km = context.getSystemService(KeyguardManager::class.java)
        val userManager = context.getSystemService(UserManager::class.java)
        (km?.isDeviceLocked == false && userManager?.isUserUnlocked == true)
    } else {
        true
    }
}

fun Activity.checkIfKeyboardEnabled(): Boolean {
    val packageLocal = packageName
    val inputMethodManager = (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
    val list = inputMethodManager.enabledInputMethodList
    for (inputMethod in list) {
        val packageName = inputMethod.packageName
        if (packageName == packageLocal) {
            return true
        }
    }
    return false
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}