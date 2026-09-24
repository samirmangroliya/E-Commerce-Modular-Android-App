package com.samir.home.mobile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.samir.commonproductui.ProductGrid
import com.samir.home.domain.HomeUIState
import com.samir.home.domain.HomeViewModel
import com.samir.model.Product
import com.samir.ui.ErrorView
import com.samir.ui.LoadingView

@Composable
fun HomeScreen(
    modifier: Modifier,
    onProductClick: (Int) -> Unit,
    onCartClick: () -> Unit,
    onChatClick: () -> Unit,
    viewModel: HomeViewModel? = null,
) {
    if (LocalInspectionMode.current && viewModel == null) {
        HomeScreenContent(
            state = HomeUIState(isLoading = true),
            onProductClick = { onProductClick(it.id) },
            onCartClick = onCartClick,
            onChatClick = onChatClick,
            onSearchQueryChanged = {},
            onRetry = {}
        )
    } else {
        val actualViewModel: HomeViewModel = viewModel ?: hiltViewModel()
        val state by actualViewModel.uiState.collectAsState()
        HomeScreenContent(
            state = state,
            onProductClick = {
                onProductClick(it.id)
                actualViewModel.logProductClick(it)
            },
            onCartClick = onCartClick,
            onChatClick = onChatClick,
            onSearchQueryChanged = actualViewModel::onSearchQueryChanged,
            onRetry = actualViewModel::loadProducts
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(
    state: HomeUIState,
    onProductClick: (Product) -> Unit,
    onCartClick: () -> Unit,
    onChatClick: () -> Unit,
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
                    IconButton(onClick = onChatClick) {
                        Icon(Icons.Default.ChatBubble, contentDescription = "Chat")
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

            OutlinedTextField(
                value = state.searchQuery,
                onValueChange = onSearchQueryChanged,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                label = { Text("Search products (e.g. phone)") },
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = Color.Gray
                )
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