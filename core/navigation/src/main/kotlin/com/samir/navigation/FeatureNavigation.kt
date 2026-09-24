package com.samir.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface FeatureNavigation {

    fun register(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController
    )
}