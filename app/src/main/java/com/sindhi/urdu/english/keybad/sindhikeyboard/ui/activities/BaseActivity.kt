package com.sindhi.urdu.english.keybad.sindhikeyboard.ui.activities

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context?) {
        if (newBase == null) {
            super.attachBaseContext(null)
            return
        }

        // Copy the current configuration
        val config = Configuration(newBase.resources.configuration)

        // Force font scale to default (prevents text shrinking/stretching)
        config.fontScale = 1.0f

        // Create a new context with the updated configuration
        val context = newBase.createConfigurationContext(config)

        // Attach the wrapped context
        super.attachBaseContext(context)
    }
}
