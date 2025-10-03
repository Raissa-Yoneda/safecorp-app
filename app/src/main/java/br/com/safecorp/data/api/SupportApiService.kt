package br.com.safecorp.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SupportApiService {
    private const val BASE_URL = "http://10.0.2.2:3000/" // Para emulador Android

    val api: SupportApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SupportApi::class.java)
    }
}
