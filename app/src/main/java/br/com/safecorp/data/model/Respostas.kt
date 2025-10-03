package br.com.safecorp.data.model

data class Respostas(
    val pergunta1: String,
    val pergunta2: String,
    val pergunta3: String
)

data class AvaliacaoRequest(
    val respostas: Respostas
)
