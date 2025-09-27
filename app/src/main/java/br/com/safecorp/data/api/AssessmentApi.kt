package br.com.safecorp.data.api

import retrofit2.http.Body
import retrofit2.http.POST

data class AssessmentRequest(
    val answers: List<Int>
)

data class AssessmentResponse(
    val score: Int,
    val suggestion: String
)

interface AssessmentApi {
    @POST("assessment/submit")
    suspend fun submitAssessment(@Body request: AssessmentRequest): AssessmentResponse
} 