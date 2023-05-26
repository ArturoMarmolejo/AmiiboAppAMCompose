package com.arturomarmolejo.amiiboappamcompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arturomarmolejo.amiiboappamcompose.model.apimodel.Amiibo
import com.arturomarmolejo.amiiboappamcompose.model.domain.AmiiboDomain
import com.arturomarmolejo.amiiboappamcompose.rest.AmiiboRepository
import com.arturomarmolejo.amiiboappamcompose.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AmiiboViewModel @Inject constructor(
    private val amiiboRepository: AmiiboRepository
): ViewModel() {
    var selectedAmiibo: AmiiboDomain? = null

    private val _amiiboList: MutableStateFlow<UIState<List<AmiiboDomain>>> =
        MutableStateFlow(UIState.LOADING)
    val amiiboList: StateFlow<UIState<List<AmiiboDomain>>> get() = _amiiboList

    init {
        getItems()
    }

    fun getItems() {
        viewModelScope.launch {
            amiiboRepository.getAmiiboList().collect{
                _amiiboList.value = it
            }
        }
    }

}