package br.com.safecorp.data.repository

import br.com.safecorp.data.api.AssessmentApi
import br.com.safecorp.data.dao.AssessmentDao
import br.com.safecorp.data.model.AvaliacaoRequest
import br.com.safecorp.data.model.Respostas
import br.com.safecorp.models.Avaliacao
import java.util.Date

class AssessmentRepository(
    private val assessmentDao: AssessmentDao,
    private val assessmentApi: AssessmentApi
) {

    // Função para enviar avaliação com logs detalhados
    suspend fun submitAssessment(answers: List<String>, token: String): Avaliacao? {
        val request = AvaliacaoRequest(
            respostas = Respostas(
                pergunta1 = answers.getOrNull(0) ?: "",
                pergunta2 = answers.getOrNull(1) ?: "",
                pergunta3 = answers.getOrNull(2) ?: ""
            )
        )

        try {
            // Log do token e corpo da requisição
            println("=== submitAssessment ===")
            println("Token: $token")
            println("Request: $request")

            val response = assessmentApi.submitAssessment("Bearer $token", request)

            // Log da resposta
            println("Response: $response")
            return response

        } catch (e: retrofit2.HttpException) {
            // Erro HTTP (401, 404, 500, etc)
            println("HttpException ao enviar avaliação: ${e.code()} - ${e.message()}")
            e.printStackTrace()
        } catch (e: Exception) {
            // Qualquer outro erro
            println("Erro ao enviar avaliação: ${e.localizedMessage}")
            e.printStackTrace()
        }

        return null
    }

    // Função para buscar avaliações
    suspend fun getAssessments(token: String): List<Avaliacao> {
        return assessmentApi.getAssessments("Bearer $token")
    }
}
