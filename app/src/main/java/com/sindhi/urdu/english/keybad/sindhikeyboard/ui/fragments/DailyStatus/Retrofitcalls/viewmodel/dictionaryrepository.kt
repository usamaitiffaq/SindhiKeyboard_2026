package com.sindhi.urdu.english.keybad.sindhikeyboard.ui.fragments.DailyStatus.Retrofitcalls.viewmodel

import com.sindhi.urdu.english.keybad.sindhikeyboard.ui.fragments.DailyStatus.Retrofitcalls.models.dailystatusResponse
import com.sindhi.urdu.english.keybad.sindhikeyboard.ui.fragments.dictionary.models.dictionarywordresponse
import retrofit2.Response

class dictionaryrepository(val dictionarydao: Dictionarydao) {

    suspend fun getdailystatus(): Response<dailystatusResponse> {
        return dictionarydao.getdailystatus()
    }

    suspend fun getwordsdetails(word: String): Response<dictionarywordresponse> {
        return dictionarydao.getworddetails(word)
    }
}