package com.example.lpg.android.ui.nav

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.lpg.android.data.local.model.Order
import com.example.lpg.android.data.model.Address
import com.example.lpg.android.data.model.GasItem
import com.example.lpg.android.ui.screen.cart.CartScreen
import com.example.lpg.android.ui.screen.checkout.CheckOutScreen
import com.example.lpg.android.ui.screen.checkout.FillInAddressInfo
import com.example.lpg.android.ui.screen.home.HomeScreen
import com.example.lpg.android.ui.screen.orders.OrderScreen
import com.example.lpg.android.ui.viewmodel.CartViewModel
import com.example.lpg.android.ui.viewmodel.CylinderViewModel
import com.example.lpg.android.ui.viewmodel.OrdersViewModel


@Composable
fun MainNavigation(
    cylinderViewModel: CylinderViewModel,
    cartViewModel: CartViewModel,
    orderViewModel: OrdersViewModel,
) {

    val navController = rememberNavController()
    val cartItems by cartViewModel.cartItems.collectAsState()
    val orders by orderViewModel.orders.observeAsState()
    val cartTotal by cartViewModel.cartTotal.collectAsState()
    val onAddToCart: (GasItem) -> Unit = {
        cartViewModel.addItem(it)
    }
    val onRemoveFromCart: (GasItem) -> Unit = {
        cartViewModel.removeItem(it)
    }
    val onCheckOut: (Order) -> Unit = { order ->
        orderViewModel.addOrder(order)
        cartViewModel.clearCart()
        navController.popBackStack(Home, false)
    }
    var userAddress by remember { mutableStateOf(Address.default) }

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
                onClearCart = { cartViewModel.clearCart() },
                onCheckOut = { navController.navigate(CheckOut) }
            )
        }

        composable<CheckOut> {
            CheckOutScreen(
                navController = navController,
                cartItems = cartItems,
                addressInfo = userAddress,
                onCheckout = onCheckOut
            )
        }

        composable<AddressInfo> {
            FillInAddressInfo(
                addressInfo = userAddress,
                onSave = {
                    userAddress = it
                    navController.popBackStack()
                },
                onClose = {
                    navController.popBackStack()
                }
            )
        }

        composable<Orders> {
            orders?.let {
                OrderScreen(
                    orders = it,
                    onClose = {
                        navController.popBackStack()
                    }
                )
            } ?: run {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Error Processing orders.")
                }
            }
        }

    }

}