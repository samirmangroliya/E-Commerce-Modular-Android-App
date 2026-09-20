package com.samir.ecommerceapp.routes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.samir.ecommerceapp.chat.ChatModuleLauncher
import com.samir.cart.mobile.HomeScreen

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier, onShowMessage: (String) -> Unit
) {
    val context = LocalContext.current

    HomeScreen(
        modifier = modifier,
        onProductClick = { product ->

        },
        onCartClick = {
            ChatModuleLauncher.launchChat(
                context = context, onError = onShowMessage
            )
        }
    )
}