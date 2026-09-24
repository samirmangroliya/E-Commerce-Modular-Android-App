package com.samir.profile.mobile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.samir.navigation.AppRoutes
import com.samir.navigation.FeatureNavigation
import com.samir.profile.mobile.route.ProfileRoute

class ProfileNavigation : FeatureNavigation {

    override fun register(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController
    ) {
        navGraphBuilder.composable(
            route = AppRoutes.PROFILE
        ) {
            ProfileRoute()
        }
    }
}