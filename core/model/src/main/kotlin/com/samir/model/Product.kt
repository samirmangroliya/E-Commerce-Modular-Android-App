package com.samir.model

/**
 * Domain model. This is the ONLY representation of a product that feature modules
 * (home-domain, cart, product-detail, etc.) ever see or work with.
 */
data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Int,
    val brand: String,
    val category: String,
    val thumbnail: String,
    val images: List<String>,
) {
    val discountedPrice: Double
        get() = price - (price * discountPercentage / 100)

    val isInStock: Boolean
        get() = stock > 0
}