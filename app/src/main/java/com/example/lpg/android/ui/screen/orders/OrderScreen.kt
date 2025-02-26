package com.example.lpg.android.ui.screen.orders

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lpg.android.data.local.model.Order
import com.example.lpg.android.data.model.OrderModel
import com.example.lpg.android.ui.components.OrderItem
import com.example.lpg.android.ui.theme.LpgGasAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderScreen(
    orders: List<Order> = emptyList(),
    modifier: Modifier = Modifier,
    onClose: () -> Unit = {},
) {

    val modeled = orders.map {
        OrderModel(
            orderId = it.orderId,
            total = it.total,
            datePlaced = it.datePlaced,
            items = it.items.size
        )
    }

    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {

        item {
            CenterAlignedTopAppBar(
                title = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "Orders")
                        Text(
                            text = "Your orders.",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onClose) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
            )
        }

        if (orders.isEmpty()) {
            item {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    Text(text = "No orders found")
                }
            }
        } else {
            items(modeled) { item ->
                OrderItem(orderModel = item)
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun OrderScreenPrev() {
    LpgGasAppTheme {
        OrderScreen()
    }
}


