package com.samir.checkout.mobile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.AsyncImage
import com.samir.model.Product
import com.samir.checkout.domain.CheckoutUIState
import com.samir.checkout.domain.CheckoutViewModel
import com.samir.ui.ErrorView
import com.samir.ui.LoadingView
import com.samir.ui.PriceTag

@Composable
fun CheckoutScreen(
    onProductClick: (Int) -> Unit,
    onCartClick: () -> Unit,
    viewModel: CheckoutViewModel? = null,
) {
    if (LocalInspectionMode.current && viewModel == null) {
        CheckoutScreenContent(
            state = CheckoutUIState(isLoading = true),
            onProductClick = onProductClick,
            onCartClick = onCartClick,
            onSearchQueryChanged = {},
            onRetry = {}
        )
    } else {
        val actualViewModel: CheckoutViewModel = viewModel ?: hiltViewModel()
        val state by actualViewModel.uiState.collectAsState()
        CheckoutScreenContent(
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
fun CheckoutScreenContent(
    state: CheckoutUIState,
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

@Composable
private fun ProductGrid(
    products: List<Product>,
    onProductClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // tablet adaptivity note below
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier.fillMaxSize(),
    ) {
        items(products, key = { it.id }) { product ->
            ProductCard(product = product, onClick = { onProductClick(product.id) })
        }
    }
}

@Composable
private fun ProductCard(product: Product, onClick: () -> Unit) {
    Card(onClick = onClick, shape = RoundedCornerShape(12.dp)) {
        Column(modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                model = product.thumbnail,
                contentDescription = product.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(8.dp)),
            )
            Spacer(Modifier.height(8.dp))
            Text(text = product.title, style = MaterialTheme.typography.bodyMedium, maxLines = 1)
            Spacer(Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                PriceTag(price = product.discountedPrice)
            }
        }
    }
}