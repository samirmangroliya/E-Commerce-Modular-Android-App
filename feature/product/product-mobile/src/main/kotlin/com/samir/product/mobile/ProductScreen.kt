package com.samir.product.mobile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.samir.commonproductui.ProductGrid
import com.samir.model.Product
import com.samir.product.domain.ProductUIState
import com.samir.product.domain.ProductViewModel
import com.samir.ui.ErrorView
import com.samir.ui.LoadingView

@Composable
fun ProductScreen(
    onProductClick: (Product) -> Unit,
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
    onProductClick: (Product) -> Unit,
    onCartClick: () -> Unit,
    onSearchQueryChanged: (String) -> Unit,
    onRetry: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("E-Shop") },
                actions = {
                    IconButton(onClick = onCartClick) {
                        Icon(Icons.Default.ShoppingCart, contentDescription = "Cart")
                    }
                },
            )
        },
    ) { padding ->
        Column(modifier = Modifier
            .padding(padding)
            .fillMaxSize()) {

            OutlinedTextField(
                value = state.searchQuery,
                onValueChange = onSearchQueryChanged,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                label = { Text("Search products (e.g. phone)") },
                singleLine = true,
            )

            when {
                state.isLoading -> LoadingView(modifier = Modifier.weight(1f))
                state.errorMessage != null -> ErrorView(
                    message = state.errorMessage ?: "",
                    modifier = Modifier.weight(1f),
                    onRetry = onRetry,
                )

                else -> ProductGrid(
                    products = state.products,
                    onProductClick = onProductClick,
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}