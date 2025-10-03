package br.com.safecorp.network

import br.com.safecorp.models.Avaliacao
import br.com.safecorp.models.SelfCheck
import br.com.safecorp.models.Support
import br.com.safecorp.models.TokenResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    // Auth: gera um token
    @POST("auth")
    fun loginAnonimo(): Call<TokenResponse>

    // Avaliações
    @POST("avaliacoes")
    fun criarAvaliacao(
        @Body avaliacao: Avaliacao,
        @Header("Authorization") authHeader: String
    ): Call<Avaliacao>

    @GET("avaliacoes")
    fun listarAvaliacoes(
        @Header("Authorization") authHeader: String
    ): Call<List<Avaliacao>>

    // Humor
    @POST("humor")
    fun registrarHumor(
        @Body humor: SelfCheck,
        @Header("Authorization") authHeader: String
    ): Call<SelfCheck>

    @GET("humor")
    fun listarHumor(
        @Header("Authorization") authHeader: String
    ): Call<List<SelfCheck>>

    // Apoio (esse não precisa de token)
    @GET("apoio")
    fun listarApoio(): Call<List<Support>>
}
