package com.samir.commonproduct.data

import com.samir.model.Product
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {
    @GET("products")
    suspend fun getProducts(
        @Query("limit") limit: Int = 20,
        @Query("skip") skip: Int = 0,
    ): ProductListResponseDto

    @GET("products/{id}")
    suspend fun getProductDetail(@Path("id") id: Int): Product

    @GET("products/search")
    suspend fun searchProducts(@Query("q") query: String): ProductListResponseDto
}