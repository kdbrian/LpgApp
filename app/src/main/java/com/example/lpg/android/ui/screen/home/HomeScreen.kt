package com.example.lpg.android.ui.screen.home

import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.lpg.android.R
import com.example.lpg.android.data.model.CartObject
import com.example.lpg.android.data.model.GasItem
import com.example.lpg.android.ui.components.ProductCard
import com.example.lpg.android.ui.components.ProductCardLoading
import com.example.lpg.android.ui.nav.Cart
import com.example.lpg.android.ui.nav.Orders
import com.example.lpg.android.ui.screen.detail.ProductDetailsBottomSheet
import com.example.lpg.android.ui.theme.LpgGasAppTheme
import com.example.lpg.android.ui.viewmodel.CylinderViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    cartItems: List<CartObject>,
    navController: NavHostController,
    onAddToCart: (GasItem) -> Unit = {},
    cylinderViewModel: CylinderViewModel,
) {

    var isCategoryDropDownExpanded by remember { mutableStateOf(false) }
    val categories = listOf("All", "3 Kg", "6 Kg", "13 Kg")
    var selectedCategory by remember { mutableIntStateOf(0) }
    val icon = remember {
        derivedStateOf {
            if (isCategoryDropDownExpanded) Icons.Rounded.KeyboardArrowUp
            else Icons.Rounded.KeyboardArrowDown
        }
    }
    var isDetailsBottomSheetShown by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    val (selectedProduct, setSelectedProduct) = remember {
        mutableStateOf<GasItem?>(null)
    }
    val gasItems = cylinderViewModel.gasItems.collectAsLazyPagingItems()

    val onDismissSheet: () -> Unit = {
        scope.launch {
            sheetState.hide()
            isDetailsBottomSheetShown = !isDetailsBottomSheetShown
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
                IconButton(onClick = { navController.navigate(Orders) }) {
                    Icon(painter = painterResource(R.drawable.orders_24), contentDescription = null)
                }

                IconButton(onClick = { navController.navigate(Cart) }) {
                    Icon(imageVector = Icons.Rounded.ShoppingCart, contentDescription = null)
                }
            },
        )

        Spacer(Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(text = "Filter", style = MaterialTheme.typography.titleMedium)

            Spacer(Modifier.weight(1f))

            Box {
                OutlinedTextField(
                    value = categories[selectedCategory],
                    onValueChange = {},
                    enabled = false,
                    modifier = Modifier
                        .width(150.dp)
                        .wrapContentHeight()
                        .pointerInput(Unit) {
                            detectTapGestures {
                                isCategoryDropDownExpanded = !isCategoryDropDownExpanded
                            }
                        },
                    trailingIcon = {
                        Icon(imageVector = icon.value, contentDescription = null)
                    },
                    shape = RoundedCornerShape(24.dp)
                )

                DropdownMenu(
                    expanded = isCategoryDropDownExpanded,
                    onDismissRequest = { isCategoryDropDownExpanded = false }
                ) {
                    categories.forEachIndexed { index, category ->
                        DropdownMenuItem(
                            text = { Text(text = category) },
                            onClick = {
                                selectedCategory = index
                                isCategoryDropDownExpanded = false
                            }
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(12.dp))
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(gasItems.itemCount) { index ->
                gasItems[index]?.let { gasItem ->
                    ProductCard(
                        gasItem = gasItem,
                        onSelect = {
                            isDetailsBottomSheetShown = !isDetailsBottomSheetShown
                            setSelectedProduct(it)
                        }
                    )
                }
            }

            gasItems.apply {
                println("state : $loadState")
                when (loadState.append) {
                    is LoadState.Loading -> {
                        item(3) {
                            ProductCardLoading()
                        }
                    }

                    is LoadState.Error -> {
                        item { Text(text = "Failed to load more items.") }
                    }

                    is LoadState.NotLoading -> {
                        if (loadState.append.endOfPaginationReached) {
                            item {
                                Text(
                                    text = "No more items",
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                        }
                    }
                }
            }

        }

        if (isDetailsBottomSheetShown) {
            ModalBottomSheet(
                onDismissRequest = onDismissSheet,
                sheetState = sheetState,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                selectedProduct?.let { item ->
                    ProductDetailsBottomSheet(
                        gasItem = item,
                        onAddToCart = {
                            onAddToCart(it)
                            onDismissSheet()
                        },
                        onClose = onDismissSheet
                    )
                } ?: kotlin.run {
                    ProductCardLoading()
                }

            }

        }

    }

}


@Preview(showBackground = true)
@Composable
private fun HomeScreenPrev() {
    LpgGasAppTheme {
//        HomeScreen(
//            navController = rememberNavController()
//        )
    }
}





