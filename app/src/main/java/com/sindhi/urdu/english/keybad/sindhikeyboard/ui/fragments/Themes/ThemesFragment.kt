package com.sindhi.urdu.english.keybad.sindhikeyboard.ui.fragments.Themes

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.AppCompatTextView
import androidx.compose.ui.platform.ComposeView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.gms.ads.MobileAds
import com.manual.mediation.library.sotadlib.utils.hideSystemUIUpdated
import com.sindhi.urdu.english.keybad.BuildConfig
import com.sindhi.urdu.english.keybad.R
import com.sindhi.urdu.english.keybad.databinding.FragmentThemesBinding
import com.sindhi.urdu.english.keybad.sindhikeyboard.ads.ApplicationClass
import com.sindhi.urdu.english.keybad.sindhikeyboard.ads.ApplicationClass.Companion.selectedTheme
import com.sindhi.urdu.english.keybad.sindhikeyboard.ads.NetworkCheck
import com.sindhi.urdu.english.keybad.sindhikeyboard.ads.NewNativeAdClass
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.preferences.Preferences
import com.sindhi.urdu.english.keybad.sindhikeyboard.utils.PURCHASE
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.screens.ThemesScreen
import com.sindhi.urdu.english.keybad.sindhikeyboard.utils.RemoteConfigConst.NATIVE_THEMES
import com.sindhi.urdu.english.keybad.sindhikeyboard.utils.safeNavigate

class ThemesFragment : Fragment() {

    private lateinit var binding: FragmentThemesBinding
    var navController: NavController? = null
    var isPurchased: Boolean? = null
    var bundle = Bundle()
    val argsThemes: ThemesFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentThemesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isNavControllerAdded()

        val composeView = view.findViewById<ComposeView>(R.id.composeView)
        composeView.setContent {
            ThemesScreen(context = requireContext(), onApplyThemeClick = { theme ->
                selectedTheme = theme
                try {
                    if (navController != null) {
                        val action = ThemesFragmentDirections.actionThemesFragmentToThemesApplyFragment("ThemesFragment")
                        navController?.safeNavigate(action)
                    } else {
                        isNavControllerAdded()
                    }
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            })
        }
        if (BuildConfig.BUILD_TYPE == "debug"){
            MobileAds.openAdInspector(requireContext()) { error ->
                if (error != null) {
                    Log.e("AdInspector", "Error occurred: ${error.message}")
                } else {
                    Log.d("AdInspector", "Ad Inspector closed successfully")
                }
            }
        }
        else{
            MobileAds.openAdInspector(requireContext()) { error ->
                if (error != null) {
                    Log.e("AdInspector", "Error occurred: ${error.message}")
                } else {
                    Log.d("AdInspector", "Ad Inspector closed successfully")
                }
            }
        }
        bundle.putString("ThemesFragment", "ThemesFragment")
        ApplicationClass.firebaseAnalyticsEventsLog.logEvent("event_themes", bundle)

        isPurchased = PreferenceManager.getDefaultSharedPreferences(requireContext()).getBoolean(PURCHASE, false)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                myBackPressed()
            }
        })
    }

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
            txtSindhiKeyboard.text = resources.getString(R.string.label_themes)

            val startDrawable = txtSindhiKeyboard.compoundDrawables[0]
            txtSindhiKeyboard.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                    if (event.x <= (startDrawable?.bounds?.width() ?: 0)) {
                        myBackPressed()
                        return@setOnTouchListener true
                    }
                }
                false
            }
        }

        if (isPurchased!!) {
            binding.nativeAdContainerAd.visibility = View.GONE
            binding.separator.visibility = View.GONE
        } else {
            if (NetworkCheck.isNetworkAvailable(requireContext())
                && requireActivity().getSharedPreferences("RemoteConfig", Context.MODE_PRIVATE).getString(
                    Preferences.ADS_NATIVE_THEMES,"ON").equals("ON",true)) {
                val pref =requireActivity().getSharedPreferences("RemoteConfig", MODE_PRIVATE)
                val adId  =if (!BuildConfig.DEBUG){
                    pref.getString(NATIVE_THEMES,"ca-app-pub-3747520410546258/6696428641")
                }
                else{
                    resources.getString(R.string.ADMOB_NATIVE_LANGUAGE_2)
                }
                NewNativeAdClass.checkAdRequestAdmob(
                    mContext = requireActivity(),
                    adId = adId!!,
                    fragmentName = "ThemesFragment",
                    isMedia = false,
                    adContainer = binding.nativeAdContainerAd,
                    isMediumAd = false,
                    onFailed = {
                        binding.nativeAdContainerAd.visibility = View.GONE
                        binding.separator.visibility = View.GONE
                    },
                    onAddLoaded = {
                        binding.shimmerLayout.stopShimmer()
                        binding.shimmerLayout.visibility = View.GONE
                        binding.separator.visibility = View.VISIBLE

                    })
            } else {
                binding.nativeAdContainerAd.visibility = View.GONE
                binding.separator.visibility = View.GONE
            }
        }
    }

    private fun myBackPressed() {
        argsThemes.let {
            if (navController != null) {
                argsThemes.let {
                    if (navController != null) {
                        when (argsThemes.fromFragment) {
                            "FromHome", "FromExploreFeature" -> {
                                val action = ThemesFragmentDirections.actionThemesFragmentToNavHome()
                                navController?.safeNavigate(action)
                            }
                            "SeeMore" -> {
                                val action = ThemesFragmentDirections.actionThemesFragmentToExitScreenFragment()
                                navController?.safeNavigate(action)
                            }
                            else -> {
                                val action = ThemesFragmentDirections.actionThemesFragmentToNavHome()
                                navController?.safeNavigate(action)
                            }
                        }
                    } else {
                        isNavControllerAdded()
                    }
            }
            }
        }
    }

    fun isNavControllerAdded() {
        if (isAdded) {
            navController = findNavController()
        }
    }
}
