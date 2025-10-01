package br.com.safecorp.data.api

import br.com.safecorp.models.Avaliacao
import kotlinx.coroutines.delay

class MockAssessmentApi : AssessmentApi {
    override suspend fun submitAssessment(token: String, avaliacao: Avaliacao): Avaliacao {
        delay(1000)

        // Simulação de lógica de avaliação
        val score = listOfNotNull(
            avaliacao.pergunta1,
            avaliacao.pergunta2,
            avaliacao.pergunta3
        ).count { it.equals("Sim", ignoreCase = true) }

        val suggestion = when {
            score <= 1 -> "Considere conversar com um supervisor ou RH para apoio."
            score == 2 -> "Algumas áreas de atenção. Tente técnicas de gestão de estresse."
            else -> "Você está bem! Continue mantendo hábitos saudáveis."
        }

        return avaliacao.copy(
            id = "mock123",
            date = avaliacao.date ?: "hoje"
        )
    }

    override suspend fun getAssessments(token: String): List<Avaliacao> {
        delay(500)
        return listOf(
            Avaliacao("Sim", "Não", "Talvez", "2025-10-01", "1"),
            Avaliacao("Não", "Não", "Sim", "2025-09-30", "2")
        )
    }
}
