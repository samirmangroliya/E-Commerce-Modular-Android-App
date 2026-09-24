package com.samir.ecommerceapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ProductionQuantityLimits
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.samir.navigation.AppRoutes

data class BottomBarDestination(
    val route: String,
    val label: String,
    val icon: ImageVector
)

val bottomBarDestinations = listOf(
    BottomBarDestination(
        route = AppRoutes.HOME,
        label = "Home",
        icon = Icons.Default.Home
    ),
    BottomBarDestination(
        route = AppRoutes.PRODUCTS,
        label = "Products",
        icon = Icons.Default.ProductionQuantityLimits
    ),
    BottomBarDestination(
        route = AppRoutes.CART,
        label = "Cart",
        icon = Icons.Default.ShoppingCart
    ),
    BottomBarDestination(
        route = AppRoutes.ORDERS,
        label = "Orders",
        icon = Icons.Default.ShoppingBag
    ),
    BottomBarDestination(
        route = AppRoutes.PROFILE,
        label = "Profile",
        icon = Icons.Default.Person
    )
)