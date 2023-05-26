package com.arturomarmolejo.amiiboappamcompose.rest

import com.arturomarmolejo.amiiboappamcompose.model.apimodel.AmiiboResponse
import retrofit2.Response
import retrofit2.http.GET

interface AmiiboServiceApi {

    @GET(AMIIBO)
    suspend fun getAmiiboList(): Response<AmiiboResponse>

    companion object {
        //https://www.amiiboapi.com/api/amiibo/
        const val BASE_URL = "https://www.amiiboapi.com/api/"
        const val AMIIBO = "amiibo/"

    }
}