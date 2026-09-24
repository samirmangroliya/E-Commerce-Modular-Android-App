package com.samir.home.route

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.samir.home.mobile.HomeScreen
import com.samir.model.Product
import kotlinx.coroutines.launch

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    onProductClick: (Int) -> Unit,
    onCartClick: () -> Unit,
    onChatClick: () -> Unit
) {
    Scaffold(
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->

        HomeScreen(
            modifier = Modifier.padding(innerPadding),

            onProductClick = onProductClick,

            onCartClick = onCartClick,

            onChatClick = onChatClick
        )
    }
}