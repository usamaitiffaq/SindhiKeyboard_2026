package com.sindhi.urdu.english.keybad.sindhikeyboard.stickers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class StickerRepository {
    private val apiService: ApiService
    init {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()
        //solutionoftechnologies.com/sindhi_stickers/content.json
        val retrofit = Retrofit.Builder()
            .baseUrl("https://solutionoftechnologies.com/sindhi_stickers/")
            .client(okHttpClient)
//            .client(provideOkHttpClient(context))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)
    }

    suspend fun getStickers(): StickerPackData? {
        return try {
            val response = apiService.fetchStickers()
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}