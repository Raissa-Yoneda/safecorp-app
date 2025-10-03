package br.com.safecorp.data.api

import br.com.safecorp.data.model.SupportResource
import retrofit2.http.GET
import retrofit2.http.Header

interface SupportApi {
    @GET("apoio")
    suspend fun getSupportResources(
        @Header("Authorization") authHeader: String
    ): List<SupportResource>
}
