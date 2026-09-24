package com.samir.product.mobile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.samir.navigation.AppRoutes
import com.samir.navigation.AppRoutes.productDetailsRoute
import com.samir.navigation.FeatureNavigation
import com.samir.navigation.NavArgs
import com.samir.product.mobile.route.ProductDetailsRoute
import com.samir.product.mobile.route.ProductListRoute

class ProductNavigation(
    private val onClickCart: () -> Unit
) : FeatureNavigation {

    override fun register(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController
    ) {
        navGraphBuilder.composable(
            route = AppRoutes.PRODUCTS
        ) {
            ProductListRoute(
                onProductClick = { id ->
                    navController.navigate(productDetailsRoute(id))
                }, onCartClick = onClickCart
            )
        }

        navGraphBuilder.composable(
            route = AppRoutes.PRODUCT_DETAILS,
            arguments = listOf(
                navArgument(NavArgs.PRODUCT_ID_ARGUMENT) {
                    type = NavType.IntType
                }
            )
        ) {
            ProductDetailsRoute(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}