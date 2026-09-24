package com.samir.home.route

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.samir.home.mobile.HomeScreen

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