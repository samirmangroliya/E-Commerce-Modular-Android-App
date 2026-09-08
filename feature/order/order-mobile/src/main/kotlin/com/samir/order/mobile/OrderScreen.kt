package com.samir.order.mobile

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
import com.samir.order.domain.OrderUIState
import com.samir.order.domain.OrderViewModel

@Composable
fun OrderScreen(
    onProductClick: (Int) -> Unit,
    onCartClick: () -> Unit,
    viewModel: OrderViewModel? = null,
) {
    if (LocalInspectionMode.current && viewModel == null) {
        OrderScreenContent(
            state = OrderUIState(isLoading = true),
            onProductClick = onProductClick,
            onCartClick = onCartClick,
            onSearchQueryChanged = {},
            onRetry = {}
        )
    } else {
        val actualViewModel: OrderViewModel = viewModel ?: hiltViewModel()
        val state by actualViewModel.uiState.collectAsState()
        OrderScreenContent(
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
fun OrderScreenContent(
    state: OrderUIState,
    onProductClick: (Int) -> Unit,
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

        }
    }
}