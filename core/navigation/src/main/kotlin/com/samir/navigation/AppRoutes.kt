package com.samir.navigation

object AppRoutes {

    const val AUTH = "auth"
    const val MAIN = "main"

    const val HOME = "home"
    const val CART = "cart"

    const val PRODUCTS = "products"
    const val ORDERS = "orders"
    const val PROFILE = "profile"

    const val PRODUCT = "product"
    const val PRODUCT_DETAILS = "$PRODUCT/{productId}"
    const val CHECKOUT = "checkout"

    fun productDetailsRoute(productId: Int): String {
        return "$PRODUCT/$productId"
    }
}