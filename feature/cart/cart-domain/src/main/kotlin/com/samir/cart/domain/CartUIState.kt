package com.samir.cart.domain

import com.samir.model.Product
import com.samir.domain.ProductSortOrder

data class CartUIState(
    val products: List<Product> = emptyList(),
    val searchQuery: String = "",
    val sortOrder: ProductSortOrder = ProductSortOrder.RatingHighToLow,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)