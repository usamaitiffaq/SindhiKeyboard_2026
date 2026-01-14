package com.sindhi.urdu.english.keybad.sindhikeyboard.ui.activities

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.airbnb.lottie.LottieAnimationView
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.manual.mediation.library.sotadlib.adMobAdClasses.AdMobBannerAdSplash
import com.manual.mediation.library.sotadlib.callingClasses.LanguageScreensConfiguration
import com.manual.mediation.library.sotadlib.callingClasses.SOTAdsConfigurations
import com.manual.mediation.library.sotadlib.callingClasses.SOTAdsManager
import com.manual.mediation.library.sotadlib.callingClasses.WalkThroughScreensConfiguration
import com.manual.mediation.library.sotadlib.callingClasses.WelcomeScreensConfiguration
import com.manual.mediation.library.sotadlib.data.Language
import com.manual.mediation.library.sotadlib.data.WalkThroughItem
import com.manual.mediation.library.sotadlib.utils.MyLocaleHelper
import com.manual.mediation.library.sotadlib.utils.NetworkCheck
import com.manual.mediation.library.sotadlib.utils.PrefHelper
import com.manual.mediation.library.sotadlib.utils.hideSystemUIUpdated
import com.manual.mediation.library.sotadlib.utils.setStatusBarColor
import com.manual.mediation.library.sotadlib.utilsGoogleAdsConsent.ConsentConfigurations
import com.mbridge.msdk.MBridgeConstans
import com.mbridge.msdk.MBridgeSDK
import com.mbridge.msdk.out.MBridgeSDKFactory
import com.sindhi.urdu.english.keybad.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.Locale
import androidx.core.content.edit
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.firebase.abt.FirebaseABTesting.OriginService.REMOTE_CONFIG
import com.sindhi.urdu.english.keybad.BuildConfig
import com.sindhi.urdu.english.keybad.databinding.ActivitySplashBinding
import com.sindhi.urdu.english.keybad.sindhikeyboard.ads.ApplicationClass.Companion.applicationClass
import com.sindhi.urdu.english.keybad.sindhikeyboard.ads.ResumeAd
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.domain.constants.KEYPAD_BANNER_SHOW
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.preferences.Preferences.ADS_NATIVE_CONVERSATION
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.preferences.Preferences.ADS_NATIVE_EXIT
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.preferences.Preferences.ADS_NATIVE_HISTORY
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.preferences.Preferences.ADS_NATIVE_HOME
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.preferences.Preferences.ADS_NATIVE_POETRY
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.preferences.Preferences.ADS_NATIVE_POETRY_INSIDE
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.preferences.Preferences.ADS_NATIVE_SELECT_KEYBOARD
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.preferences.Preferences.ADS_NATIVE_SELECT_LANGUAGE_SPEECH_TO_TEXT
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.preferences.Preferences.ADS_NATIVE_SETTINGS
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.preferences.Preferences.ADS_NATIVE_SPEECHTOTEXT
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.preferences.Preferences.ADS_NATIVE_THEMES
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.preferences.Preferences.ADS_NATIVE_THEMES_APPLIED_TEST
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.preferences.Preferences.ADS_NATIVE_THEMES_APPLY
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.preferences.Preferences.ADS_NATIVE_TRANSLATION_HOME
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.preferences.Preferences.ADS_NATIVE_WALK_THROUGH
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.preferences.Preferences.COLLAPSIBLE_CONVERSATION
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.preferences.Preferences.COLLAPSIBLE_HOME
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.preferences.Preferences.COLLAPSIBLE_SELECT_KEYBOARD
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.preferences.Preferences.COLLAPSIBLE_SETTINGS
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.preferences.Preferences.COLLAPSIBLE_TRANSLATION
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.preferences.Preferences.INTERSTITIAL_CONVERSATION_ENTER
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.preferences.Preferences.INTERSTITIAL_CONVERSATION_SAVE
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.preferences.Preferences.INTERSTITIAL_HISTORY_ENTER
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.preferences.Preferences.INTERSTITIAL_HISTORY_TO_CONVERSATION
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.preferences.Preferences.INTERSTITIAL_SETTINGS_ENTER
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.preferences.Preferences.INTERSTITIAL_SINDHI_STATUS_ENTER
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.preferences.Preferences.INTERSTITIAL_SINDHI_STATUS_POETRY_CLICK
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.preferences.Preferences.INTERSTITIAL_SPEECH_TO_TEXT_3_CLICK_ENABLE
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.preferences.Preferences.INTERSTITIAL_SPEECH_TO_TEXT_ENTER
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.preferences.Preferences.INTERSTITIAL_THEME_APPLIED
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.preferences.Preferences.INTERSTITIAL_THEME_ENTER
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.preferences.Preferences.INTERSTITIAL_TRANSLATION_ENTER
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.preferences.Preferences.OPEN_AD_ENABLE_KEYBOARD
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.preferences.Preferences.OPEN_AD_INSIDE_APP
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.utilityClasses.CustomFirebaseEvents
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.utilityClasses.isKeyboardEnabled
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.utilityClasses.isKeyboardSelected
import com.sindhi.urdu.english.keybad.sindhikeyboard.utils.MyPrefHelper
import com.sindhi.urdu.english.keybad.sindhikeyboard.utils.RemoteConfigConst
import com.sindhi.urdu.english.keybad.sindhikeyboard.utils.RemoteConfigConst.ADMOB_BANNER_SPLASH
import com.sindhi.urdu.english.keybad.sindhikeyboard.utils.RemoteConfigConst.ADMOB_INTERSTITIAL_LETS_START
import com.sindhi.urdu.english.keybad.sindhikeyboard.utils.RemoteConfigConst.ADMOB_NATIVE_LANGUAGE_1
import com.sindhi.urdu.english.keybad.sindhikeyboard.utils.RemoteConfigConst.ADMOB_NATIVE_LANGUAGE_2
import com.sindhi.urdu.english.keybad.sindhikeyboard.utils.RemoteConfigConst.ADMOB_NATIVE_SURVEY_1
import com.sindhi.urdu.english.keybad.sindhikeyboard.utils.RemoteConfigConst.ADMOB_NATIVE_SURVEY_2
import com.sindhi.urdu.english.keybad.sindhikeyboard.utils.RemoteConfigConst.ADMOB_NATIVE_WALKTHROUGH_1
import com.sindhi.urdu.english.keybad.sindhikeyboard.utils.RemoteConfigConst.ADMOB_NATIVE_WALKTHROUGH_2
import com.sindhi.urdu.english.keybad.sindhikeyboard.utils.RemoteConfigConst.ADMOB_NATIVE_WALKTHROUGH_3
import com.sindhi.urdu.english.keybad.sindhikeyboard.utils.RemoteConfigConst.ADMOB_NATIVE_WALKTHROUGH_FULLSCR
import com.sindhi.urdu.english.keybad.sindhikeyboard.utils.RemoteConfigConst.ADMOB_SPLASH_INTERSTITIAL
import com.sindhi.urdu.english.keybad.sindhikeyboard.utils.RemoteConfigConst.ADMOB_SPLASH_RESUME
import com.sindhi.urdu.english.keybad.sindhikeyboard.utils.RemoteConfigConst.ADS_BANNER_HISTORY
import com.sindhi.urdu.english.keybad.sindhikeyboard.utils.RemoteConfigConst.ADS_BANNER_THEMES_TEST
import com.sindhi.urdu.english.keybad.sindhikeyboard.utils.RemoteConfigConst.BANNER_POETRY
import com.sindhi.urdu.english.keybad.sindhikeyboard.utils.RemoteConfigConst.BANNER_SPLASH
import com.sindhi.urdu.english.keybad.sindhikeyboard.utils.RemoteConfigConst.BANNER_STICKER
import com.sindhi.urdu.english.keybad.sindhikeyboard.utils.RemoteConfigConst.BANNER_STICKER_DETAILS
import com.sindhi.urdu.english.keybad.sindhikeyboard.utils.RemoteConfigConst.INTERSTITIAL_STICKER_ENTER
import com.sindhi.urdu.english.keybad.sindhikeyboard.utils.RemoteConfigConst.IS_FIRST_TIME
import com.sindhi.urdu.english.keybad.sindhikeyboard.utils.RemoteConfigConst.NATIVE_STICKERS
import com.sindhi.urdu.english.keybad.sindhikeyboard.utils.RemoteConfigConst.NATIVE_STICKERS_DETAILS
import com.sindhi.urdu.english.keybad.sindhikeyboard.utils.RemoteConfigConst.TIMER_NATIVE_F_SRC


