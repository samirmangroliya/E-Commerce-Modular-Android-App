package com.samir.product.mobile.route

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.samir.product.mobile.screens.ProductDetailsScreen
import com.samir.product.presentation.ProductDetailsViewModel

@Composable
fun ProductDetailsRoute(
    onBack: () -> Unit
) {
    val viewModel: ProductDetailsViewModel = hiltViewModel()

    val uiState =
        viewModel.uiState.collectAsStateWithLifecycle()

    ProductDetailsScreen(
        uiState = uiState.value,
        onBack = onBack,

        onAddToCart = { product, it ->
            viewModel.addToCart(product, it)
        },

        onBuyNow = { product, it ->
            viewModel.buyNow(product, it)
        },

        onIncreaseQuantity = { product, it ->
            viewModel.increaseQuantity(product, it)
        },

        onDecreaseQuantity = { product, it ->
            viewModel.decreaseQuantity(product, it)
        },

        onWishlistClick = { product ->
            viewModel.toggleWishlist(product)
        },

        onShareClick = { product ->
            viewModel.shareProduct(product)
        },

        onChangeAddress = {
            viewModel.changeAddress()
        }
    )
}