package com.sindhi.urdu.english.keybad.sindhikeyboard.ads

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.sindhi.urdu.english.keybad.BuildConfig
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.appopen.AppOpenAd
import com.manual.mediation.library.sotadlib.utils.AdLoadingDialog
import com.sindhi.urdu.english.keybad.R
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.preferences.Preferences.OPEN_AD_ENABLE_KEYBOARD
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.preferences.Preferences.OPEN_AD_INSIDE_APP
import com.sindhi.urdu.english.keybad.sindhikeyboard.utils.RemoteConfigConst.RESUME_OVER_ALL

class ResumeAd(globalClass: ApplicationClass? = null) : Application.ActivityLifecycleCallbacks,
    LifecycleObserver {

    private var adVisible = false
    var appOpenAd: AppOpenAd? = null
    private var currentActivity: Activity? = null
    var isShowingDialog = true
    var isShowingAd = false
    private var myApplicationClass: ApplicationClass? = globalClass
    var fullScreenContentCallback: FullScreenContentCallback? = null

    init {
        myApplicationClass.let {
            this.myApplicationClass?.registerActivityLifecycleCallbacks(this)
            ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        }
        currentActivity.let {
            if (currentActivity?.localClassName != null || currentActivity?.localClassName.equals("")) {
                fetchAd()
            }
        }
    }

    fun fetchAd() {
        if (isAdAvailable()) {
            return
        }

        if (myApplicationClass != null) {
            if (!NetworkCheck.isNetworkAvailable(myApplicationClass)) {
                return
            }
        } else {
            return
        }

        val loadCallback: AppOpenAd.AppOpenAdLoadCallback =
            object : AppOpenAd.AppOpenAdLoadCallback() {
                override fun onAdLoaded(ad: AppOpenAd) {
                    appOpenAd = ad
                    myApplicationClass.let {
                        if (myApplicationClass?.getString(R.string.ShowPopups).equals("true")) {
                            Toast.makeText(
                                myApplicationClass,
                                "OpenAd :: AdMob :: Loaded",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }

                override fun onAdFailedToLoad(p0: LoadAdError) {
                    super.onAdFailedToLoad(p0)
                    myApplicationClass.let {
                        if (myApplicationClass?.getString(R.string.ShowPopups).equals("true")) {
                            Toast.makeText(
                                myApplicationClass,
                                "OpenAd :: AdMob :: Failed to Load",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        val request: AdRequest = getAdRequest()
        val pref = (currentActivity?.getSharedPreferences("RemoteConfig", Context.MODE_PRIVATE))
        val apOpenAdID = if (!BuildConfig.DEBUG) {
            pref?.getString(RESUME_OVER_ALL, "ca-app-pub-3747520410546258/7925749832")
        } else {
            myApplicationClass?.getString(R.string.admob_app_open)
        }

        myApplicationClass?.applicationContext?.apply {
            AppOpenAd.load(
                this,
                apOpenAdID!!,
                request,
                loadCallback
            )
            if (myApplicationClass?.getString(R.string.ShowPopups) == "true") {
                Toast.makeText(myApplicationClass, "OpenAd :: AdMob :: Request", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun showAdIfAvailable(onAdNotAvailableOrShown: (() -> Unit)? = null) {
        if (!isShowingAd && isAdAvailable()) {
            fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    isShowingDialog = false
                    dismissWaitDialog()
                    onAdNotAvailableOrShown.let {
                        onAdNotAvailableOrShown?.invoke()
                    }
                    appOpenAd = null
                    isShowingAd = false
                    adVisible = false
                    fetchAd()
                }

                override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                    isShowingDialog = false
                    dismissWaitDialog()
                    onAdNotAvailableOrShown.let {
                        onAdNotAvailableOrShown?.invoke()
                    }
                    if (myApplicationClass?.getString(R.string.ShowPopups) == "true") {
                        Toast.makeText(
                            myApplicationClass,
                            "OpenAd :: AdMob :: onAdFailedToShowFullScreenContent",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onAdShowedFullScreenContent() {
                    isShowingAd = true
                    isShowingDialog = false
                    dismissWaitDialog()
                }
            }
            adVisible = true
            appOpenAd?.fullScreenContentCallback = fullScreenContentCallback
            isShowingDialog = true
            showWaitDialog()
            Handler(Looper.getMainLooper()).postDelayed({
                appOpenAd!!.show(currentActivity!!)
                dismissWaitDialog()
            }, 1500)
        } else {
            isShowingDialog = false
            dismissWaitDialog()
            onAdNotAvailableOrShown.let {
                onAdNotAvailableOrShown?.invoke()
            }
            if (currentActivity?.localClassName != null || currentActivity?.localClassName.equals("")) {
                fetchAd()
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onAppForegrounded() {
        Log.e("ResumeAd", "" + currentActivity?.localClassName)
        if (currentActivity?.localClassName != null || currentActivity?.localClassName.equals("")) {
            if (!InterstitialClassAdMob.isInterstitialAdVisible) {
                if (currentActivity?.localClassName.equals("sindhikeyboard.ui.activities.KeyboardSelectionActivity")) {
                    if (currentActivity?.getSharedPreferences("RemoteConfig", Context.MODE_PRIVATE)
                            ?.getString(OPEN_AD_ENABLE_KEYBOARD, "ON").equals("ON", true)
                    ) {
                        showAdIfAvailable()
                        Log.e("ResumeAd", "" + currentActivity?.localClassName)
                    }
                } else if (currentActivity?.localClassName.equals("sindhikeyboard.ui.activities.NavigationActivity")) {
                    if (currentActivity?.getSharedPreferences("RemoteConfig", Context.MODE_PRIVATE)
                            ?.getString(OPEN_AD_INSIDE_APP, "ON").equals("ON", true)
                    ) {
                        showAdIfAvailable()
                        Log.e("ResumeAd", "" + currentActivity?.localClassName)
                    }
                }
            }
        }
    }

    private fun getAdRequest(): AdRequest {
        return AdRequest.Builder().build()
    }

    private fun isAdAvailable(): Boolean {
        return appOpenAd != null
    }

    override fun onActivityCreated(p0: Activity, p1: Bundle?) {
    }

    override fun onActivityStarted(p0: Activity) {
        currentActivity = p0
    }

    override fun onActivityResumed(p0: Activity) {
        currentActivity = p0
    }

    override fun onActivityPaused(p0: Activity) {
        dismissWaitDialog()
    }

    override fun onActivityStopped(p0: Activity) {
        dismissWaitDialog()
    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
    }

    override fun onActivityDestroyed(p0: Activity) {
        dismissWaitDialog()
    }

    private fun showWaitDialog() {
        if (isShowingDialog) {
            currentActivity?.let {
                if (!(currentActivity as Activity).isFinishing) {
                    AdLoadingDialog.dismissDialog(currentActivity!!)
                }
            }
        }
        if (isShowingDialog) {
            currentActivity?.let {
                if (!(currentActivity as Activity).isFinishing) {
                    val view = (currentActivity as Activity).layoutInflater.inflate(
                        com.manual.mediation.library.sotadlib.R.layout.dialog_adloading,
                        null,
                        false
                    )
                    isShowingDialog = true
                    AdLoadingDialog.setContentView(
                        currentActivity!!,
                        view = view,
                        isCancelable = false
                    ).showDialogInterstitial()
                }
            }
        }
    }

    private fun dismissWaitDialog() {
        currentActivity?.let {
            if (!(currentActivity as Activity).isFinishing) {
                AdLoadingDialog.dismissDialog(currentActivity!!)
            }
        }
    }
}
