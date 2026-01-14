package com.sindhi.urdu.english.keybad.sindhikeyboard.ui.fragments.Themes

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.AppCompatTextView
import androidx.compose.ui.platform.ComposeView
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.preference.PreferenceManager
import com.manual.mediation.library.sotadlib.utils.hideSystemUIUpdated
import com.sindhi.urdu.english.keybad.BuildConfig
import com.sindhi.urdu.english.keybad.R
import com.sindhi.urdu.english.keybad.databinding.FragmentThemesApplyBinding
import com.sindhi.urdu.english.keybad.sindhikeyboard.ads.ApplicationClass
import com.sindhi.urdu.english.keybad.sindhikeyboard.ads.InterstitialClassAdMob
import com.sindhi.urdu.english.keybad.sindhikeyboard.ads.NetworkCheck
import com.sindhi.urdu.english.keybad.sindhikeyboard.ads.NewNativeAdClass
import com.sindhi.urdu.english.keybad.sindhikeyboard.utils.blockingClickListener
import com.sindhi.urdu.english.keybad.sindhikeyboard.utils.safeNavigate
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.keyboardComponents.ApplyThemePreview
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.preferences.MyPreferences
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.preferences.Preferences
import com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.utilityClasses.CustomFirebaseEvents
import com.sindhi.urdu.english.keybad.sindhikeyboard.utils.PURCHASE
import com.sindhi.urdu.english.keybad.sindhikeyboard.utils.RemoteConfigConst.NATIVE_OVER_ALL
import com.sindhi.urdu.english.keybad.sindhikeyboard.utils.RemoteConfigConst.NATIVE_THEMES
import kotlinx.coroutines.launch

class ThemesApplyFragment : Fragment() {

    private lateinit var binding: FragmentThemesApplyBinding
    var isPurchased: Boolean? = null
    var navController: NavController? = null
    val argsThemes: ThemesApplyFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentThemesApplyBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isNavControllerAdded()

        val composeView = view.findViewById<ComposeView>(R.id.composeView)
        composeView.setContent {
            ApplicationClass.selectedTheme?.let {
                ApplyThemePreview(
                    ApplicationClass.selectedTheme!!.name,
                    ApplicationClass.selectedTheme!!.backgroundColor,
                    ApplicationClass.selectedTheme!!.backgroundColor2,
                    requireContext()
                )
            }
        }

        binding.tvApplyTheme.blockingClickListener {
            if (navController != null) {
                Toast.makeText(requireContext(),"Applying Theme...!", Toast.LENGTH_SHORT).show()
                ApplicationClass.selectedTheme?.let { theme ->
                    viewLifecycleOwner.lifecycleScope.launch {
                        MyPreferences(requireContext()).setTheme(theme)
                    }

                    if (NetworkCheck.isNetworkAvailable(context) && !(PreferenceManager.getDefaultSharedPreferences(requireActivity()).getBoolean(PURCHASE,false))
                        && requireActivity().getSharedPreferences("RemoteConfig", Context.MODE_PRIVATE).getString(Preferences.INTERSTITIAL_THEME_APPLIED,"ON").equals("ON",true)) {
                        if (navController != null) {
                            binding.tvApplyTheme.isClickable = false
                            InterstitialClassAdMob.showIfAvailableOrLoadAdMobInterstitial(
                                context = context,
                                nameFragment = "ThemesApplyFragment",
                                onAdClosedCallBackAdmob = {
                                    Handler(Looper.getMainLooper()).postDelayed({
                                        if (isAdded) {
                                            Toast.makeText(requireContext(),"Theme Applied...!", Toast.LENGTH_SHORT).show()
                                            val action = ThemesApplyFragmentDirections.actionThemesApplyFragmentToThemesTestFragment()
                                            navController?.safeNavigate(action)
                                        }
                                    },300)
                                },
                                onAdShowedCallBackAdmob = { })
                        } else {
                            isNavControllerAdded()
                        }
                    } else {
                        Toast.makeText(requireContext(),"Theme Applied...!", Toast.LENGTH_SHORT).show()
                        val action = ThemesApplyFragmentDirections.actionThemesApplyFragmentToThemesTestFragment()
                        navController?.safeNavigate(action)
                    }
                }
            } else {
                isNavControllerAdded()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                argsThemes.let {
                    if (navController != null) {
                        when (argsThemes.directFromFragment) {
                            "ExitScreenFragment" -> {
                                val action = ThemesApplyFragmentDirections.actionThemesApplyFragmentToNavHome()
                                navController?.safeNavigate(action)
                            }
                            "ThemesFragment" -> {
                                val action = ThemesApplyFragmentDirections.actionThemesApplyFragmentToThemesFragment("")
                                navController?.safeNavigate(action)
                            }
                            else -> {}
                        }
                    } else {
                        isNavControllerAdded()
                    }
                }
            }
        })
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
            txtSindhiKeyboard.text = resources.getString(R.string.label_apply_theme)

            val startDrawable = txtSindhiKeyboard.compoundDrawables[0]
            txtSindhiKeyboard.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                    if (event.x <= (startDrawable?.bounds?.width() ?: 0)) {
                        requireActivity().onBackPressed()
                        return@setOnTouchListener true
                    }
                }
                false
            }
        }

        CustomFirebaseEvents.activitiesFragmentEvent(requireActivity(), "ThemesApplyFragment")

        isPurchased = PreferenceManager.getDefaultSharedPreferences(requireContext()).getBoolean(PURCHASE, false)
        if (isPurchased == true) {
            binding.nativeAdContainerAd.visibility = View.GONE
        } else {
            if (NetworkCheck.isNetworkAvailable(requireContext())
                && requireActivity().getSharedPreferences("RemoteConfig", Context.MODE_PRIVATE).getString(Preferences.ADS_NATIVE_THEMES_APPLY,"ON").equals("ON",true)) {
                val pref =requireActivity().getSharedPreferences("RemoteConfig", MODE_PRIVATE)
                val adId  =if (!BuildConfig.DEBUG){
                    pref.getString(NATIVE_THEMES,"ca-app-pub-3747520410546258/6696428641")
                }
                else{
                    resources.getString(R.string.ADMOB_NATIVE_LANGUAGE_2)
                }
                    NewNativeAdClass.checkAdRequestAdmob(
                        mContext = requireActivity(),
                        adId = adId!!,//getString(R.string.admob_native),
                        fragmentName = "ThemesFragment",
                        isMedia = true,
                        isMediaOnLeft = true,
                        adContainer = binding.nativeAdContainerAd,
                        isMediumAd = true,
                        onFailed = {
                            binding.nativeAdContainerAd.visibility = View.GONE
                        },
                        onAddLoaded = {
                            binding.shimmerLayout.stopShimmer()
                            binding.shimmerLayout.visibility = View.GONE
                        })
            } else {
                binding.nativeAdContainerAd.visibility = View.GONE
            }
        }
    }

    fun isNavControllerAdded() {
        if (isAdded) {
            navController = findNavController()
        }
    }
}