class SplashActivity : AppCompatActivity() {
    private var mFirebaseRemoteConfig: FirebaseRemoteConfig? = null
    private lateinit var binding: ActivitySplashBinding
    private lateinit var sotAdsConfigurations: SOTAdsConfigurations
    private var firstOpenFlowAdIds: HashMap<String, String> = HashMap()
    private var isDuplicateScreenStarted = true
    private var isFirstTime = true
    private var isFirst: Boolean = true
    private lateinit var pref: SharedPreferences

    override fun attachBaseContext(base: Context) {
        val language = MyPrefHelper(base).getSelectedLanguageCode()
        super.attachBaseContext(MyLocaleHelper.setLocale(base, language))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        CustomFirebaseEvents.logEvent(this, eventName = "splash_scr")
        pref = getSharedPreferences(REMOTE_CONFIG, MODE_PRIVATE)
        isFirst = pref.getBoolean("isFirst", true)
        setContentView(binding.root)
        setStatusBarColor(ContextCompat.getColor(this, R.color.board_theme__red))
        hideSystemUIUpdated()
        handleBackPress()
        CustomFirebaseEvents.logEvent(this, eventName = "splash_scr")
        supportActionBar?.hide()

        Glide.with(this@SplashActivity)
            .asBitmap()
            .load(R.drawable.ic_splash_bg)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .skipMemoryCache(true)
            .into(binding.clMainBg)

        Glide.with(this@SplashActivity)
            .asBitmap()
            .load(R.drawable.ic_splash)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .skipMemoryCache(true)
            .into(binding.ivSplashIcon)

        Glide.with(this@SplashActivity)
            .asBitmap()
            .load(R.drawable.ic_splash_text)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .skipMemoryCache(true)
            .into(binding.ivSplashTextIcon)
    }

    override fun onResume() {
        super.onResume()
        if (NetworkCheck.isNetworkAvailable(this)) {
            checkAppUpdate(this) {
                lifecycleScope.launch {
                    val remoteConfigData = initializeRemoteConfigAndAdIds()
                    startFirstOpenFlow(remoteConfigData)

                }
            }

        }
    }

    private fun handleBackPress() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                /**BackPress Should Disable InSplash**/
            }
        })
    }

