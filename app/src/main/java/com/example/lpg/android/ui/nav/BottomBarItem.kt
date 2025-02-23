package com.example.lpg.android.ui.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

sealed class BottomBarItem(
    val icon: ImageVector,
    val title: String,
    val route: Route,
) {
    data object HomeBottomBarItem : BottomBarItem(
        icon = Icons.Default.Home,
        title = "Home",
        route = Home
    )


    data object CartBottomBarItem : BottomBarItem(
        icon = Icons.Default.ShoppingCart,
        title = "Cart",
        route = Home
    )

    companion object {

        private val homeCartBottomBarItems = listOf(HomeBottomBarItem, CartBottomBarItem)

        @Composable
        fun BottomBar(
            modifier: Modifier = Modifier,
            items: List<BottomBarItem> = homeCartBottomBarItems,
            navController: NavHostController,
        ) {
            val backStackEntry = navController.currentBackStackEntryAsState()
            val route = backStackEntry.value?.destination?.route
            NavigationBar(modifier = modifier) {
                items.forEach { item ->
                    NavigationBarItem(
                        selected = item.route.toString() == route,
                        onClick = { navController.navigate(item.route) },
                        icon = { Icon(imageVector = item.icon, contentDescription = null) },
                        label = { Text(text = item.title) }
                    )
                }
            }


        }
    }

}