package br.com.safecorp.data.model

data class SupportResource(
    val id: Int,
    val title: String,
    val description: String,
    val contactInfo: String,
    val type: String // e.g., "Crisis", "Counseling", "Emergency"
) 