package com.arturomarmolejo.amiiboappamcompose.model.apimodel


import com.google.gson.annotations.SerializedName

data class Release(
    @SerializedName("au")
    val au: String? = null,
    @SerializedName("eu")
    val eu: String? = null,
    @SerializedName("jp")
    val jp: String? = null,
    @SerializedName("na")
    val na: String? = null
)