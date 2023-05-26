package com.arturomarmolejo.amiiboappamcompose.model.domain

import android.graphics.drawable.Icon
import com.arturomarmolejo.amiiboappamcompose.model.apimodel.Amiibo
import com.arturomarmolejo.amiiboappamcompose.model.apimodel.Release
import com.google.gson.Gson

data class AmiiboDomain(
    val amiiboSeries: String,
    val character: String,
    val gameSeries: String,
    val head: String,
    val image: String,
    val name: String,
    val release: Release,
    val tail: String,
    val type: String
)

fun List<Amiibo>?.toAmiiboDomain(): List<AmiiboDomain> =
    this?.map {
//        val releaseGson = Gson().toJson(it.release)
        AmiiboDomain(
            amiiboSeries = it.amiiboSeries ?: "",
            character = it.character ?: "",
            gameSeries = it.gameSeries ?: "",
            head = it.image ?: "",
            image = it.image ?: "",
            name = it.name ?: "",
            release = (it.release ?: Release(au = "", eu = "", jp = "", na = "")),
            tail = it.tail ?: "",
            type = it.type ?: ""
        )
    } ?: emptyList()
