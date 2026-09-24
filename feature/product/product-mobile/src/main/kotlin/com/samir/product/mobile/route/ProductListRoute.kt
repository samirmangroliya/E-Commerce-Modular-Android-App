package com.samir.product.mobile.route

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.samir.model.Product
import com.samir.product.mobile.ProductScreen
import com.samir.product.mobile.screens.ProductDetailsScreen
import com.samir.product.presentation.ProductDetailsViewModel

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