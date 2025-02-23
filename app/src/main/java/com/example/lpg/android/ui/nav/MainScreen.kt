package com.example.lpg.android.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lpg.android.ui.screen.home.HomeScreen


@Composable
fun MainScreen(modifier: Modifier = Modifier) {

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Home,
    ){

        composable<Home> {
            HomeScreen(
                navController = navController
            )
        }

        composable<Details> {  }

        composable<Cart> {  }

        composable<CheckOut> {  }

    }

}