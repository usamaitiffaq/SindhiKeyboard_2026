package com.sindhi.urdu.english.keybad.sindhikeyboard.ui.fragments.DailyStatus.Retrofitcalls.viewmodel

import com.sindhi.urdu.english.keybad.sindhikeyboard.ui.fragments.DailyStatus.Retrofitcalls.models.dailystatusResponse
import com.sindhi.urdu.english.keybad.sindhikeyboard.ui.fragments.dictionary.models.dictionarywordresponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Dictionarydao {

    @GET("daily_urdu_qoutes.json")
    suspend fun getdailystatus(): Response<dailystatusResponse>

    @GET("/api/v2/entries/en/{word}")
    suspend fun getworddetails(@Path("word") word: String): Response<dictionarywordresponse>
}