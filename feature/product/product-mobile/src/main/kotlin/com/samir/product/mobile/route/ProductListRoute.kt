package com.samir.product.mobile.route

import androidx.compose.runtime.Composable
import com.samir.product.mobile.ProductScreen

@Composable
fun ProductListRoute(
    onProductClick: (Int) -> Unit,
    onCartClick: () -> Unit
) {

    ProductScreen(
        onProductClick = onProductClick,
        onCartClick = onCartClick
    )
}