package br.com.safecorp.models

import com.google.gson.annotations.SerializedName

data class Support(
    @SerializedName("_id") val id: String,
    val nome: String,
    val link: String
)
