package com.samir.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.samir.home.route.HomeRoute
import com.samir.navigation.AppRoutes
import com.samir.navigation.FeatureNavigation

class HomeNavigation(
    private val onProductClick: (Int) -> Unit,
    private val onCartClick: () -> Unit,
    private val onChatClick: () -> Unit
) : FeatureNavigation {

    override fun register(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController
    ) {
        navGraphBuilder.composable(
            route = AppRoutes.HOME
        ) {
            HomeRoute(
                onProductClick = onProductClick,
                onCartClick = onCartClick,
                onChatClick = onChatClick
            )
        }
    }
}