package com.samir.cart.domain

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * Shared by BOTH home-mobile and home-tv. Neither Compose UI knows about
 * GetProductsUseCase, ProductRepository, or dummyjson — they only ever see this class.
 */
@HiltViewModel
class CartViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(CartUIState(isLoading = true))
    val uiState: StateFlow<CartUIState> = _uiState.asStateFlow()

    init {
        loadCart()
    }

    fun loadCart() {

    }
}