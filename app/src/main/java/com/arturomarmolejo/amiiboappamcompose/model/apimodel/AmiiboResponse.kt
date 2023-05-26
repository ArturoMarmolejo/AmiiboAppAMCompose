package com.arturomarmolejo.amiiboappamcompose.model.apimodel


import com.google.gson.annotations.SerializedName

data class AmiiboResponse(
    @SerializedName("amiibo")
    val amiibo: List<Amiibo> = listOf()
)