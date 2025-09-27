package br.com.safecorp.data.api

import br.com.safecorp.data.model.SupportResource
import retrofit2.http.GET

interface SupportApi {
    @GET("support-resources")
    suspend fun getSupportResources(): List<SupportResource>
} 