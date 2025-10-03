
package br.com.safecorp.data.repository

import br.com.safecorp.data.dao.AssessmentDao
import br.com.safecorp.data.api.AssessmentApi
import br.com.safecorp.models.Avaliacao
import java.util.Date

class AssessmentRepository(
    private val assessmentDao: AssessmentDao,
    private val assessmentApi: AssessmentApi
) {

    suspend fun submitAssessment(answers: List<String>, token: String): Avaliacao {
        val avaliacao = Avaliacao(
            pergunta1 = answers.getOrNull(0) ?: "",
            pergunta2 = answers.getOrNull(1) ?: "",
            pergunta3 = answers.getOrNull(2) ?: "",
            date = Date().toString()
        )

        // Chamada suspensa para API
        val saved = assessmentApi.submitAssessment("Bearer $token", avaliacao)

        return saved
    }

    suspend fun getAssessments(token: String): List<Avaliacao> {
        return assessmentApi.getAssessments("Bearer $token")
    }
}
