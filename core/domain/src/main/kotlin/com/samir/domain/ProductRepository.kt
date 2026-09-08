package com.samir.domain

import com.samir.common.network.NetworkResult
import com.samir.model.Product

interface ProductRepository {
    suspend fun getProducts(limit: Int = 20, skip: Int = 0): NetworkResult<List<Product>>
    suspend fun getProductDetail(id: Int): NetworkResult<Product>
    suspend fun searchProducts(query: String): NetworkResult<List<Product>>
}