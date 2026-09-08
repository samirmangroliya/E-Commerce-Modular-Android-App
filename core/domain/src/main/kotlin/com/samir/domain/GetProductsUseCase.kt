package com.samir.domain

import javax.inject.Inject
import com.samir.common.network.NetworkResult
import com.samir.model.Product

class GetProductsUseCase @Inject constructor(
    private val repository: ProductRepository,
) {
    suspend operator fun invoke(limit: Int = 20, skip: Int = 0): NetworkResult<List<Product>> =
        repository.getProducts(limit, skip)
}

