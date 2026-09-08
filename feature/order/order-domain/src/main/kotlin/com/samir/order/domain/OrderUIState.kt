package com.samir.order.domain

import com.samir.model.Product
import com.samir.domain.ProductSortOrder

data class OrderUIState(
    val products: List<Product> = emptyList(),
    val searchQuery: String = "",
    val sortOrder: ProductSortOrder = ProductSortOrder.RatingHighToLow,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)