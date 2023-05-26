package com.arturomarmolejo.amiiboappamcompose.rest

import com.arturomarmolejo.amiiboappamcompose.model.domain.AmiiboDomain
import com.arturomarmolejo.amiiboappamcompose.model.domain.toAmiiboDomain
import com.arturomarmolejo.amiiboappamcompose.utils.UIState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface AmiiboRepository {
    fun getAmiiboList(): Flow<UIState<List<AmiiboDomain>>>
}

class AmiiboRepositoryImpl @Inject constructor(
    private val amiiboServiceApi: AmiiboServiceApi,
    private val ioDispatcher: CoroutineDispatcher
): AmiiboRepository {
    override fun getAmiiboList(): Flow<UIState<List<AmiiboDomain>>> = flow {
        emit(UIState.LOADING)
        try {
            val response = amiiboServiceApi.getAmiiboList()
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(UIState.SUCCESS(it.amiibo.toAmiiboDomain()))
                } ?: throw Exception("Response body is null")
            } else {
                throw Exception(response.errorBody()?.string())
            }
        } catch (error: Exception) {
            emit(UIState.ERROR(error))
        }
    }.flowOn(ioDispatcher)
}