package com.samir.order.mobile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.samir.navigation.AppRoutes
import com.samir.navigation.FeatureNavigation
import com.samir.order.mobile.route.OrderListRoute

class OrderNavigation : FeatureNavigation {

    override fun register(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController
    ) {
        navGraphBuilder.composable(
            route = AppRoutes.ORDERS
        ) {
            OrderListRoute()
        }
    }
}