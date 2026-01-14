package com.sindhi.urdu.english.keybad.sindhikeyboard.ui.fragments.dictionary

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object retrofitHelper {

    fun getInstance(Base_URL : String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Base_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}