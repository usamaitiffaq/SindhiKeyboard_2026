package com.sindhi.urdu.english.keybad.sindhikeyboard.stickers

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("content.json")
    suspend fun fetchStickers(): Response<StickerPackData>
}