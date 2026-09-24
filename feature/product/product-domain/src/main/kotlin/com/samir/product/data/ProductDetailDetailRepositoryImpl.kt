package com.samir.product.data

import com.samir.common.network.NetworkResult
import com.samir.commonproduct.data.ProductApi
import com.samir.model.Product
import com.samir.product.domain.repository.ProductDetailRepository
import kotlinx.coroutines.CancellationException
import javax.inject.Inject

class ProductDetailDetailRepositoryImpl @Inject constructor(
    private val productApi: ProductApi
) : ProductDetailRepository {

    override suspend fun getProductById(
        productId: Int
    ): NetworkResult<Product> {

        return try {
            if (productId <= 0) {
                return NetworkResult.Error(
                    message = "Invalid product ID"
                )
            }

            val product = productApi.getProductDetail(productId)

            NetworkResult.Success(product)

        } catch (exception: CancellationException) {
            throw exception
        } catch (exception: Exception) {
            NetworkResult.Error(
                message = "Unable to fetch product details",
                throwable = exception
            )
        }
    }
}