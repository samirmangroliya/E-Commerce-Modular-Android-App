package com.samir.order.domain

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow(OrderUIState(isLoading = true))
    val uiState: StateFlow<OrderUIState> = _uiState.asStateFlow()

    init {
        loadOrders()
    }

    fun loadOrders() {

    }
}