package com.example.lpg.android.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.lpg.android.data.model.GasItem
import com.example.lpg.android.ui.screen.cart.CartScreen
import com.example.lpg.android.ui.screen.checkout.CheckOutScreen
import com.example.lpg.android.ui.screen.checkout.FillInAddressInfo
import com.example.lpg.android.ui.screen.home.HomeScreen
import com.example.lpg.android.ui.screen.orders.OrderScreen
import com.example.lpg.android.ui.viewmodel.CartViewModel
import com.example.lpg.android.ui.viewmodel.CylinderViewModel


@Composable
fun MainNavigation(
    cylinderViewModel: CylinderViewModel,
    cartViewModel: CartViewModel,
) {

    val navController = rememberNavController()
    val cartItems by cartViewModel.cartItems.collectAsState()
    val cartTotal by cartViewModel.cartTotal.collectAsState()
    val onAddToCart: (GasItem) -> Unit = {
        cartViewModel.addItem(it)
    }
    val onRemoveFromCart: (GasItem) -> Unit = {
        cartViewModel.removeItem(it)
    }

    NavHost(
        navController = navController,
        startDestination = Home,
    ) {
        composable<Home> {
            HomeScreen(
                navController = navController,
                onAddToCart = onAddToCart,
                cylinderViewModel = cylinderViewModel,
                cartItems = cartItems
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
                onClose = { navController.popBackStack() },
                onClearCart = { cartViewModel.clearCart() }
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