package com.sindhi.urdu.english.keybad.sindhikeyboard.ui.fragments.DailyStatus.Retrofitcalls.models

import androidx.annotation.Keep

@Keep
data class Cat(
    val id: Int,
    val image: String,
    val name: String
)

@Keep
data class Cat_Update(
    val id: Int,
    val image: String,
    val name: String
)