package com.samir.domain

import com.samir.common.network.NetworkResult
import com.samir.model.Product
import javax.inject.Inject

class GetProductDetailUseCase @Inject constructor(
    private val repository: ProductRepository,
) {
    suspend operator fun invoke(productId: Int): NetworkResult<Product> =
        repository.getProductDetail(productId)
}