//    private fun startFirstOpenFlow(remoteConfigData: HashMap<String, Any>) {
//
//        CustomFirebaseEvents.logEvent(this, eventName = "splash_scr")
//
//
//        SOTAdsManager.setOnFlowStateListener(
//            reConfigureBuilders = {
//                SOTAdsManager.refreshStrings(setUpWelcomeScreen(this), getWalkThroughList(this))
//            },
//            onFinish = {
//                gotoMain()
//            }
//        )
//
//        val consentConfig = ConsentConfigurations.Builder()
//            .setApplicationContext(applicationClass)
//            .setActivityContext(this)
//            .setTestDeviceHashedIdList(
//                arrayListOf(
//                    "1CACAE8A22757A86CEDB03884D2E2CFD",
//                    "DA22829C12F48E67EF7CB5DE5C301F89",
//                    "09DD12A6DB3BBF9B55D65FAA9FD7D8E0",
//                    "3F8FB4EE64D851EDBA704E705EC63A62",
//                    "84C3994693FB491110A5A4AEF8C5561B",
//                    "CB2F3812ACAA2A3D8C0B31682E1473EB",
//                    "F02B044F22C917805C3DF6E99D3B8800"
//                )
//            )
//            .setOnConsentGatheredCallback {
//
//                if (NetworkCheck.isNetworkAvailable(this)
//                ) {
//                    ResumeAd(applicationClass)
//                    val sdk: MBridgeSDK = MBridgeSDKFactory.getMBridgeSDK()
//                    sdk.setDoNotTrackStatus(false)
//                    sdk.setConsentStatus(this, MBridgeConstans.IS_SWITCH_ON)
//                }
//
//                sotAdsConfigurations.setRemoteConfigData(
//                    activityContext = this@SplashActivity,
//                    myRemoteConfigData = remoteConfigData
//                )
//
//                if (NetworkCheck.isNetworkAvailable(this) && remoteConfigData.getValue(BANNER_SPLASH) == true) {
//                    Log.d("SOT_ADS_TAG", "startFirstOpenFlow: trrr")
//                    binding.bannerAd.visibility = View.VISIBLE
//                    loadAdmobBannerAd() // This is now safe to call
//                } else {
//                    Log.d("SOT_ADS_TAG", "startFirstOpenFlow: falss")
//                    binding.bannerAd.visibility = View.GONE
//                }
//            }
//            .build()
//
//        val currentLocale: Locale = resources.configuration.locale
//        val language = currentLocale.language
//        Log.d("languageCode", "getWalkThroughList:$language ")
//        MyPrefHelper(this).setSelectedLanguageCode(language)
//
//        val welcomeScreensConfiguration = WelcomeScreensConfiguration.Builder()
//            .setActivityContext(this)
//            .setXMLLayout(setUpWelcomeScreen(this))
//            .build()
//
//        val languageScreensConfiguration = LanguageScreensConfiguration.Builder()
//            .setActivityContext(this)
//            .setDrawableColors(
//                selectedDrawable = AppCompatResources.getDrawable(
//                    this, R.drawable.ic_lang_selected
//                )!!,
//                unSelectedDrawable = AppCompatResources.getDrawable(
//                    this, R.drawable.ic_lang_unselected
//                )!!,
//                selectedRadio = AppCompatResources.getDrawable(
//                    this, R.drawable.ic_selected_radio
//                )!!,
//                unSelectedRadio = AppCompatResources.getDrawable(
//                    this, R.drawable.ic_unselected_radio
//                )!!,
//                tickSelector = AppCompatResources.getDrawable(
//                    this,
//                    com.manual.mediation.library.sotadlib.R.drawable.ic_done
//                )!!,
//                themeColor = ContextCompat.getColor(this, R.color.white),
//                statusBarColor = ContextCompat.getColor(this, R.color.white),
//                font = ContextCompat.getColor(this, R.color.black),
//                headingColor = ContextCompat.getColor(this, R.color.black)
//            )
//            .setLanguages(
//                arrayListOf(
//                    Language.Urdu,
//                    Language.English,
//                    Language.Hindi,
//                    Language.French,
//                    Language.Dutch,
//                    Language.Arabic,
//                    Language.German
//                )
//            )
//            .build()
//
//        val walkThroughScreensConfiguration = WalkThroughScreensConfiguration.Builder()
//            .setActivityContext(this)
//            .setWalkThroughContent(getWalkThroughList(this))
//            .build()
//
//        sotAdsConfigurations = SOTAdsConfigurations.Builder()
//            .setFirstOpenFlowAdIds(firstOpenFlowAdIds)
//            .setConsentConfig(consentConfig)
//            .setLanguageScreenConfiguration(languageScreensConfiguration)
//            .setWelcomeScreenConfiguration(welcomeScreensConfiguration)
//            .setWalkThroughScreenConfiguration(walkThroughScreensConfiguration)
//            .build()
//
//        SOTAdsManager.startFlow(sotAdsConfigurations)
//
//
//    }

    private fun startFirstOpenFlow(remoteConfigData: HashMap<String, Any>) {

        CustomFirebaseEvents.logEvent(this, eventName = "splash_scr")

        SOTAdsManager.setOnFlowStateListener(
            reConfigureBuilders = {
                SOTAdsManager.refreshStrings(setUpWelcomeScreen(this), getWalkThroughList(this))
            },
            onFinish = {
                gotoMain()
            }
        )

        val consentConfig = ConsentConfigurations.Builder()
            .setApplicationContext(applicationClass)
            .setActivityContext(this)
            .setTestDeviceHashedIdList(
                arrayListOf(
                    "1CACAE8A22757A86CEDB03884D2E2CFD",
                    "DA22829C12F48E67EF7CB5DE5C301F89",
                    "09DD12A6DB3BBF9B55D65FAA9FD7D8E0",
                    "3F8FB4EE64D851EDBA704E705EC63A62",
                    "84C3994693FB491110A5A4AEF8C5561B",
                    "CB2F3812ACAA2A3D8C0B31682E1473EB",
                    "F02B044F22C917805C3DF6E99D3B8800"
                )
            )
            .setOnConsentGatheredCallback {
                if (NetworkCheck.isNetworkAvailable(this)) {
                    ResumeAd(applicationClass)
                    val sdk: MBridgeSDK = MBridgeSDKFactory.getMBridgeSDK()
                    sdk.setDoNotTrackStatus(false)
                    sdk.setConsentStatus(this, MBridgeConstans.IS_SWITCH_ON)
                }

                // FIXED: Check if initialized to prevent crash
                if (::sotAdsConfigurations.isInitialized) {
                    sotAdsConfigurations.setRemoteConfigData(
                        activityContext = this@SplashActivity,
                        myRemoteConfigData = remoteConfigData
                    )
                }

                // Safe handling of HashMap getValue
                if (NetworkCheck.isNetworkAvailable(this) && remoteConfigData[BANNER_SPLASH] == true) {
                    Log.d("SOT_ADS_TAG", "startFirstOpenFlow: trrr")
                    binding.bannerAd.visibility = View.VISIBLE
                    loadAdmobBannerAd()
                } else {
                    Log.d("SOT_ADS_TAG", "startFirstOpenFlow: falss")
                    binding.bannerAd.visibility = View.GONE
                }
            }
            .build()

        val currentLocale: Locale = resources.configuration.locale
        val language = currentLocale.language
        Log.d("languageCode", "getWalkThroughList:$language ")
        MyPrefHelper(this).setSelectedLanguageCode(language)

        val welcomeScreensConfiguration = WelcomeScreensConfiguration.Builder()
            .setActivityContext(this)
            .setXMLLayout(setUpWelcomeScreen(this))
            .build()

        val languageScreensConfiguration = LanguageScreensConfiguration.Builder()
            .setActivityContext(this)
            .setDrawableColors(
                selectedDrawable = AppCompatResources.getDrawable(this, R.drawable.ic_lang_selected)!!,
                unSelectedDrawable = AppCompatResources.getDrawable(this, R.drawable.ic_lang_unselected)!!,
                selectedRadio = AppCompatResources.getDrawable(this, R.drawable.ic_selected_radio)!!,
                unSelectedRadio = AppCompatResources.getDrawable(this, R.drawable.ic_unselected_radio)!!,
                tickSelector = AppCompatResources.getDrawable(this, com.manual.mediation.library.sotadlib.R.drawable.ic_done)!!,
                themeColor = ContextCompat.getColor(this, R.color.white),
                statusBarColor = ContextCompat.getColor(this, R.color.white),
                font = ContextCompat.getColor(this, R.color.black),
                headingColor = ContextCompat.getColor(this, R.color.black)
            )
            .setLanguages(
                arrayListOf(
                    Language.Urdu, Language.English, Language.Hindi, Language.French,
                    Language.Dutch, Language.Arabic, Language.German
                )
            )
            .build()

        // INITIALIZE IT HERE
        val walkThroughScreensConfiguration = WalkThroughScreensConfiguration.Builder()
            .setActivityContext(this)
            .setWalkThroughContent(getWalkThroughList(this))
            .build()

        sotAdsConfigurations = SOTAdsConfigurations.Builder()
            .setFirstOpenFlowAdIds(firstOpenFlowAdIds)
            .setConsentConfig(consentConfig)
            .setLanguageScreenConfiguration(languageScreensConfiguration)
            .setWelcomeScreenConfiguration(welcomeScreensConfiguration)
            .setWalkThroughScreenConfiguration(walkThroughScreensConfiguration)
            .build()

        SOTAdsManager.startFlow(sotAdsConfigurations)
    }

    private fun setUpWelcomeScreen(context: Context): View {

        val activity = context as? Activity ?: run {
            return View(context).apply {
                // You might want to log this error in production
                Log.e("WelcomeScreen", "Context is not an Activity")
            }
        }

        val localizedConfig =
            resources.configuration.apply {
                MyLocaleHelper.onAttach(
                    context,
                    MyPrefHelper(this@SplashActivity).getSelectedLanguageCode()
                )
            }

        val localizedContext = ContextWrapper(this).createConfigurationContext(localizedConfig)

        val welcomeScreenView = LayoutInflater.from(localizedContext)
            .inflate(R.layout.layout_welcome_scr_1, null, false)
        val progressAnim = welcomeScreenView.findViewById<LottieAnimationView>(R.id.progress)


        val txtWallpapers =
            welcomeScreenView.findViewById<AppCompatTextView>(R.id.txtWallpapers)
        val txtSindhiEditor =
            welcomeScreenView.findViewById<AppCompatTextView>(R.id.txtSindhiEditor)
        val txtLiveThemes =
            welcomeScreenView.findViewById<AppCompatTextView>(R.id.txtLiveThemes)
        val txtPhotoOnKeyboard =
            welcomeScreenView.findViewById<AppCompatTextView>(R.id.txtPhotoOnKeyboard)
        val txtPhotoTranslator =
            welcomeScreenView.findViewById<AppCompatTextView>(R.id.txtPhotoTranslator)
        val txtInstantSticker =
            welcomeScreenView.findViewById<AppCompatTextView>(R.id.txtInstantSticker)
        val nextButton = welcomeScreenView.findViewById<AppCompatTextView>(R.id.txtNext)

        fun handleCheckboxToggle(
            isChecked: Boolean,
            textView: AppCompatTextView,
            eventName: String
        ): Boolean {
            if (isDuplicateScreenStarted) {
                SOTAdsManager.showWelcomeDupScreen()
            }
            isDuplicateScreenStarted = false
            progressAnim.visibility = View.GONE

            CustomFirebaseEvents.logEvent(activity, eventName = eventName)

            val newState = !isChecked
            textView.setBackgroundResource(
                if (newState) R.drawable.ic_selected_state
                else R.drawable.ic_unselected_state
            )
            return newState
        }

        // State variables
        var txtWallpapersBool = false
        var txtSindhiEditorBool = false
        var txtLiveThemesBool = false
        var txtPhotoOnKeyboardBool = false
        var txtCreatePDFBool = false
        var txtInstantStickerBool = false
        var next = false

        txtWallpapers.setOnClickListener {
            txtWallpapersBool = handleCheckboxToggle(
                txtWallpapersBool,
                txtWallpapers,
                "survey_scr_check_wallpaper"
            )
        }

        txtSindhiEditor.setOnClickListener {
            txtSindhiEditorBool = handleCheckboxToggle(
                txtSindhiEditorBool,
                txtSindhiEditor,
                "survey_scr_check_sindhi_editor"
            )
        }

        txtLiveThemes.setOnClickListener {
            txtLiveThemesBool = handleCheckboxToggle(
                txtLiveThemesBool,
                txtLiveThemes,
                "survey_scr_check_live_themes"
            )
        }

        txtPhotoOnKeyboard.setOnClickListener {
            txtPhotoOnKeyboardBool = handleCheckboxToggle(
                txtPhotoOnKeyboardBool,
                txtPhotoOnKeyboard,
                "survey_scr_check_photo_on_keyboard"
            )
        }

        txtPhotoTranslator.setOnClickListener {
            txtCreatePDFBool = handleCheckboxToggle(
                txtCreatePDFBool,
                txtPhotoTranslator,
                "survey_scr_check_photo_translator"
            )
        }

        txtInstantSticker.setOnClickListener {
            txtInstantStickerBool = handleCheckboxToggle(
                txtInstantStickerBool,
                txtInstantSticker,
                "survey_scr_check_instant_stickers"
            )
        }

        nextButton.setOnClickListener {
            if (txtWallpapersBool || txtSindhiEditorBool ||
                txtLiveThemesBool || txtPhotoOnKeyboardBool ||
                txtCreatePDFBool || txtInstantStickerBool
            ) {
                CustomFirebaseEvents.logEvent(activity, eventName = "survey2_scr")
                CustomFirebaseEvents.logEvent(activity, eventName = "survey2_scr_tap_continue")
                SOTAdsManager.completeWelcomeScreens()
            } else {
                CustomFirebaseEvents.logEvent(activity, eventName = "survey1_scr")
                CustomFirebaseEvents.logEvent(activity, eventName = "survey1_scr_tap_continue")

                if (isDuplicateScreenStarted) {
                    SOTAdsManager.showWelcomeDupScreen()
                }
                isDuplicateScreenStarted = false

                Toast.makeText(activity, "Please check the checkbox", Toast.LENGTH_SHORT)
                    .apply {
                        setGravity(Gravity.CENTER, 0, 0)
                        show()
                    }
            }
        }

        return welcomeScreenView
    }

    private fun getWalkThroughList(context: Context): ArrayList<WalkThroughItem> {
        val localizedContext = ContextWrapper(this).createConfigurationContext(
            resources.configuration.apply {
                MyLocaleHelper.onAttach(
                    context,
                    MyPrefHelper(this@SplashActivity).getSelectedLanguageCode()
                )
            }
        )

        return arrayListOf(
            WalkThroughItem(
                heading = "Amazing Themes",
                description = localizedContext.getString(R.string.label_w_1_descrption),
                headingColor = R.color.black,
                descriptionColor = R.color.black,
                nextColor = R.color.colorPrimary,
                drawableResId = R.drawable.ic_w_1,  // Changed from Drawable to resource ID
                drawableBubbleResId = R.drawable.ic_bubble_one,
                R.color.white,
                imageScale = 0
            ),
            WalkThroughItem(
                heading = "Hussn e Gufttar",
                description = localizedContext.getString(R.string.label_w_2_descrption),
                headingColor = R.color.black,
                descriptionColor = R.color.black,
                nextColor = R.color.colorPrimary,
                drawableResId = R.drawable.ic_w_2,
                drawableBubbleResId = R.drawable.ic_bubble_two,
                R.color.white,
                imageScale = 0


            ),
            WalkThroughItem(
                heading = "Funny Stickers & Emojis",
                description = localizedContext.getString(R.string.label_w_3_descrption),
                headingColor = R.color.black,
                descriptionColor = R.color.black,
                nextColor = R.color.colorPrimary,
                drawableResId = R.drawable.ic_w_3,
                drawableBubbleResId = R.drawable.ic_bubble_three,
                R.color.white,
                imageScale = 0
            )
        )
    }



    private fun saveAllValues() {
        Log.i("HelloTag", "saveAllValues: ")

        val editor = getSharedPreferences("RemoteConfig", MODE_PRIVATE).edit()

        mFirebaseRemoteConfig?.apply {
            getString(RemoteConfigConst.RESUME_INTER_SPLASH).trim().takeIf { it.isNotEmpty() }
                ?.let {
                    editor.putString(RemoteConfigConst.RESUME_INTER_SPLASH, it)
                }
            editor.putBoolean(
                BANNER_SPLASH, getBoolean(BANNER_SPLASH)
            )

            editor.putBoolean(
                RemoteConfigConst.RESUME_OVERALL, getBoolean(RemoteConfigConst.RESUME_OVERALL)
            )

            Log.e("resume", "value of ${getBoolean(RemoteConfigConst.RESUME_OVERALL)}")

            editor.putBoolean(
                RemoteConfigConst.NATIVE_LANGUAGE_1, getBoolean(RemoteConfigConst.NATIVE_LANGUAGE_1)
            )
            editor.putBoolean(
                RemoteConfigConst.NATIVE_LANGUAGE_2, getBoolean(RemoteConfigConst.NATIVE_LANGUAGE_2)
            )
            editor.putBoolean(
                RemoteConfigConst.NATIVE_SURVEY_1, getBoolean(RemoteConfigConst.NATIVE_SURVEY_1)
            )
            editor.putBoolean(
                RemoteConfigConst.NATIVE_SURVEY_2, getBoolean(RemoteConfigConst.NATIVE_SURVEY_2)
            )
            editor.putBoolean(
                RemoteConfigConst.NATIVE_WALKTHROUGH_1,
                getBoolean(RemoteConfigConst.NATIVE_WALKTHROUGH_1)
            )
            editor.putBoolean(
                RemoteConfigConst.NATIVE_WALKTHROUGH_2,
                getBoolean(RemoteConfigConst.NATIVE_WALKTHROUGH_2)
            )
            editor.putBoolean(
                RemoteConfigConst.NATIVE_WALKTHROUGH_FULLSCR,
                getBoolean(RemoteConfigConst.NATIVE_WALKTHROUGH_FULLSCR)
            )
            editor.putBoolean(
                RemoteConfigConst.NATIVE_WALKTHROUGH_3,
                getBoolean(RemoteConfigConst.NATIVE_WALKTHROUGH_3)
            )
            editor.putBoolean(
                RemoteConfigConst.INTERSTITIAL_LETS_START,
                getBoolean(RemoteConfigConst.INTERSTITIAL_LETS_START)
            )

        }


            Log.e(
                "Ids Firebase",
                "Fetched SuccessFully : Native :: " + mFirebaseRemoteConfig!!.getString(
                    KEYPAD_BANNER_SHOW
                )
            )

            if (!TextUtils.isEmpty(mFirebaseRemoteConfig!!.getString(KEYPAD_BANNER_SHOW).trim())) {
                getSharedPreferences("RemoteConfig", MODE_PRIVATE).edit()
                    .putString(
                        KEYPAD_BANNER_SHOW,
                        mFirebaseRemoteConfig!!.getString(KEYPAD_BANNER_SHOW)
                    ).apply()
            }

            // ADS REMOTE CONFIG NATIVE
            if (!TextUtils.isEmpty(mFirebaseRemoteConfig!!.getString(ADS_NATIVE_WALK_THROUGH).trim())) {
                getSharedPreferences("RemoteConfig", MODE_PRIVATE).edit()
                    .putString(
                        ADS_NATIVE_WALK_THROUGH,
                        mFirebaseRemoteConfig!!.getString(ADS_NATIVE_WALK_THROUGH)
                    ).apply()
            }

            if (!TextUtils.isEmpty(
                    mFirebaseRemoteConfig!!.getString(
                        ADS_NATIVE_SELECT_LANGUAGE_SPEECH_TO_TEXT
                    ).trim()
                )
            ) {
                getSharedPreferences("RemoteConfig", MODE_PRIVATE).edit()
                    .putString(
                        ADS_NATIVE_SELECT_LANGUAGE_SPEECH_TO_TEXT,
                        mFirebaseRemoteConfig!!.getString(ADS_NATIVE_SELECT_LANGUAGE_SPEECH_TO_TEXT)
                    ).apply()
            }


            if (!TextUtils.isEmpty(
                    mFirebaseRemoteConfig!!.getString(
                        INTERSTITIAL_STICKER_ENTER
                    ).trim()
                )
            ) {
                getSharedPreferences("RemoteConfig", MODE_PRIVATE).edit()
                    .putString(
                        INTERSTITIAL_STICKER_ENTER,
                        mFirebaseRemoteConfig!!.getString(INTERSTITIAL_STICKER_ENTER)
                    ).apply()
            }

            if (!TextUtils.isEmpty(mFirebaseRemoteConfig!!.getString(ADS_NATIVE_CONVERSATION).trim())) {
                getSharedPreferences("RemoteConfig", MODE_PRIVATE).edit()
                    .putString(
                        ADS_NATIVE_CONVERSATION,
                        mFirebaseRemoteConfig!!.getString(ADS_NATIVE_CONVERSATION)
                    ).apply()
            }

            if (!TextUtils.isEmpty(mFirebaseRemoteConfig!!.getString(ADS_NATIVE_HISTORY).trim())) {
                getSharedPreferences("RemoteConfig", MODE_PRIVATE).edit()
                    .putString(
                        ADS_NATIVE_HISTORY,
                        mFirebaseRemoteConfig!!.getString(ADS_NATIVE_HISTORY)
                    ).apply()
            }

            if (!TextUtils.isEmpty(mFirebaseRemoteConfig!!.getString(BANNER_POETRY).trim())) {
                getSharedPreferences("RemoteConfig", MODE_PRIVATE).edit()
                    .putString(BANNER_POETRY, mFirebaseRemoteConfig!!.getString(BANNER_POETRY)).apply()
            }

            if (!TextUtils.isEmpty(mFirebaseRemoteConfig!!.getString(ADS_NATIVE_HOME).trim())) {
                getSharedPreferences("RemoteConfig", MODE_PRIVATE).edit()
                    .putString(ADS_NATIVE_HOME, mFirebaseRemoteConfig!!.getString(ADS_NATIVE_HOME))
                    .apply()
            }

            if (!TextUtils.isEmpty(mFirebaseRemoteConfig!!.getString(ADS_NATIVE_EXIT).trim())) {
                getSharedPreferences("RemoteConfig", MODE_PRIVATE).edit()
                    .putString(ADS_NATIVE_EXIT, mFirebaseRemoteConfig!!.getString(ADS_NATIVE_EXIT))
                    .apply()
            }

            if (!TextUtils.isEmpty(mFirebaseRemoteConfig!!.getString(ADS_NATIVE_THEMES).trim())) {
                getSharedPreferences("RemoteConfig", MODE_PRIVATE).edit()
                    .putString(ADS_NATIVE_THEMES, mFirebaseRemoteConfig!!.getString(ADS_NATIVE_THEMES))
                    .apply()
            }

            if (!TextUtils.isEmpty(mFirebaseRemoteConfig!!.getString(ADS_NATIVE_THEMES_APPLY).trim())) {
                getSharedPreferences("RemoteConfig", MODE_PRIVATE).edit()
                    .putString(
                        ADS_NATIVE_THEMES_APPLY,
                        mFirebaseRemoteConfig!!.getString(ADS_NATIVE_THEMES_APPLY)
                    ).apply()
            }

            if (!TextUtils.isEmpty(
                    mFirebaseRemoteConfig!!.getString(ADS_NATIVE_THEMES_APPLIED_TEST).trim()
                )
            ) {
                getSharedPreferences("RemoteConfig", MODE_PRIVATE).edit()
                    .putString(
                        ADS_NATIVE_THEMES_APPLIED_TEST,
                        mFirebaseRemoteConfig!!.getString(ADS_NATIVE_THEMES_APPLIED_TEST)
                    ).apply()
            }

            if (!TextUtils.isEmpty(mFirebaseRemoteConfig!!.getString(ADS_NATIVE_POETRY).trim())) {
                getSharedPreferences("RemoteConfig", MODE_PRIVATE).edit()
                    .putString(ADS_NATIVE_POETRY, mFirebaseRemoteConfig!!.getString(ADS_NATIVE_POETRY))
                    .apply()
            }

            if (!TextUtils.isEmpty(
                    mFirebaseRemoteConfig!!.getString(ADS_NATIVE_POETRY_INSIDE).trim()
                )
            ) {
                getSharedPreferences("RemoteConfig", MODE_PRIVATE).edit()
                    .putString(
                        ADS_NATIVE_POETRY_INSIDE,
                        mFirebaseRemoteConfig!!.getString(ADS_NATIVE_POETRY_INSIDE)
                    ).apply()
            }

            if (!TextUtils.isEmpty(
                    mFirebaseRemoteConfig!!.getString(ADS_NATIVE_SELECT_KEYBOARD).trim()
                )
            ) {
                getSharedPreferences("RemoteConfig", MODE_PRIVATE).edit()
                    .putString(
                        ADS_NATIVE_SELECT_KEYBOARD,
                        mFirebaseRemoteConfig!!.getString(ADS_NATIVE_SELECT_KEYBOARD)
                    ).apply()
            }

            if (!TextUtils.isEmpty(mFirebaseRemoteConfig!!.getString(ADS_NATIVE_SETTINGS).trim())) {
                getSharedPreferences("RemoteConfig", MODE_PRIVATE).edit()
                    .putString(
                        ADS_NATIVE_SETTINGS,
                        mFirebaseRemoteConfig!!.getString(ADS_NATIVE_SETTINGS)
                    ).apply()
            }

            if (!TextUtils.isEmpty(mFirebaseRemoteConfig!!.getString(ADS_NATIVE_SPEECHTOTEXT).trim())) {
                getSharedPreferences("RemoteConfig", MODE_PRIVATE).edit()
                    .putString(
                        ADS_NATIVE_SPEECHTOTEXT,
                        mFirebaseRemoteConfig!!.getString(ADS_NATIVE_SPEECHTOTEXT)
                    ).apply()
            }

            if (!TextUtils.isEmpty(
                    mFirebaseRemoteConfig!!.getString(ADS_NATIVE_TRANSLATION_HOME).trim()
                )
            ) {
                getSharedPreferences("RemoteConfig", MODE_PRIVATE).edit()
                    .putString(
                        ADS_NATIVE_TRANSLATION_HOME,
                        mFirebaseRemoteConfig!!.getString(ADS_NATIVE_TRANSLATION_HOME)
                    ).apply()
            }

            if (!TextUtils.isEmpty(
                    mFirebaseRemoteConfig!!.getString(ADS_NATIVE_TRANSLATION_HOME).trim()
                )
            ) {
                getSharedPreferences("RemoteConfig", MODE_PRIVATE).edit()
                    .putString(
                        ADS_NATIVE_TRANSLATION_HOME,
                        mFirebaseRemoteConfig!!.getString(ADS_NATIVE_TRANSLATION_HOME)
                    ).apply()
            }

            if (!TextUtils.isEmpty(mFirebaseRemoteConfig!!.getString(COLLAPSIBLE_HOME).trim())) {
                getSharedPreferences("RemoteConfig", MODE_PRIVATE).edit()
                    .putString(COLLAPSIBLE_HOME, mFirebaseRemoteConfig!!.getString(COLLAPSIBLE_HOME))
                    .apply()
            }

            if (!TextUtils.isEmpty(mFirebaseRemoteConfig!!.getString(COLLAPSIBLE_TRANSLATION).trim())) {
                getSharedPreferences("RemoteConfig", MODE_PRIVATE).edit()
                    .putString(
                        COLLAPSIBLE_TRANSLATION,
                        mFirebaseRemoteConfig!!.getString(COLLAPSIBLE_TRANSLATION)
                    ).apply()
            }

            if (!TextUtils.isEmpty(
                    mFirebaseRemoteConfig!!.getString(COLLAPSIBLE_SELECT_KEYBOARD).trim()
                )
            ) {
                getSharedPreferences("RemoteConfig", MODE_PRIVATE).edit()
                    .putString(
                        COLLAPSIBLE_SELECT_KEYBOARD,
                        mFirebaseRemoteConfig!!.getString(COLLAPSIBLE_SELECT_KEYBOARD)
                    ).apply()
            }

            if (!TextUtils.isEmpty(mFirebaseRemoteConfig!!.getString(COLLAPSIBLE_SETTINGS).trim())) {
                getSharedPreferences("RemoteConfig", MODE_PRIVATE).edit()
                    .putString(
                        COLLAPSIBLE_SETTINGS,
                        mFirebaseRemoteConfig!!.getString(COLLAPSIBLE_SETTINGS)
                    ).apply()
            }

            if (!TextUtils.isEmpty(mFirebaseRemoteConfig!!.getString(ADS_BANNER_THEMES_TEST).trim())) {
                getSharedPreferences("RemoteConfig", MODE_PRIVATE).edit()
                    .putString(
                        ADS_BANNER_THEMES_TEST,
                        mFirebaseRemoteConfig!!.getString(ADS_BANNER_THEMES_TEST)
                    ).apply()
            }

            if (!TextUtils.isEmpty(mFirebaseRemoteConfig!!.getString(ADS_BANNER_HISTORY).trim())) {
                getSharedPreferences("RemoteConfig", MODE_PRIVATE).edit()
                    .putString(
                        ADS_BANNER_HISTORY,
                        mFirebaseRemoteConfig!!.getString(ADS_BANNER_HISTORY)
                    ).apply()
            }

            if (!TextUtils.isEmpty(
                    mFirebaseRemoteConfig!!.getString(COLLAPSIBLE_CONVERSATION).trim()
                )
            ) {
                getSharedPreferences("RemoteConfig", MODE_PRIVATE).edit()
                    .putString(
                        COLLAPSIBLE_CONVERSATION,
                        mFirebaseRemoteConfig!!.getString(COLLAPSIBLE_CONVERSATION)
                    ).apply()
            }

            if (!TextUtils.isEmpty(
                    mFirebaseRemoteConfig!!.getString(INTERSTITIAL_THEME_ENTER).trim()
                )
            ) {
                getSharedPreferences("RemoteConfig", MODE_PRIVATE).edit()
                    .putString(
                        INTERSTITIAL_THEME_ENTER,
                        mFirebaseRemoteConfig!!.getString(INTERSTITIAL_THEME_ENTER)
                    ).apply()
            }

            if (!TextUtils.isEmpty(
                    mFirebaseRemoteConfig!!.getString(INTERSTITIAL_THEME_APPLIED).trim()
                )
            ) {
                getSharedPreferences("RemoteConfig", MODE_PRIVATE).edit()
                    .putString(
                        INTERSTITIAL_THEME_APPLIED,
                        mFirebaseRemoteConfig!!.getString(INTERSTITIAL_THEME_APPLIED)
                    ).apply()
            }

            if (!TextUtils.isEmpty(
                    mFirebaseRemoteConfig!!.getString(INTERSTITIAL_SINDHI_STATUS_ENTER).trim()
                )
            ) {
                getSharedPreferences("RemoteConfig", MODE_PRIVATE).edit()
                    .putString(
                        INTERSTITIAL_SINDHI_STATUS_ENTER,
                        mFirebaseRemoteConfig!!.getString(INTERSTITIAL_SINDHI_STATUS_ENTER)
                    ).apply()
            }

            if (!TextUtils.isEmpty(
                    mFirebaseRemoteConfig!!.getString(
                        INTERSTITIAL_SINDHI_STATUS_POETRY_CLICK
                    ).trim()
                )
            ) {
                getSharedPreferences("RemoteConfig", MODE_PRIVATE).edit()
                    .putString(
                        INTERSTITIAL_SINDHI_STATUS_POETRY_CLICK,
                        mFirebaseRemoteConfig!!.getString(INTERSTITIAL_SINDHI_STATUS_POETRY_CLICK)
                    ).apply()
            }

            if (!TextUtils.isEmpty(
                    mFirebaseRemoteConfig!!.getString(INTERSTITIAL_TRANSLATION_ENTER).trim()
                )
            ) {
                getSharedPreferences("RemoteConfig", MODE_PRIVATE).edit()
                    .putString(
                        INTERSTITIAL_TRANSLATION_ENTER,
                        mFirebaseRemoteConfig!!.getString(INTERSTITIAL_TRANSLATION_ENTER)
                    ).apply()
            }

            if (!TextUtils.isEmpty(
                    mFirebaseRemoteConfig!!.getString(INTERSTITIAL_SETTINGS_ENTER).trim()
                )
            ) {
                getSharedPreferences("RemoteConfig", MODE_PRIVATE).edit()
                    .putString(
                        INTERSTITIAL_SETTINGS_ENTER,
                        mFirebaseRemoteConfig!!.getString(INTERSTITIAL_SETTINGS_ENTER)
                    ).apply()
            }

            if (!TextUtils.isEmpty(
                    mFirebaseRemoteConfig!!.getString(INTERSTITIAL_CONVERSATION_ENTER).trim()
                )
            ) {
                getSharedPreferences("RemoteConfig", MODE_PRIVATE).edit()
                    .putString(
                        INTERSTITIAL_CONVERSATION_ENTER,
                        mFirebaseRemoteConfig!!.getString(INTERSTITIAL_CONVERSATION_ENTER)
                    ).apply()
            }

            if (!TextUtils.isEmpty(
                    mFirebaseRemoteConfig!!.getString(INTERSTITIAL_CONVERSATION_SAVE).trim()
                )
            ) {
                getSharedPreferences("RemoteConfig", MODE_PRIVATE).edit()
                    .putString(
                        INTERSTITIAL_CONVERSATION_SAVE,
                        mFirebaseRemoteConfig!!.getString(INTERSTITIAL_CONVERSATION_SAVE)
                    ).apply()
            }

            if (!TextUtils.isEmpty(
                    mFirebaseRemoteConfig!!.getString(INTERSTITIAL_SPEECH_TO_TEXT_ENTER).trim()
                )
            ) {
                getSharedPreferences("RemoteConfig", MODE_PRIVATE).edit()
                    .putString(
                        INTERSTITIAL_SPEECH_TO_TEXT_ENTER,
                        mFirebaseRemoteConfig!!.getString(INTERSTITIAL_SPEECH_TO_TEXT_ENTER)
                    ).apply()
            }

            if (!TextUtils.isEmpty(
                    mFirebaseRemoteConfig!!.getString(BANNER_STICKER).trim()
                )
            ) {
                getSharedPreferences("RemoteConfig", MODE_PRIVATE).edit()
                    .putString(
                        BANNER_STICKER,
                        mFirebaseRemoteConfig!!.getString(BANNER_STICKER)
                    ).apply()
            }

            if (!TextUtils.isEmpty(
                    mFirebaseRemoteConfig!!.getString(NATIVE_STICKERS).trim()
                )
            ) {
                getSharedPreferences("RemoteConfig", MODE_PRIVATE).edit {
                    putString(
                        NATIVE_STICKERS,
                        mFirebaseRemoteConfig!!.getString(NATIVE_STICKERS)
                    )
                }
            }

            if (!TextUtils.isEmpty(
                    mFirebaseRemoteConfig!!.getString(NATIVE_STICKERS_DETAILS).trim()
                )
            ) {
                getSharedPreferences("RemoteConfig", MODE_PRIVATE).edit {
                    putString(
                        NATIVE_STICKERS_DETAILS,
                        mFirebaseRemoteConfig!!.getString(NATIVE_STICKERS_DETAILS)
                    )
                }
            }

            if (!TextUtils.isEmpty(
                    mFirebaseRemoteConfig!!.getString(BANNER_STICKER_DETAILS).trim()
                )
            ) {
                getSharedPreferences("RemoteConfig", MODE_PRIVATE).edit {
                    putString(
                        BANNER_STICKER_DETAILS,
                        mFirebaseRemoteConfig!!.getString(BANNER_STICKER_DETAILS)
                    )
                }
            }

            if (!TextUtils.isEmpty(
                    mFirebaseRemoteConfig!!.getString(
                        INTERSTITIAL_SPEECH_TO_TEXT_3_CLICK_ENABLE
                    ).trim()
                )
            ) {
                getSharedPreferences("RemoteConfig", MODE_PRIVATE).edit {
                    putString(
                        INTERSTITIAL_SPEECH_TO_TEXT_3_CLICK_ENABLE,
                        mFirebaseRemoteConfig!!.getString(INTERSTITIAL_SPEECH_TO_TEXT_3_CLICK_ENABLE)
                    )
                }
            }

            if (!TextUtils.isEmpty(
                    mFirebaseRemoteConfig!!.getString(INTERSTITIAL_HISTORY_ENTER).trim()
                )
            ) {
                getSharedPreferences("RemoteConfig", MODE_PRIVATE).edit {
                    putString(
                        INTERSTITIAL_HISTORY_ENTER,
                        mFirebaseRemoteConfig!!.getString(INTERSTITIAL_HISTORY_ENTER)
                    )
                }
            }

            if (!TextUtils.isEmpty(
                    mFirebaseRemoteConfig!!.getString(
                        INTERSTITIAL_HISTORY_TO_CONVERSATION
                    ).trim()
                )
            ) {
                getSharedPreferences("RemoteConfig", MODE_PRIVATE).edit {
                    putString(
                        INTERSTITIAL_HISTORY_TO_CONVERSATION,
                        mFirebaseRemoteConfig!!.getString(INTERSTITIAL_HISTORY_TO_CONVERSATION)
                    )
                }
            }

            // ADS REMOTE CONFIG INTERSTITIAL
            if (!TextUtils.isEmpty(mFirebaseRemoteConfig!!.getString(OPEN_AD_ENABLE_KEYBOARD).trim())) {
                getSharedPreferences("RemoteConfig", MODE_PRIVATE).edit {
                    putString(
                        OPEN_AD_ENABLE_KEYBOARD,
                        mFirebaseRemoteConfig!!.getString(OPEN_AD_ENABLE_KEYBOARD)
                    )
                }
            }

            if (!TextUtils.isEmpty(mFirebaseRemoteConfig!!.getString(OPEN_AD_INSIDE_APP).trim())) {
                getSharedPreferences("RemoteConfig", MODE_PRIVATE).edit {
                    putString(
                        OPEN_AD_INSIDE_APP,
                        mFirebaseRemoteConfig!!.getString(OPEN_AD_INSIDE_APP)
                    )
                }
            }



        editor.apply()

    }


    private fun getSharedPreferencesValues(): HashMap<String, Any> {
        Log.i("HelloTag", "getSharedPreferencesValues: ")
        val remoteConfigHashMap: HashMap<String, Any> = HashMap()
        val prefs: SharedPreferences = getSharedPreferences(REMOTE_CONFIG, MODE_PRIVATE)

        isFirstTime = prefs.getBoolean(IS_FIRST_TIME, true)

        remoteConfigHashMap.apply {
            this["RESUME_INTER_SPLASH"] =
                "${prefs.getString(RemoteConfigConst.RESUME_INTER_SPLASH, "Empty")}"
            this["BANNER_SPLASH"] = prefs.getBoolean(BANNER_SPLASH, false)
            this["RESUME_OVERALL"] = prefs.getBoolean(RemoteConfigConst.RESUME_OVERALL, false)
            this["NATIVE_LANGUAGE_1"] = prefs.getBoolean(RemoteConfigConst.NATIVE_LANGUAGE_1, false)
            this["NATIVE_LANGUAGE_2"] = prefs.getBoolean(RemoteConfigConst.NATIVE_LANGUAGE_2, false)
            this["NATIVE_SURVEY_1"] = prefs.getBoolean(RemoteConfigConst.NATIVE_SURVEY_1, false)
            this["NATIVE_SURVEY_2"] = prefs.getBoolean(RemoteConfigConst.NATIVE_SURVEY_2, false)
            this["INTERSTITIAL_LETS_START"] =
                prefs.getBoolean(RemoteConfigConst.INTERSTITIAL_LETS_START, false)
            this["NATIVE_WALKTHROUGH_1"] =
                prefs.getBoolean(RemoteConfigConst.NATIVE_WALKTHROUGH_1, false)
            this["NATIVE_WALKTHROUGH_2"] =
                prefs.getBoolean(RemoteConfigConst.NATIVE_WALKTHROUGH_2, false)
            this["NATIVE_WALKTHROUGH_FULLSCR"] =
                prefs.getBoolean(RemoteConfigConst.NATIVE_WALKTHROUGH_FULLSCR, false)
            this["NATIVE_WALKTHROUGH_3"] =
                prefs.getBoolean(RemoteConfigConst.NATIVE_WALKTHROUGH_3, false)
            this["TIMER_NATIVE_F_SRC"] = "${prefs.getString(TIMER_NATIVE_F_SRC, "Empty")}"
            this["IS_PURCHASED"] = false
        }
        return remoteConfigHashMap
    }

    private fun loadAdmobBannerAd() {
        val pID = firstOpenFlowAdIds.get("ADMOB_BANNER_SPLASH")
            ?: resources.getString(R.string.ADMOB_BANNER_SPLASH)
        Log.i("SOT_ADS_TAG", "placementID=$pID")

        AdMobBannerAdSplash(
            this@SplashActivity,
            placementID = pID,//resources.getString(R.string.ADMOB_BANNER_SPLASH),
            bannerContainer = binding.bannerAd,
            shimmerContainer = binding.bannerAd,
            onAdFailed = {
                Log.i("SOT_ADS_TAG", "onAdFailed")
                binding.bannerAd.visibility = View.GONE
            },
            onAdLoaded = {
                binding.bannerAd.visibility = View.VISIBLE
                Log.i("SOT_ADS_TAG", "onAdLoaded ddd")
            },
            onAdClicked = {}
        )
    }

    private fun gotoMain() {
        val time = if (PrefHelper(this).getBooleanDefault("StartScreens", default = false)) {
            0
        } else {
            if (NetworkCheck.isNetworkAvailable(this)) {
                0
            } else {
                3000
            }
        }
        Handler(Looper.getMainLooper()).postDelayed({
            val intent: Intent = if (!isKeyboardEnabled(this) || !isKeyboardSelected(this)) {
                Intent(this, KeyboardSelectionActivity::class.java).putExtra(
                    "MoveTo", intent.getStringExtra("MoveTo")
                )
            } else if (intent.getStringExtra("MoveTo").equals("Stickers")) {
                Intent(this, StickersViewActivity::class.java).putExtra(
                    "MoveTo",
                    intent.getStringExtra("MoveTo")
                )
            } else {
                Intent(this, NavigationActivity::class.java).putExtra(
                    "MoveTo",
                    intent.getStringExtra("MoveTo")
                )
            }
            startActivity(intent)
            finish()
        }, time.toLong())
    }


    private suspend fun initializeRemoteConfigAndAdIds(): HashMap<String, Any> =
        withContext(Dispatchers.IO) {
            // Initialize Remote Config
            if (mFirebaseRemoteConfig == null) {
                mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
                val configSettings = FirebaseRemoteConfigSettings.Builder()
                    .setMinimumFetchIntervalInSeconds(0) // Good for testing, set to 3600 for production
                    .build()
                mFirebaseRemoteConfig!!.setConfigSettingsAsync(configSettings)
            }

            if (NetworkCheck.isNetworkAvailable(this@SplashActivity)) {
                if (!BuildConfig.DEBUG) {
                    try {
                        val fetchSuccessful = mFirebaseRemoteConfig!!.fetchAndActivate().await()
                        if (fetchSuccessful) {
                            Log.i("RemoteConfig", "RemoteConfig fetched successfully")
                            fillAdIdsFromRemote(mFirebaseRemoteConfig!!)
                            saveAllValues()
                        } else {
                            Log.i("RemoteConfig", "RemoteConfig fetch failed, using fallback")
                            fillAdIdsFromResources()
                        }
                    } catch (e: Exception) {
                        Log.e("RemoteConfig", "Exception fetching config: ${e.message}")
                        fillAdIdsFromResources()
                    }
                } else {
                    try {
                        Log.i("RemoteConfig", "debug ...fetching (debug mode)")
                        val fetchSuccessful = mFirebaseRemoteConfig!!.fetchAndActivate().await()
                        if (fetchSuccessful) {
                            Log.d("RemoteConfig", "RemoteConfig fetched successfully (debug)")
                            fillAdIdsFromResources() // Or use fillAdIdsFromRemote if you want debug remote
                            saveAllValues()
                        } else {
                            Log.d(
                                "RemoteConfig",
                                "RemoteConfig fetch failed (debug), using fallback"
                            )
                            fillAdIdsFromResources()
                        }
                    } catch (e: Exception) {
                        Log.e("RemoteConfig", "Exception fetching config (debug): ${e.message}")
                        fillAdIdsFromResources()
                    }
                }
            } else {
                Log.i("RemoteConfig", "No network, using local resources")
                fillAdIdsFromResources()
            }

            return@withContext getSharedPreferencesValues()
        }


