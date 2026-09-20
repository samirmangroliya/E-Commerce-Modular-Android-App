package com.samir.checkout.domain

import com.samir.model.Product

data class CheckoutUIState(
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)