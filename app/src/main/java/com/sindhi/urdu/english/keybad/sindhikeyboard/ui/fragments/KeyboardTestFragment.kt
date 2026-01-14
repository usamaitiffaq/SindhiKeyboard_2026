package com.sindhi.urdu.english.keybad.sindhikeyboard.ui.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.firebase.analytics.FirebaseAnalytics.Event.PURCHASE
import com.manual.mediation.library.sotadlib.utils.hideSystemUIUpdated
import com.sindhi.urdu.english.keybad.BuildConfig
import com.sindhi.urdu.english.keybad.R
import com.sindhi.urdu.english.keybad.databinding.FragmentKeyboardTestBinding
import com.sindhi.urdu.english.keybad.sindhikeyboard.ads.NativeMaster
import com.sindhi.urdu.english.keybad.sindhikeyboard.ads.NetworkCheck.Companion.isNetworkAvailable
import com.sindhi.urdu.english.keybad.sindhikeyboard.ads.NewNativeAdClass
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.preferences.Preferences
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.utilityClasses.CustomFirebaseEvents
import com.sindhi.urdu.english.keybad.sindhikeyboard.utils.RemoteConfigConst.ADS_BANNER_THEMES_TEST
import com.sindhi.urdu.english.keybad.sindhikeyboard.utils.RemoteConfigConst.BANNER_INSIDE
import com.sindhi.urdu.english.keybad.sindhikeyboard.utils.RemoteConfigConst.NATIVE_THEMES

