package br.com.safecorp.data.api

import br.com.safecorp.data.model.AvaliacaoRequest
import br.com.safecorp.models.Avaliacao
import kotlinx.coroutines.delay

class MockAssessmentApi : AssessmentApi {

    override suspend fun submitAssessment(token: String, avaliacao: AvaliacaoRequest): Avaliacao {
        delay(1000) // simula tempo de rede

        // Simulação de lógica de avaliação: contar "Sim" nas respostas
        val score = listOfNotNull(
            avaliacao.respostas.pergunta1,
            avaliacao.respostas.pergunta2,
            avaliacao.respostas.pergunta3
        ).count { it.equals("Sim", ignoreCase = true) }

        val suggestion = when {
            score <= 1 -> "Considere conversar com um supervisor ou RH para apoio."
            score == 2 -> "Algumas áreas de atenção. Tente técnicas de gestão de estresse."
            else -> "Você está bem! Continue mantendo hábitos saudáveis."
        }

        // Retorna Avaliacao "fake" baseado no AvaliacaoRequest
        return Avaliacao(
            pergunta1 = avaliacao.respostas.pergunta1,
            pergunta2 = avaliacao.respostas.pergunta2,
            pergunta3 = avaliacao.respostas.pergunta3,
            date = "2025-10-03",
            id = "mock-id-123"
        )
    }

    override suspend fun getAssessments(token: String): List<Avaliacao> {
        delay(500) // simula tempo de rede

        return listOf(
            Avaliacao("Sim", "Não", "Talvez", "2025-10-01", "1"),
            Avaliacao("Não", "Não", "Sim", "2025-09-30", "2")
        )
    }
}
