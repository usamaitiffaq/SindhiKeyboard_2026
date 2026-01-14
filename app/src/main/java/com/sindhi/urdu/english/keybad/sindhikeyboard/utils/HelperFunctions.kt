package com.sindhi.urdu.english.keybad.sindhikeyboard.utils

import android.os.SystemClock
import android.util.Log
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDirections

const val PURCHASE = "is_purchased"

fun NavController.safeNavigate(direction: NavDirections) {
    currentDestination?.getAction(direction.actionId)?.run {
        navigate(direction)
    }
}

fun View.blockingClickListener(debounceTime: Long = 1500L, action: () -> Unit) {
    this.setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0
        override fun onClick(v: View) {
            val timeNow = SystemClock.elapsedRealtime()
            val elapsedTimeSinceLastClick = timeNow - lastClickTime
            Log.e("clickTag", """DebounceTime: 
                $debounceTime Time Elapsed: 
                $elapsedTimeSinceLastClick Is within debounce time: 
                ${elapsedTimeSinceLastClick < debounceTime}""".trimIndent())

            if (elapsedTimeSinceLastClick < debounceTime) {
                Log.e("clickTag", "Double click shielded")
                return
            }
            else {
                Log.e("clickTag", "Click happened")
                action()
            }
            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}