class KeyboardTestFragment : Fragment() {
    private lateinit var binding: FragmentKeyboardTestBinding
    var isPurchased: Boolean? = null
    var navController: NavController? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentKeyboardTestBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isNavControllerAdded()
        checkForLoadBanner()
        binding.etTestKeyboard.apply {
            isFocusableInTouchMode = true
            requestFocus()
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    // Check if our custom logic ran
                    val didCustomAction = performCustomNavigation()

                    if (!didCustomAction) {
                        // Custom logic did not run.
                        // Disable this callback and let the system handle the back press.
                        isEnabled = false
                        requireActivity().onBackPressedDispatcher.onBackPressed()
                    }
                    // If didCustomAction was true, we do nothing else.
                }
            }
        )
    }

    fun isNavControllerAdded() {
        if (isAdded) {
            navController = findNavController()
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onResume() {
        super.onResume()
        requireActivity().hideSystemUIUpdated()
        isNavControllerAdded()

        val ivClose = requireActivity().findViewById<ImageView>(R.id.ivClose)
        if (ivClose != null) {
            ivClose.visibility = View.INVISIBLE
        }

        val txtSindhiKeyboard = requireActivity().findViewById<AppCompatTextView>(R.id.txtSindhiKeyboard)
        if (txtSindhiKeyboard != null) {
            txtSindhiKeyboard.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(requireContext(), R.drawable.back),null,null,null)
            txtSindhiKeyboard.text = resources.getString(R.string.label_test_theme)

            val startDrawable = txtSindhiKeyboard.compoundDrawables[0]

            // CORRECTED Toolbar Back Button Touch Listener
            txtSindhiKeyboard.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                    if (event.x <= (startDrawable?.bounds?.width() ?: 0)) {
                        // Check if our custom logic runs
                        val didCustomAction = performCustomNavigation()

                        if (!didCustomAction) {
                            // If no custom action, just perform a normal back press
                            requireActivity().onBackPressedDispatcher.onBackPressed()
                        }
                        return@setOnTouchListener true
                    }
                }
                false
            }
        }

        CustomFirebaseEvents.activitiesFragmentEvent(requireActivity(), "KeyboardTestFragment")

        val pref = requireActivity().getSharedPreferences("RemoteConfig", Context.MODE_PRIVATE)
        val adId  =if (!BuildConfig.DEBUG){
            pref.getString(NATIVE_THEMES,"ca-app-pub-3747520410546258/6696428641")
        }
        else{
            resources.getString(R.string.ADMOB_NATIVE_LANGUAGE_2)
        }

        isPurchased = PreferenceManager.getDefaultSharedPreferences(requireContext()).getBoolean(PURCHASE, false)
        if (isPurchased == true) {
            binding.nativeAdContainerAd1.visibility = View.GONE
        } else {
            if (isNetworkAvailable(requireContext())
                && requireActivity().getSharedPreferences("RemoteConfig", Context.MODE_PRIVATE).getString(
                    Preferences.ADS_NATIVE_THEMES_APPLIED_TEST,"ON").equals("ON",true)) {
                NewNativeAdClass.checkAdRequestAdmob(
                    mContext = requireActivity(),
                    adId = adId!!,
                    fragmentName = "keybordtest",
                    isMedia = true,
                    isMediaOnLeft = true,
                    adContainer = binding.nativeAdContainerAd1,
                    isMediumAd = true,
                    onFailed = {
                        binding.nativeAdContainerAd1.visibility = View.GONE
                    },
                    onAddLoaded = {
                        binding.shimmerLayout.stopShimmer()
                        binding.shimmerLayout.visibility = View.GONE
                    })
            } else {
                binding.nativeAdContainerAd1.visibility = View.GONE
            }
        }
    }

    private fun checkForLoadBanner() {
        if (isNetworkAvailable(requireContext()) && requireActivity().getSharedPreferences(
                "RemoteConfig",
                Context.MODE_PRIVATE
            ).getString(
                ADS_BANNER_THEMES_TEST, "ON"
            ).equals("ON", true)
        ) {
            if (NativeMaster.collapsibleBannerAdMobHashMap!!.containsKey("kettest")) {
                val collapsibleAdView: AdView? =
                    NativeMaster.collapsibleBannerAdMobHashMap!!["kettest"]
                Handler().postDelayed({
                    binding.shimmerLayout1.stopShimmer()
                    binding.shimmerLayout1.visibility = View.GONE
                    binding.separator.visibility = View.VISIBLE
                    binding.adViewContainer.removeView(binding.shimmerLayout1)

                    val parent = collapsibleAdView?.parent as? ViewGroup
                    parent?.removeView(collapsibleAdView)

                    binding.adViewContainer.addView(collapsibleAdView)
                }, 500)
            } else {
                loadBanner()
            }
        } else {
            binding.adViewContainer.visibility = View.GONE
            binding.shimmerLayout1.stopShimmer()
            binding.shimmerLayout1.visibility = View.GONE
            binding.separator.visibility = View.GONE
        }
    }

    private val adSize: AdSize
        get() {
            val display = requireActivity().windowManager.defaultDisplay
            val outMetrics = DisplayMetrics()
            display.getMetrics(outMetrics)

            val density = outMetrics.density

            var adWidthPixels = binding.adViewContainer.width.toFloat()
            if (adWidthPixels == 0f) {
                adWidthPixels = outMetrics.widthPixels.toFloat()
            }

            val adWidth = (adWidthPixels / density).toInt()
            return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(requireContext(), adWidth)
        }


    /**
     * This is the single, safe helper function to handle your custom back logic.
     * Returns true if a custom action was performed, false otherwise.
     */
    private fun performCustomNavigation(): Boolean {
        isNavControllerAdded() // Ensure navController is set
        navController?.let { nc ->
            val currentDestination = nc.currentDestination?.id
            if (currentDestination == R.id.themes_test_fragment) {
                // We have a custom action, perform it
                val action = KeyboardTestFragmentDirections.actionThemesTestFragmentToNavHome()
                nc.navigate(action)
                return true // We handled the back press
            }
        }
        return false // We did NOT handle the back press
    }


    private fun loadBanner() {
        val pref = requireActivity().getSharedPreferences("RemoteConfig", Context.MODE_PRIVATE)
        val adId  =if (!BuildConfig.DEBUG){
            pref.getString(BANNER_INSIDE,"ca-app-pub-3747520410546258/1697692330")
        }
        else{
            resources.getString(R.string.ADMOB_BANNER_SPLASH)
        }

        val adView = AdView(requireActivity())
        adView.setAdSize(adSize)
        adView.adUnitId =adId!!
        val adRequest = AdRequest.Builder().build()

        adView.loadAd(adRequest)
        adView.adListener = object : AdListener() {
            override fun onAdLoaded() {
                binding.separator.visibility = View.VISIBLE
                binding.adViewContainer.removeAllViews()
                binding.adViewContainer.addView(adView)
                NativeMaster.collapsibleBannerAdMobHashMap!!["kettest"] = adView
                Log.d("jdjasjjsa", "onAdLoaded: ")

            }

            override fun onAdFailedToLoad(error: LoadAdError) {
                binding.separator.visibility = View.GONE

            }

            override fun onAdOpened() {
                Log.d("jdjasjjsa", "onAdOpened: ")

            }

            override fun onAdClicked() {

            }

            override fun onAdClosed() {

            }
        }
    }
}

