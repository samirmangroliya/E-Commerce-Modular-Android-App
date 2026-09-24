package com.samir.ecommerceapp.navigation

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.samir.navigation.AppRoutes

@Composable
fun MainBottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentDestination: NavDestination? =
        navBackStackEntry.value?.destination

    NavigationBar(
        modifier = modifier
    ) {
        bottomBarDestinations.forEach { destination ->

            val selected =
                currentDestination?.route == destination.route

            NavigationBarItem(
                selected = selected,
                onClick = {
                    navController.navigate(destination.route) {

                        popUpTo(AppRoutes.HOME) {
                            saveState = true
                        }

                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    androidx.compose.material3.Icon(
                        imageVector = destination.icon,
                        contentDescription = destination.label
                    )
                },
                label = {
                    Text(destination.label)
                }
            )
        }
    }
}