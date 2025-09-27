package br.com.safecorp.network

import br.com.safecorp.models.Avaliacao
import br.com.safecorp.models.SelfCheck
import br.com.safecorp.models.Support
import br.com.safecorp.models.TokenResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

object RetrofitClient {

    private const val BASE_URL = "http://10.0.2.2:3000/api/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    val assessmentApi: AssessmentApi by lazy {
        retrofit.create(AssessmentApi::class.java)
    }

    val selfCheckApi: SelfCheckApi by lazy {
        retrofit.create(SelfCheckApi::class.java)
    }

    val supportApi: SupportApi by lazy {
        retrofit.create(SupportApi::class.java)
    }

    interface ApiService {
        @POST("auth")
        fun loginAnonimo(): Call<TokenResponse>
    }

    interface AssessmentApi {
        @POST("avaliacoes")
        fun salvarAvaliacao(
            @Header("Authorization") token: String,
            @Body body: Map<String, String>
        ): Call<Avaliacao>

        @GET("avaliacoes")
        fun listarAvaliacoes(@Header("Authorization") token: String): Call<List<Avaliacao>>
    }

    interface SelfCheckApi {
        @POST("humor")
        fun salvarHumor(
            @Header("Authorization") token: String,
            @Body body: Map<String, String>
        ): Call<SelfCheck>

        @GET("humor")
        fun listarHumor(@Header("Authorization") token: String): Call<List<SelfCheck>>
    }

    interface SupportApi {
        @GET("apoio")
        fun listarApoio(): Call<List<Support>>
    }
}
