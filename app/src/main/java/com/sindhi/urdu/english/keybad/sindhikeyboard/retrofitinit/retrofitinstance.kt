package com.sindhi.urdu.english.keybad.sindhikeyboard.retrofitinit

import android.content.Context
import com.sindhi.urdu.english.keybad.sindhikeyboard.utils.Networkutils.hasNetwork
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class retrofitinstance(cacheSize:Long,context:Context,val BASE_URL:String) {

    val myCache = Cache(context.cacheDir, cacheSize)
    val okHttpClient = OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .cache(myCache)
        .addInterceptor { chain ->
            var request = chain.request()
            val cacheExpired = (24 * 60 * 60 * 1000).toLong()
            request = if (hasNetwork(context))
                request.newBuilder().header("Cache-Control", "public, max-age=" + cacheExpired).build()
            else
                request.newBuilder().header("Cache-Control",
                    "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()

            chain.proceed(request)
        }
        .build()

    fun getinstance(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(
                okHttpClient
            ).build()
    }
}