//    private fun fillAdIdsFromRemote(remoteConfig: FirebaseRemoteConfig) {
//        firstOpenFlowAdIds.apply {
//            this["ADMOB_SPLASH_INTERSTITIAL"] = remoteConfig.getString(ADMOB_SPLASH_INTERSTITIAL)
//            this["ADMOB_SPLASH_RESUME"] = remoteConfig.getString(ADMOB_SPLASH_RESUME)
//            this["ADMOB_BANNER_SPLASH"] = remoteConfig.getString(ADMOB_BANNER_SPLASH)
//            this["ADMOB_NATIVE_LANGUAGE_1"] = remoteConfig.getString(ADMOB_NATIVE_LANGUAGE_1)
//            this["ADMOB_NATIVE_LANGUAGE_2"] = remoteConfig.getString(ADMOB_NATIVE_LANGUAGE_2)
//            this["ADMOB_NATIVE_SURVEY_1"] = remoteConfig.getString(ADMOB_NATIVE_SURVEY_1)
//            this["ADMOB_NATIVE_SURVEY_2"] = remoteConfig.getString(ADMOB_NATIVE_SURVEY_2)
//            this["ADMOB_NATIVE_WALKTHROUGH_1"] = remoteConfig.getString(ADMOB_NATIVE_WALKTHROUGH_1)
//            this["ADMOB_NATIVE_WALKTHROUGH_2"] = remoteConfig.getString(ADMOB_NATIVE_WALKTHROUGH_2)
//            this["ADMOB_NATIVE_WALKTHROUGH_FULLSCR"] =
//                remoteConfig.getString(ADMOB_NATIVE_WALKTHROUGH_FULLSCR)
//            this["ADMOB_NATIVE_WALKTHROUGH_3"] = remoteConfig.getString(ADMOB_NATIVE_WALKTHROUGH_3)
//            this["ADMOB_INTERSTITIAL_LETS_START"] = remoteConfig.getString(ADMOB_INTERSTITIAL_LETS_START)
//
//            forEach { (key, value) ->
//                Log.d("AdConfig", "$key -> $value")
//            }
//        }
//    }
//
//    private fun fillAdIdsFromResources() {
//        firstOpenFlowAdIds.apply {
//            this["ADMOB_SPLASH_INTERSTITIAL"] = getString(R.string.ADMOB_SPLASH_INTERSTITIAL)
//            this["ADMOB_SPLASH_RESUME"] = getString(R.string.ADMOB_SPLASH_RESUME)
//            this["ADMOB_BANNER_SPLASH"] = getString(R.string.ADMOB_BANNER_SPLASH)
//            this["ADMOB_NATIVE_LANGUAGE_1"] = getString(R.string.ADMOB_NATIVE_LANGUAGE_1)
//            this["ADMOB_NATIVE_LANGUAGE_2"] = getString(R.string.ADMOB_NATIVE_LANGUAGE_2)
//            this["ADMOB_NATIVE_SURVEY_1"] = getString(R.string.ADMOB_NATIVE_SURVEY_1)
//            this["ADMOB_NATIVE_SURVEY_2"] = getString(R.string.ADMOB_NATIVE_SURVEY_2)
//            this["ADMOB_NATIVE_WALKTHROUGH_1"] = getString(R.string.ADMOB_NATIVE_WALKTHROUGH_1)
//            this["ADMOB_NATIVE_WALKTHROUGH_2"] = getString(R.string.ADMOB_NATIVE_WALKTHROUGH_2)
//            this["ADMOB_NATIVE_WALKTHROUGH_FULLSCR"] = getString(R.string.ADMOB_NATIVE_WALKTHROUGH_FULLSCR)
//            this["ADMOB_NATIVE_WALKTHROUGH_3"] = getString(R.string.ADMOB_NATIVE_WALKTHROUGH_3)
//            this["ADMOB_INTERSTITIAL_LETS_START"] =
//                getString(R.string.ADMOB_INTERSTITIAL_LETS_START)
//        }
//    }

    private fun fillAdIdsFromRemote(remoteConfig: FirebaseRemoteConfig) {
        firstOpenFlowAdIds.apply {
            this["ADMOB_SPLASH_INTERSTITIAL"] = remoteConfig.getString(ADMOB_SPLASH_INTERSTITIAL)
            this["ADMOB_SPLASH_RESUME"] = remoteConfig.getString(ADMOB_SPLASH_RESUME)
            this["ADMOB_BANNER_SPLASH"] = remoteConfig.getString(ADMOB_BANNER_SPLASH)
            this["ADMOB_NATIVE_LANGUAGE_1"] = remoteConfig.getString(ADMOB_NATIVE_LANGUAGE_1)
            this["ADMOB_NATIVE_LANGUAGE_2"] = remoteConfig.getString(ADMOB_NATIVE_LANGUAGE_2)
            this["ADMOB_NATIVE_SURVEY_1"] = remoteConfig.getString(ADMOB_NATIVE_SURVEY_1)
            this["ADMOB_NATIVE_SURVEY_2"] = remoteConfig.getString(ADMOB_NATIVE_SURVEY_2)
            this["ADMOB_NATIVE_WALKTHROUGH_1"] = remoteConfig.getString(ADMOB_NATIVE_WALKTHROUGH_1)
            this["ADMOB_NATIVE_WALKTHROUGH_2"] = remoteConfig.getString(ADMOB_NATIVE_WALKTHROUGH_2)
            this["ADMOB_NATIVE_WALKTHROUGH_FULLSCR"] = remoteConfig.getString(ADMOB_NATIVE_WALKTHROUGH_FULLSCR)
            this["ADMOB_NATIVE_WALKTHROUGH_3"] = remoteConfig.getString(ADMOB_NATIVE_WALKTHROUGH_3)
            this["ADMOB_INTERSTITIAL_LETS_START"] = remoteConfig.getString(ADMOB_INTERSTITIAL_LETS_START)

            Log.d("AdConfig", "===== Loaded Ad IDs =====")
            forEach { (key, value) ->
                Log.d("AdConfig", "$key = $value")
            }
            Log.d("AdConfig", "=========================")
        }
    }


    private fun fillAdIdsFromResources() {
        firstOpenFlowAdIds.apply {
            this["ADMOB_SPLASH_INTERSTITIAL"] = getString(R.string.ADMOB_SPLASH_INTERSTITIAL)
            this["ADMOB_SPLASH_RESUME"] = getString(R.string.ADMOB_SPLASH_RESUME)
            this["ADMOB_BANNER_SPLASH"] = getString(R.string.ADMOB_BANNER_SPLASH)
            this["ADMOB_NATIVE_LANGUAGE_1"] = getString(R.string.ADMOB_NATIVE_LANGUAGE_1)
            this["ADMOB_NATIVE_LANGUAGE_2"] = getString(R.string.ADMOB_NATIVE_LANGUAGE_2)
            this["ADMOB_NATIVE_SURVEY_1"] = getString(R.string.ADMOB_NATIVE_SURVEY_1)
            this["ADMOB_NATIVE_SURVEY_2"] = getString(R.string.ADMOB_NATIVE_SURVEY_2)
            this["ADMOB_NATIVE_WALKTHROUGH_1"] = getString(R.string.ADMOB_NATIVE_WALKTHROUGH_1)
            this["ADMOB_NATIVE_WALKTHROUGH_2"] = getString(R.string.ADMOB_NATIVE_WALKTHROUGH_2)
            this["ADMOB_NATIVE_WALKTHROUGH_FULLSCR"] = getString(R.string.ADMOB_NATIVE_WALKTHROUGH_FULLSCR)
            this["ADMOB_NATIVE_WALKTHROUGH_3"] = getString(R.string.ADMOB_NATIVE_WALKTHROUGH_3)
            this["ADMOB_INTERSTITIAL_LETS_START"] = getString(R.string.ADMOB_INTERSTITIAL_LETS_START)
        }
    }

    private fun checkAppUpdate(context: Context, onDone: () -> Unit) {
        val prefs = context.getSharedPreferences("AppPrefs", MODE_PRIVATE)
        val savedVersion = prefs.getInt("last_version_code", -1)
        val currentVersion = getCurrentVersionCode(context)

        if (currentVersion > savedVersion) {
            // app is update now show FistTeamFavSelection
            prefs.edit { putInt("last_version_code", currentVersion) }
            PrefHelper(this).putBoolean("StartScreens", value = false)
            onDone.invoke()
        } else {
            onDone.invoke()
        }
    }


    private fun getCurrentVersionCode(context: Context): Int {
        val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            packageInfo.longVersionCode.toInt()
        } else {
            packageInfo.versionCode
        }
    }
}