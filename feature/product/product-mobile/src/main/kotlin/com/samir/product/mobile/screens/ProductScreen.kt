package com.samir.product.mobile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.samir.commonproductui.ProductList
import com.samir.product.presentation.ProductUIState
import com.samir.product.presentation.ProductViewModel
import com.samir.ui.ErrorView
import com.samir.ui.LoadingView

@Composable
fun ProductScreen(
    onProductClick: (Int) -> Unit,
    onCartClick: () -> Unit,
    viewModel: ProductViewModel? = null,
) {
    if (LocalInspectionMode.current && viewModel == null) {
        ProductScreenContent(
            state = ProductUIState(isLoading = true),
            onProductClick = onProductClick,
            onCartClick = onCartClick,
            onSearchQueryChanged = {},
            onRetry = {}
        )
    } else {
        val actualViewModel: ProductViewModel = viewModel ?: hiltViewModel()
        val state by actualViewModel.uiState.collectAsState()
        ProductScreenContent(
            state = state,
            onProductClick = onProductClick,
            onCartClick = onCartClick,
            onSearchQueryChanged = actualViewModel::onSearchQueryChanged,
            onRetry = actualViewModel::loadProducts
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreenContent(
    state: ProductUIState,
    onProductClick: (Int) -> Unit,
    onCartClick: () -> Unit,
    onSearchQueryChanged: (String) -> Unit,
    onRetry: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Product") },
                actions = {
                    IconButton(onClick = onCartClick) {
                        Icon(Icons.Default.ShoppingCart, contentDescription = "Cart")
                    }
                },
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = padding.calculateTopPadding())
        ) {

            when {
                state.isLoading -> LoadingView(modifier = Modifier.weight(1f))
                state.errorMessage != null -> ErrorView(
                    message = state.errorMessage ?: "",
                    modifier = Modifier.weight(1f),
                    onRetry = onRetry,
                )

                else -> ProductList(
                    products = state.products,
                    onProductClick = {
                        onProductClick(it.id)
                    },
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}