package br.com.safecorp.network

import br.com.safecorp.models.Avaliacao
import br.com.safecorp.models.SelfCheck
import br.com.safecorp.models.Support
import br.com.safecorp.models.TokenResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    // 1️⃣ Auth - gerar token
    @POST("auth")
    fun loginAnonimo(): Call<TokenResponse>

    // 2️⃣ Avaliações
    @POST("avaliacoes")
    fun criarAvaliacao(
        @Body avaliacao: Avaliacao,
        @Header("Authorization") authHeader: String
    ): Call<Avaliacao>

    @GET("avaliacoes")
    fun listarAvaliacoes(
        @Header("Authorization") authHeader: String
    ): Call<List<Avaliacao>>

    // 3️⃣ Humor
    @POST("humor")
    fun registrarHumor(
        @Body humor: SelfCheck,
        @Header("Authorization") authHeader: String
    ): Call<SelfCheck>

    @GET("humor")
    fun listarHumor(
        @Header("Authorization") authHeader: String
    ): Call<List<SelfCheck>>

    // 4️⃣ Apoio (não precisa de token)
    @GET("apoio")
    fun listarApoio(): Call<List<Support>>
}
