package com.sindhi.urdu.english.keybad.sindhikeyboard.utils

import android.content.Context
import android.net.ConnectivityManager

object Networkutils {

    fun hasNetwork(context: Context): Boolean {
        var isConnected = false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected) isConnected = true
        return isConnected
    }
}