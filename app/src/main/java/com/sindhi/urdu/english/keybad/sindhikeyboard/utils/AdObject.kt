package com.sindhi.urdu.english.keybad.sindhikeyboard.utils

import com.google.android.gms.ads.nativead.NativeAd
import com.mbridge.msdk.out.MBBannerView

object AdObject {
    var nativeAdMobHashMapKeypad: HashMap<String, Pair<NativeAd, Long>>? = HashMap()
    var nativeMintegralHashMapKeypad: HashMap<String, Pair<MBBannerView, Long>>? = HashMap()

    var keyPadBannerFailedShowTimeAdmob = 0L
    var keyPadBannerFailedShowTimeMintegral = 0L
}