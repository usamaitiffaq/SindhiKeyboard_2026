package com.sindhi.urdu.english.keybad.sindhikeyboard.ui.fragments.DailyStatus.Retrofitcalls.models

import androidx.annotation.Keep

@Keep
data class dailystatusResponse(
    val bg: List<Bg>,
    val cat: List<Cat>,
    val cat_update: List<Cat_Update>,
    val quotes: List<Quote>
)