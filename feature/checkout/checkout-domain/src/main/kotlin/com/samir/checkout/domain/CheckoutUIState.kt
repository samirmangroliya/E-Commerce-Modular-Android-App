package com.samir.checkout.domain

import com.samir.domain.ProductSortOrder
import com.samir.model.Product

data class CheckoutUIState(
    val products: List<Product> = emptyList(),
    val searchQuery: String = "",
    val sortOrder: ProductSortOrder = ProductSortOrder.RatingHighToLow,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)