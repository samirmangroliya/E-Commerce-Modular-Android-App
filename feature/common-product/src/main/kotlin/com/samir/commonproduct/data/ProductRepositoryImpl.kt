package com.samir.commonproduct.data

import com.samir.common.network.NetworkResult
import com.samir.common.network.safeApiCall
import com.samir.commonproduct.domain.ProductRepository
import com.samir.model.Product
import javax.inject.Inject


class ProductRepositoryImpl @Inject constructor(
    private val api: ProductApi,
) : ProductRepository {

    override suspend fun getProducts(limit: Int, skip: Int): NetworkResult<List<Product>> =
        safeApiCall { api.getProducts(limit, skip).products.map { it.toDomain() } }

    override suspend fun getProductDetail(id: Int): NetworkResult<Product> =
        safeApiCall { api.getProductDetail(id) }

    override suspend fun searchProducts(query: String): NetworkResult<List<Product>> =
        safeApiCall { api.searchProducts(query).products.map { it.toDomain() } }
}