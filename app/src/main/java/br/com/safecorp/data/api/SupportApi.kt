// SupportApi.kt
package br.com.safecorp.data.api

import br.com.safecorp.data.model.SupportResource
import retrofit2.http.GET

interface SupportApi {
    @GET("apoio")
    suspend fun getSupportResources(): List<SupportResource>
}
