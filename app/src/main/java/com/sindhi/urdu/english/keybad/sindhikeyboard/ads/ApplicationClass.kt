package com.sindhi.urdu.english.keybad.sindhikeyboard.ads

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.os.Looper
import com.sindhi.urdu.english.keybad.R
import android.util.Log
import android.webkit.WebView
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.applovin.sdk.AppLovinPrivacySettings
import com.applovin.sdk.AppLovinSdk
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.remoteconfig.ConfigUpdate
import com.google.firebase.remoteconfig.ConfigUpdateListener
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.mbridge.msdk.MBridgeSDK
import com.mbridge.msdk.out.MBridgeSDKFactory
import com.mbridge.msdk.out.SDKInitStatusListener
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.dbClasses.DataBaseCopyOperationsKt
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.dbClasses.SuggestionItems
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.domain.keyboard_classes.CustomTheme
import com.sindhi.urdu.english.keybad.sindhikeyboard.utils.DictionaryObject.suggestionList
import com.sindhi.urdu.english.keybad.sindhikeyboard.utils.RemoteConfigConst.KEYPAD_AD_MEDIATION
import com.sindhi.urdu.english.keybad.sindhikeyboard.utils.RemoteConfigConst.KEYPAD_AD_VISIBILITY
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception


class ApplicationClass : Application() {
    companion object {
        lateinit var firebaseAnalyticsEventsLog: FirebaseAnalytics
        lateinit var resumeAdInstance: ResumeAd
        lateinit var applicationClass: ApplicationClass
        var selectedTheme: CustomTheme? = null
        const val logTagAdmob = "NativeAdKeyboardAdmob"
        var imageUriUrduEditorBackgrounds: Uri? = null
        const val logTagMintegral = "NativeAdKeyboardMintegral"
        const val ACTION_CONFIG_CHANGED = "com.sindhi.urdu.english.keybad.CONFIG_CHANGED"
        private const val TAG = "ApplicationClass"
    }

    override fun onCreate() {
        super.onCreate()
        applicationClass = this

        try {
            WebView(this)
            Log.d(TAG, "Chromium engine pre-warmed successfully")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to pre-warm WebView", e)
        }

        // 1. Keep only the absolute fastest, required synchronous initializations here
        FirebaseApp.initializeApp(this)

        // Note: Make sure ResumeAd doesn't do disk I/O! If it does, move it down.
        resumeAdInstance = ResumeAd(this)


        // 2. Evacuate all heavy lifting to the IO Dispatcher
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Move the massive database copy off the main thread
                DataBaseCopyOperationsKt.initializeAndCopy(this@ApplicationClass)

                // Firebase singletons can cause strict mode disk reads, initialize them here
                firebaseAnalyticsEventsLog = FirebaseAnalytics.getInstance(this@ApplicationClass)

                // Fetch remote config in the background
                setupRemoteConfig()

                // Load suggestions (this was already well-written as a suspend function)
                loadSuggestions()

            } catch (e: Exception) {
                Log.e(TAG, "Error during background initialization", e)
            }
        }

        // 3. Keep SDK initializations in the background
        CoroutineScope(Dispatchers.Default).launch {
            initMintegralSdk()
        }
    }

    private fun setupRemoteConfig() {
        val remoteConfig = FirebaseRemoteConfig.getInstance()
        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(3600)
            .build()
        remoteConfig.setConfigSettingsAsync(configSettings)

        remoteConfig.fetchAndActivate()

        remoteConfig.addOnConfigUpdateListener(object : ConfigUpdateListener {
            override fun onUpdate(configUpdate: ConfigUpdate) {
                if (configUpdate.updatedKeys.contains(KEYPAD_AD_MEDIATION) ||
                    configUpdate.updatedKeys.contains(KEYPAD_AD_VISIBILITY)
                ) {
                    remoteConfig.activate().addOnCompleteListener {
                        if (it.isSuccessful) {
                            Log.d(TAG, "Remote config updated and activated.")
                            notifyConfigChanged()
                        }
                    }
                }
            }

            override fun onError(error: FirebaseRemoteConfigException) {
                Log.w(TAG, "Config update failed with code: ${error.code}", error)
            }
        })
    }

    private fun notifyConfigChanged() {
        LocalBroadcastManager.getInstance(this).sendBroadcast(Intent(ACTION_CONFIG_CHANGED))
    }

    private fun initMintegralSdk() {
        try {
            val sdk: MBridgeSDK = MBridgeSDKFactory.getMBridgeSDK()
            val map = sdk.getMBConfigurationMap(
                resources.getString(R.string.MINTEGRAL_APP_ID),
                resources.getString(R.string.MINTEGRAL_APP_KEY)
            )
            sdk.init(map, this, object : SDKInitStatusListener {
                override fun onInitSuccess() {
                    Log.d(logTagMintegral, "Mintegral SDK init success")
                }

                override fun onInitFail(errorMsg: String) {
                    Log.e(logTagMintegral, "Mintegral SDK init failed: $errorMsg")
                }
            })
        } catch (e: Exception) {
            Log.e(logTagMintegral, "Exception during Mintegral init", e)
        }
    }

    private suspend fun loadSuggestions() {
        try {
            withContext(Dispatchers.IO) {
                if (suggestionList.isEmpty()) {
                    suggestionList =
                        DataBaseCopyOperationsKt.getAllItems() as MutableList<SuggestionItems>
                    Log.d(
                        "DataBaseCopyOperations",
                        "Loaded suggestions. Size: ${suggestionList.size}"
                    )
                }
            }
        } catch (e: Exception) {
            Log.e("DataBaseCopyOperations", "Error loading suggestions", e)
        }
    }
}