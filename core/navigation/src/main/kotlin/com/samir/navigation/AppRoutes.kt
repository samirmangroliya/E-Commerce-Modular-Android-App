package com.samirmangroliya.ecommerce.navigation

/**
 * Central route contract. Feature modules reference these constants to navigate
 * WITHOUT depending on each other's module — only core-navigation is shared.
 */
object AppRoutes {
    const val HOME = "home"
    const val CART = "cart"
    const val ACCOUNT = "ACCOUNT"

    const val PRODUCT_DETAIL_ROUTE = "product_detail/{productId}"
    const val PRODUCT_ID_ARG = "productId"

    fun productDetail(productId: Int): String = "product_detail/$productId"
}
