package com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.utilityClasses

import com.google.android.gms.ads.nativead.NativeAd

object AdObject {
    var nativeAdMobHashMapKeypad: HashMap<String, Pair<NativeAd, Long>>? = HashMap()
    var keyPadBannerFailedShowTime = 0L
}