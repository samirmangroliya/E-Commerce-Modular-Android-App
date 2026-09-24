package com.samir.ecommerceapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.samir.cart.mobile.CartScreen
import com.samir.cart.mobile.navigation.CartNavigation
import com.samir.checkout.mobile.navigation.CheckoutNavigation
import com.samir.ecommerceapp.chat.ChatModuleLauncher
import com.samir.home.navigation.HomeNavigation
import com.samir.home.route.HomeRoute
import com.samir.navigation.AppRoutes
import com.samir.navigation.AppRoutes.productDetailsRoute
import com.samir.order.mobile.navigation.OrderNavigation
import com.samir.product.mobile.navigation.ProductNavigation
import com.samir.profile.mobile.navigation.ProfileNavigation

@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = AppRoutes.HOME,
        modifier = modifier
    ) {

        //Home
        HomeNavigation(
            onProductClick = {
                navController.navigate(
                    productDetailsRoute(it)
                )
            },
            onCartClick = {
                navController.navigate(AppRoutes.CART)
            },
            onChatClick = {
                ChatModuleLauncher.launchChat(context, {})
            }
        ).register(
            navGraphBuilder = this,
            navController = navController
        )

        //Product
        ProductNavigation(
            onClickCart = {
                navController.navigate(AppRoutes.CART)
            }
        ).register(
            navGraphBuilder = this,
            navController = navController
        )

        //Cart
        CartNavigation(
            onClickProduct = {
                productDetailsRoute(id)
            }
        ).register(
            navGraphBuilder = this,
            navController = navController
        )


        //Order
        OrderNavigation().register(
            navGraphBuilder = this,
            navController = navController
        )

        //Profile
        ProfileNavigation().register(
            navGraphBuilder = this,
            navController = navController
        )

        //Profile
        CheckoutNavigation().register(
            navGraphBuilder = this,
            navController = navController
        )
    }
}