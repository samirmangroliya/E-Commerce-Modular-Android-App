package com.samir.cart.mobile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.samir.cart.mobile.route.CartRoute
import com.samir.navigation.AppRoutes
import com.samir.navigation.FeatureNavigation

class CartNavigation(
    private val onClickProduct: (Int) -> Unit
) : FeatureNavigation {

    override fun register(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController
    ) {
        navGraphBuilder.composable(
            route = AppRoutes.CART
        ) {
            CartRoute {
                onClickProduct
            }
        }
    }
}