package com.vasilv.traffic_light.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state =
        _state.map { it.copy(isFormValid = validateForm(it)) }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), _state.value)

    fun onCarModelChange(value: String) {
        _state.update { it.copy(carModel = value) }
    }

    fun onStartDriving() {

    }

    private fun validateForm(state: HomeState): Boolean {
        return state.carModel.length > 3
    }
}