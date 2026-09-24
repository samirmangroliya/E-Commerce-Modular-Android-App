package com.samir.product.domain.repository

import com.samir.common.network.NetworkResult
import com.samir.model.Product

interface ProductDetailRepository {
    suspend fun getProductById(
        productId: Int
    ): NetworkResult<Product>
}