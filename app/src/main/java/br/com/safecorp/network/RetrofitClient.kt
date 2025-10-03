package br.com.safecorp.network

import br.com.safecorp.data.api.AssessmentApi
import br.com.safecorp.data.api.SelfCheckApi
import br.com.safecorp.data.api.SupportApi
import br.com.safecorp.models.TokenResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST

object RetrofitClient {

    private const val BASE_URL = "http://10.0.2.2:3000/api/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Serviços criados
    val assessmentApi: AssessmentApi by lazy {
        retrofit.create(AssessmentApi::class.java)
    }

    val selfCheckApi: SelfCheckApi by lazy {
        retrofit.create(SelfCheckApi::class.java)
    }

    val supportApi: SupportApi by lazy {
        retrofit.create(SupportApi::class.java)
    }

    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    interface ApiService {
        @POST("auth")
        fun loginAnonimo(): Call<TokenResponse>
    }
}
