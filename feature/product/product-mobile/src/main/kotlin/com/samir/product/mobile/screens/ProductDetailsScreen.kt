package com.samir.product.mobile.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.samir.model.Product
import com.samir.product.mobile.screens.productdetails.ProductBottomBar
import com.samir.product.mobile.screens.productdetails.ProductDetailsContent
import com.samir.product.mobile.screens.productdetails.ProductDetailsError
import com.samir.product.mobile.screens.productdetails.ProductDetailsTopBar
import com.samir.product.presentation.ProductDetailsUiState
import com.samir.ui.LoadingView

@Composable
fun ProductDetailsScreen(
    uiState: ProductDetailsUiState,
    onBack: () -> Unit,
    onAddToCart: (Product, Int) -> Unit,
    onBuyNow: (Product, Int) -> Unit,
    onWishlistClick: (Product) -> Unit,
    onShareClick: (Product) -> Unit,
    onChangeAddress: () -> Unit,
    modifier: Modifier = Modifier,
    onIncreaseQuantity: (Product, Int) -> Unit,
    onDecreaseQuantity: (Product, Int) -> Unit,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            ProductDetailsTopBar(
                onBack = onBack,
                product = uiState.product,
                onWishlistClick = onWishlistClick,
                onShareClick = onShareClick
            )
        },
        bottomBar = {
            if (!uiState.isLoading && uiState.product != null) {
                uiState.product?.let {
                    ProductBottomBar(
                        product = it,
                        onAddToCart = onAddToCart,
                        onBuyNow = onBuyNow
                    )
                }

            }
        }
    ) { innerPadding ->

        when {
            uiState.isLoading -> {
                LoadingView(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = innerPadding.calculateTopPadding())
                )
            }

            uiState.errorMessage != null -> {
                ProductDetailsError(
                    message = uiState.errorMessage ?: "",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                )
            }

            uiState.product != null -> {
                uiState.product?.let {
                    ProductDetailsContent(
                        product = it,
                        onChangeAddress = onChangeAddress,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }

            else -> {
                ProductDetailsError(
                    message = "Product not found",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                )
            }
        }
    }
}