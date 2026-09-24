package com.samir.product.domain.usecase

import com.samir.common.network.NetworkResult
import com.samir.model.Product
import com.samir.product.domain.repository.ProductDetailRepository
import jakarta.inject.Inject

class GetProductDetailsUseCase @Inject constructor(
    private val productDetailRepository: ProductDetailRepository
) {
    suspend operator fun invoke(
        productId: Int? = 0
    ): NetworkResult<Product> {
        if (productId == null || productId == 0) {
            return NetworkResult.Error("Product ID cannot be empty")
        }

        return productDetailRepository.getProductById(productId)
    }
}