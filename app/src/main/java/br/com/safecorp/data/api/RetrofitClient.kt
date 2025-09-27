package br.com.safecorp.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.safecorp.com/" // This is a mock URL

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val assessmentApi: AssessmentApi = retrofit.create(AssessmentApi::class.java)
    val selfCheckApi: SelfCheckApi = retrofit.create(SelfCheckApi::class.java)
    val supportApi: SupportApi = retrofit.create(SupportApi::class.java)
} 