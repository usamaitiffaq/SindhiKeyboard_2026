package com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.utilityClasses

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.remoteconfig.ConfigUpdate
import com.google.firebase.remoteconfig.ConfigUpdateListener
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.domain.constants.KEYPAD_BANNER_SHOW

class MyConfigUpdateListener(val applicationContext: Context) : ConfigUpdateListener {
    override fun onUpdate(configUpdate: ConfigUpdate) {
        Log.e("RemoteConfigChanges", "onUpdate: " + configUpdate.updatedKeys.contains(KEYPAD_BANNER_SHOW))
        if (configUpdate.updatedKeys.contains(KEYPAD_BANNER_SHOW)) {
            FirebaseRemoteConfig
                .getInstance()
                .fetchAndActivate()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val updatedValue = FirebaseRemoteConfig.getInstance().getString(KEYPAD_BANNER_SHOW)
                        Log.e("RemoteConfigChanges", "updatedValue:$updatedValue"+"ZeroSpace")

                        applicationContext.getSharedPreferences("RemoteConfig", AppCompatActivity.MODE_PRIVATE).edit()
                            .putString(KEYPAD_BANNER_SHOW, updatedValue).apply()
                    }
                }
        }
    }

    override fun onError(error: FirebaseRemoteConfigException) {
        Log.e("RemoteConfigChanges", "Error: " + error.message)
    }
}