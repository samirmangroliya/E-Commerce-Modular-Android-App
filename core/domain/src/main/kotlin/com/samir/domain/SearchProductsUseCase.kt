package com.samir.domain

import com.samir.common.network.NetworkResult
import com.samir.model.Product
import javax.inject.Inject

class SearchProductsUseCase @Inject constructor(
    private val repository: ProductRepository,
) {
    suspend operator fun invoke(query: String): NetworkResult<List<Product>> {
        if (query.isBlank()) return NetworkResult.Success(emptyList())
        return repository.searchProducts(query.trim())
    }
}