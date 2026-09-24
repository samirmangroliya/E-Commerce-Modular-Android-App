package com.samir.cart.mobile.route

import androidx.compose.runtime.Composable
import com.samir.cart.mobile.CartScreen

@Composable
fun CartRoute(
    onProductClick: (Int) -> Unit
) {
    CartScreen(
        onProductClick = onProductClick
    )
}