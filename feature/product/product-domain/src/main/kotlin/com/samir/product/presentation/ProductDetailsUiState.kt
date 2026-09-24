package com.samir.product.presentation

import com.samir.model.Product
import javax.annotation.concurrent.Immutable

@Immutable
data class ProductDetailsUiState(
    val isLoading: Boolean = false,
    val product: Product? = null,
    val errorMessage: String? = null
)