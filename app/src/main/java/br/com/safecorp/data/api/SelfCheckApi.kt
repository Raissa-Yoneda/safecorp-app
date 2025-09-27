package br.com.safecorp.data.api

import retrofit2.http.Body
import retrofit2.http.POST

data class SelfCheckRequest(
    val mood: String,
    val note: String?
)

data class SelfCheckResponse(
    val success: Boolean,
    val message: String
)

interface SelfCheckApi {
    @POST("self-check/submit")
    suspend fun submitSelfCheck(@Body request: SelfCheckRequest): SelfCheckResponse
} 