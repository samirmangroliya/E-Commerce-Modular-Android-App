package com.samir.cart.mobile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.samir.cart.domain.CartUIState
import com.samir.cart.domain.CartViewModel
import com.samir.ui.ErrorView
import com.samir.ui.LoadingView

@Composable
fun CartScreen(
    onProductClick: (Int) -> Unit,
    onCartClick: () -> Unit,
    viewModel: CartViewModel? = null,
) {
    if (LocalInspectionMode.current && viewModel == null) {
        CartScreenContent(
            state = CartUIState(isLoading = true),
            onProductClick = onProductClick,
            onRetry = {}
        )
    } else {
        val actualViewModel: CartViewModel = viewModel ?: hiltViewModel()
        val state by actualViewModel.uiState.collectAsState()
        CartScreenContent(
            state = state,
            onProductClick = onProductClick,
            onRetry = actualViewModel::loadCart
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreenContent(
    state: CartUIState,
    onProductClick: (Int) -> Unit,
    onRetry: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cart") },
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {


            when {
                state.isLoading -> LoadingView(modifier = Modifier.weight(1f))
                state.errorMessage != null -> ErrorView(
                    message = state.errorMessage ?: "",
                    modifier = Modifier.weight(1f),
                    onRetry = onRetry,
                )

                else -> {}
            }
        }
    }
}