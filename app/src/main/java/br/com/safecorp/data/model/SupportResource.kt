package br.com.safecorp.data.model

import com.google.gson.annotations.SerializedName

data class SupportResource(
    @SerializedName("_id")
    val id: String,
    @SerializedName("nome")
    val title: String,
    @SerializedName("link")
    val contactInfo: String?
)
