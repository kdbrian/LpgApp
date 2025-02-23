package com.example.lpg.android.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.lpg.android.ui.composables.ProductCard
import com.example.lpg.android.ui.nav.Cart
import com.example.lpg.android.ui.theme.LpgGasAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {

    var isCategoryDropDownExpanded by remember { mutableStateOf(false) }
    val categories = listOf("All", "3 Kg", "6 Kg", "13 Kg")
    val selectedCategory = remember { mutableIntStateOf(0) }
    val icon = remember {
        derivedStateOf {
            if (isCategoryDropDownExpanded) Icons.Rounded.KeyboardArrowUp
            else Icons.Rounded.KeyboardArrowDown
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {

        TopAppBar(
            title = {
                Text(
                    text = "Lpg Gas Exchange",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold
                )
            },
            actions = {
                IconButton(onClick = { navController.navigate(Cart) }) {
                    Icon(imageVector = Icons.Rounded.ShoppingCart, contentDescription = null)
                }
            },
        )

        Spacer(Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(text = "Filter", style = MaterialTheme.typography.titleMedium)

            Spacer(Modifier.weight(1f))

            Box {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    modifier = Modifier.width(150.dp).wrapContentHeight(),
                    trailingIcon = {
                        Icon(imageVector = icon.value, contentDescription = null)
                    }
                )

                DropdownMenu(
                    expanded = isCategoryDropDownExpanded,
                    onDismissRequest = { isCategoryDropDownExpanded = false }
                ) {
                    categories.forEach {
                        DropdownMenuItem(
                            text = { Text(text = it) },
                            onClick = { isCategoryDropDownExpanded = false }
                        )
                    }
                }
            }
        }


        Spacer(Modifier.height(12.dp))

        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(8) {
                ProductCard()
            }
        }

    }

}


@Preview(showBackground = true)
@Composable
private fun HomeScreenPrev() {
    LpgGasAppTheme {
        HomeScreen(
            navController = rememberNavController()
        )
    }
}





