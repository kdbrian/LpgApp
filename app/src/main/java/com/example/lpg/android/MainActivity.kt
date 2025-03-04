package com.example.lpg.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lpg.android.data.local.LpgAppDb
import com.example.lpg.android.data.remote.api.MockApiClient
import com.example.lpg.android.ui.nav.MainNavigation
import com.example.lpg.android.ui.theme.LpgGasAppTheme
import com.example.lpg.android.ui.viewmodel.CartViewModel
import com.example.lpg.android.ui.viewmodel.CylinderViewModel
import com.example.lpg.android.ui.viewmodel.OrdersViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            LpgGasAppTheme {
                App {

                    val appDb = LpgAppDb.getDatabase(this)

                    LocalViewModelStoreOwner.current?.let { viewModelStoreOwner ->

                        val cylinderViewModel = viewModel(
                            modelClass = CylinderViewModel::class,
                            viewModelStoreOwner = viewModelStoreOwner,
                            key = CylinderViewModel::class.simpleName,
                            factory = CylinderViewModel.Factory(MockApiClient.cylinderApiService)
                        )

                        val cartViewModel = viewModel(
                            modelClass = CartViewModel::class,
                            viewModelStoreOwner = viewModelStoreOwner,
                            key = CartViewModel::class.simpleName,
                            factory = CartViewModel.Factory()
                        )

                        val orderViewModel = viewModel(
                            modelClass = OrdersViewModel::class,
                            viewModelStoreOwner = viewModelStoreOwner,
                            key = CartViewModel::class.simpleName,
                            factory = OrdersViewModel.Factory(appDb.ordersDao())
                        )

                        MainNavigation(
                            cylinderViewModel = cylinderViewModel,
                            cartViewModel = cartViewModel,
                            orderViewModel = orderViewModel
                        )

                    }

                }
            }
        }
    }
}

@Composable
fun App(content: @Composable () -> Unit = {}, ) {
    CompositionLocalProvider {
        content()
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LpgGasAppTheme {
    }
}