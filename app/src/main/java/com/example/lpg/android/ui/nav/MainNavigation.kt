package com.example.lpg.android.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.lpg.android.data.model.CartObject
import com.example.lpg.android.data.model.GasItem
import com.example.lpg.android.ui.screen.cart.CartScreen
import com.example.lpg.android.ui.screen.checkout.CheckOutScreen
import com.example.lpg.android.ui.screen.checkout.FillInAddressInfo
import com.example.lpg.android.ui.screen.home.HomeScreen
import com.example.lpg.android.ui.screen.orders.OrderScreen


@Composable
fun MainNavigation() {

    val navController = rememberNavController()
    val cartItems = remember {
        mutableStateListOf<CartObject>()
    }
    val onAddToCart: (GasItem) -> Unit = {
        cartItems.add(CartObject(item = it, count = 1))
    }
    val onRemoveFromCart: (GasItem) -> Unit = { cartObject ->
        cartItems.removeIf { it.item == cartObject }
    }


    NavHost(
        navController = navController,
        startDestination = Home,
    ) {
        composable<Home> {
            HomeScreen(
                navController = navController,
                onAddToCart = onAddToCart
            )
        }

        composable<Details> {
            val productId = it.toRoute<Details>()
            // get product
            // ProductDetails()
        }

        composable<Cart> {
            CartScreen(
                cartItems = cartItems,
                onRemoveFromCart = onRemoveFromCart,
                onClose = { navController.popBackStack() }
            )
        }

        composable<CheckOut> {
            CheckOutScreen(
                navController = navController
            )
        }

        composable<AddressInfo> {
            FillInAddressInfo()
        }

        composable<Orders> {
            OrderScreen(
                onClose = {
                    navController.popBackStack()
                }
            )
        }

    }

}