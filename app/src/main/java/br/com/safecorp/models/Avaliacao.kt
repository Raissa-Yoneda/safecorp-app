package br.com.safecorp.models

data class Avaliacao(
    val pergunta1: String,
    val pergunta2: String,
    val pergunta3: String,
    val date: String? = null,
    val id: String? = null
)