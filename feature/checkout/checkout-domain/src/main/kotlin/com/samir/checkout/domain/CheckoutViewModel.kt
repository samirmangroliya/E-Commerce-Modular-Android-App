package com.samir.checkout.domain

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class CheckoutViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow(CheckoutUIState(isLoading = true))
    val uiState: StateFlow<CheckoutUIState> = _uiState.asStateFlow()

    fun loadCheckout(){

    }
}