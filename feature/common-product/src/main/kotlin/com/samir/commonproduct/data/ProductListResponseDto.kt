package com.samir.commonproduct.data

import com.google.gson.annotations.SerializedName
import com.samir.model.Review


data class ProductListResponseDto(
    @SerializedName("products") val products: List<ProductDto>,
    @SerializedName("total") val total: Int,
    @SerializedName("skip") val skip: Int,
    @SerializedName("limit") val limit: Int,
)

data class ProductDto(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("price") val price: Double,
    @SerializedName("discountPercentage") val discountPercentage: Double,
    @SerializedName("rating") val rating: Double,
    @SerializedName("stock") val stock: Int,
    @SerializedName("brand") val brand: String?,
    @SerializedName("category") val category: String,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("images") val images: List<String>,
    @SerializedName("reviews") val reviews: List<Review>,
    @SerializedName("warrantyInformation") val warrantyInformation: String,
    @SerializedName("shippingInformation") val shippingInformation: String,
    @SerializedName("availabilityStatus") val availabilityStatus: String,
    @SerializedName("returnPolicy") val returnPolicy: String,
)