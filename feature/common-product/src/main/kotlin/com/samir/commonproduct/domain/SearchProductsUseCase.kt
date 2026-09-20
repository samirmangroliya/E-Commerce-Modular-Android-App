package com.samir.commonproduct.domain

import com.samir.common.network.NetworkResult
import com.samir.model.Product
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

class SearchProductsUseCase @Inject constructor(
    private val repository: ProductRepository,
) {
    val mutex = Mutex()
    suspend operator fun invoke(query: String): NetworkResult<List<Product>> {
        if (query.isBlank()) return NetworkResult.Success(emptyList())
        val result = mutex.withLock(owner = query) {
            repository.searchProducts(query.trim())
        }
        return result
    }
}