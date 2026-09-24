package com.samir.checkout.mobile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.samir.checkout.mobile.route.CheckoutRoute
import com.samir.navigation.AppRoutes
import com.samir.navigation.FeatureNavigation

class CheckoutNavigation : FeatureNavigation {

    override fun register(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController
    ) {
        navGraphBuilder.composable(
            route = AppRoutes.PROFILE
        ) {
            CheckoutRoute()
        }
    }
}