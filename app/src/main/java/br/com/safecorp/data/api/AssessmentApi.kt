package br.com.safecorp.data.api

import br.com.safecorp.models.Avaliacao
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Header

interface AssessmentApi {

    @POST("avaliacoes")
    suspend fun submitAssessment(
        @Header("Authorization") token: String,
        @Body avaliacao: Avaliacao
    ): Avaliacao

    @GET("avaliacoes")
    suspend fun getAssessments(
        @Header("Authorization") token: String
    ): List<Avaliacao>
}
