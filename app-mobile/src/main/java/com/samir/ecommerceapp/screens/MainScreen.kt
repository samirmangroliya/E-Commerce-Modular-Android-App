package com.samir.ecommerceapp.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.samir.ecommerceapp.navigation.MainBottomBar
import com.samir.ecommerceapp.navigation.MainNavHost
import com.samir.navigation.AppRoutes

@Composable
fun MainScreen() {

    val navController = rememberNavController()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    val hideBottomBarRoutes = setOf(
        AppRoutes.PRODUCT_DETAILS,
        AppRoutes.CHECKOUT
    )

    val showBottomBar = currentRoute !in hideBottomBarRoutes

    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = showBottomBar,
            ) {
                MainBottomBar(
                    navController = navController
                )
            }
        }
    ) { paddingValues ->

        MainNavHost(
            navController = navController,
            modifier = Modifier.padding(paddingValues)
        )
    }
}