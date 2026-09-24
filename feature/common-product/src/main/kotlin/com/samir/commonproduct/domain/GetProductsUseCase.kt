package com.samir.commonproduct.domain

import com.samir.common.network.NetworkResult
import com.samir.model.Product
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: ProductRepository,
) {
    suspend operator fun invoke(limit: Int = 20, skip: Int = 0): NetworkResult<List<Product>> =
        repository.getProducts(limit, skip)
}