//class KeyboardTestFragment : Fragment() {
//
//    private lateinit var binding: FragmentKeyboardTestBinding
//    var isPurchased: Boolean? = null
//    var navController: NavController? = null
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
//        binding = FragmentKeyboardTestBinding.inflate(layoutInflater)
//        return binding.root
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
//    }
//
//    @SuppressLint("ClickableViewAccessibility")
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        isNavControllerAdded()
//        checkForLoadBanner()
//        binding.etTestKeyboard.apply {
//            isFocusableInTouchMode = true
//            requestFocus()
//            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//            imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
//        }
//
//        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
//            object : OnBackPressedCallback(true) {
//                override fun handleOnBackPressed() {
//                    // Directly handle here instead of calling myBackPressed() recursively
//                    handleCustomBackPressed(this)
//                }
//            }
//        )
//    }
//
//    fun isNavControllerAdded() {
//        if (isAdded) {
//            navController = findNavController()
//        }
//    }
//
//    @SuppressLint("ClickableViewAccessibility")
//    override fun onResume() {
//        super.onResume()
//        requireActivity().hideSystemUIUpdated()
//        isNavControllerAdded()
//
//        val ivClose = requireActivity().findViewById<ImageView>(R.id.ivClose)
//        if (ivClose != null) {
//            ivClose.visibility = View.INVISIBLE
//        }
//
//        val txtSindhiKeyboard = requireActivity().findViewById<AppCompatTextView>(R.id.txtSindhiKeyboard)
//        if (txtSindhiKeyboard != null) {
//            txtSindhiKeyboard.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(requireContext(), R.drawable.back),null,null,null)
//            txtSindhiKeyboard.text = resources.getString(R.string.label_test_theme)
//
//            val startDrawable = txtSindhiKeyboard.compoundDrawables[0]
//            // Inside your onResume() function
//
//            txtSindhiKeyboard.setOnTouchListener { _, event ->
//                if (event.action == MotionEvent.ACTION_DOWN) {
//                    if (event.x <= (startDrawable?.bounds?.width() ?: 0)) {
//
//                        // --- THIS IS THE FIX ---
//                        // Check if our custom logic runs
//                        val didCustomAction = performCustomNavigation()
//
//                        if (!didCustomAction) {
//                            // If no custom action, just perform a normal back press
//                            requireActivity().onBackPressedDispatcher.onBackPressed()
//                        }
//                        return@setOnTouchListener true
//                        // --- END OF FIX ---
//
//                    }
//                }
//                false
//            }
//        }
//
//        CustomFirebaseEvents.activitiesFragmentEvent(requireActivity(), "KeyboardTestFragment")
//
//        val pref =requireActivity().getSharedPreferences("RemoteConfig", MODE_PRIVATE)
//        val adId  =if (!BuildConfig.DEBUG){
//            pref.getString(NATIVE_THEMES,"ca-app-pub-3747520410546258/6696428641")
//        }
//        else{
//            resources.getString(R.string.ADMOB_NATIVE_LANGUAGE_2)
//        }
//
//        isPurchased = PreferenceManager.getDefaultSharedPreferences(requireContext()).getBoolean(PURCHASE, false)
//        if (isPurchased!!) {
//            binding.nativeAdContainerAd1.visibility = View.GONE
//        } else {
//            if (isNetworkAvailable(requireContext())
//                && requireActivity().getSharedPreferences("RemoteConfig", Context.MODE_PRIVATE).getString(
//                    Preferences.ADS_NATIVE_THEMES_APPLIED_TEST,"ON").equals("ON",true)) {
//                NewNativeAdClass.checkAdRequestAdmob(
//                    mContext = requireActivity(),
//                    adId = adId!!,
//                    fragmentName = "keybordtest",
//                    isMedia = true,
//                    isMediaOnLeft = true,
//                    adContainer = binding.nativeAdContainerAd1,
//                    isMediumAd = true,
//                    onFailed = {
//                        binding.nativeAdContainerAd1.visibility = View.GONE
//                    },
//                    onAddLoaded = {
//                        binding.shimmerLayout.stopShimmer()
//                        binding.shimmerLayout.visibility = View.GONE
//                    })
//            } else {
//                binding.nativeAdContainerAd1.visibility = View.GONE
//            }
//        }
//    }
//    private fun checkForLoadBanner() {
//        if (isNetworkAvailable(requireContext()) && requireActivity().getSharedPreferences(
//                "RemoteConfig",
//                Context.MODE_PRIVATE
//            ).getString(
//                ADS_BANNER_THEMES_TEST, "ON"
//            ).equals("ON", true)
//        ) {
//            if (NativeMaster.collapsibleBannerAdMobHashMap!!.containsKey("kettest")) {
//                val collapsibleAdView: AdView? =
//                    NativeMaster.collapsibleBannerAdMobHashMap!!["kettest"]
//                Handler().postDelayed({
//                    binding.shimmerLayout1.stopShimmer()
//                    binding.shimmerLayout1.visibility = View.GONE
//                    binding.separator.visibility = View.VISIBLE
//                    binding.adViewContainer.removeView(binding.shimmerLayout1)
//
//                    val parent = collapsibleAdView?.parent as? ViewGroup
//                    parent?.removeView(collapsibleAdView)
//
//                    binding.adViewContainer.addView(collapsibleAdView)
//                }, 500)
//            } else {
//                loadBanner()
//            }
//        } else {
//            binding.adViewContainer.visibility = View.GONE
//            binding.shimmerLayout1.stopShimmer()
//            binding.shimmerLayout1.visibility = View.GONE
//            binding.separator.visibility = View.GONE
//        }
//    }
//    /*private val adSize: AdSize
//        get() = AdSize.BANNER*/
//    private val adSize: AdSize
//        get() {
//            val display = requireActivity().windowManager.defaultDisplay
//            val outMetrics = DisplayMetrics()
//            display.getMetrics(outMetrics)
//
//            val density = outMetrics.density
//
//            var adWidthPixels = binding.adViewContainer.width.toFloat()
//            if (adWidthPixels == 0f) {
//                adWidthPixels = outMetrics.widthPixels.toFloat()
//            }
//
//            val adWidth = (adWidthPixels / density).toInt()
//            return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(requireContext(), adWidth)
//        }
//
//
//
//    private fun handleCustomBackPressed(callback: OnBackPressedCallback) {
//        navController?.let { navController ->
//            val currentDestination = navController.currentDestination?.id
//            if (currentDestination == R.id.themes_test_fragment) {
//                val action = KeyboardTestFragmentDirections.actionThemesTestFragmentToNavHome()
//                navController.navigate(action)
//            } else {
//                // Disable callback before delegating to system
//                callback.isEnabled = false
//                requireActivity().onBackPressedDispatcher.onBackPressed()
//            }
//        } ?: run {
//            isNavControllerAdded()
//        }
//    }
//
//
//
//    private fun loadBanner() {
//        val pref =requireActivity().getSharedPreferences("RemoteConfig", MODE_PRIVATE)
//        val adId  =if (!BuildConfig.DEBUG){
//            pref.getString(BANNER_INSIDE,"ca-app-pub-3747520410546258/1697692330")
//        }
//        else{
//            resources.getString(R.string.ADMOB_BANNER_SPLASH)
//        }
//
//        val adView = AdView(requireActivity())
//        adView.setAdSize(adSize)
//        adView.adUnitId =adId!!
//        val adRequest = AdRequest.Builder().build()
//
//        adView.loadAd(adRequest)
//        adView.adListener = object : AdListener() {
//            override fun onAdLoaded() {
//                binding.separator.visibility = View.VISIBLE
//                binding.adViewContainer.removeAllViews()
//                binding.adViewContainer.addView(adView)
//                NativeMaster.collapsibleBannerAdMobHashMap!!["kettest"] = adView
//                Log.d("jdjasjjsa", "onAdLoaded: ")
//
//            }
//
//            override fun onAdFailedToLoad(error: LoadAdError) {
//                binding.separator.visibility = View.GONE
//
//            }
//
//            override fun onAdOpened() {
//                Log.d("jdjasjjsa", "onAdOpened: ")
//
//            }
//
//            override fun onAdClicked() {
//
//            }
//
//            override fun onAdClosed() {
//
//            }
//        }
//    }
//}
