package com.samir.order.domain

import com.samir.model.Product

data class OrderUIState(
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)