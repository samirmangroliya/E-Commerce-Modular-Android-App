package com.samir.cart.domain

import com.samir.model.Product

data class CartUIState(
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)