package com.example.lpg.android.ui.screen.cart

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lpg.android.R
import com.example.lpg.android.data.model.CartObject
import com.example.lpg.android.data.model.GasItem
import com.example.lpg.android.ui.components.CartItem
import com.example.lpg.android.ui.theme.LpgGasAppTheme
import com.example.lpg.android.util.formatTo2DecimalPlaces

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    cartItems: List<CartObject>,
    onClose: () -> Unit = {},
    onClearCart: () -> Unit = {},
    onRemoveFromCart: (GasItem) -> Unit,
) {

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
        ) {
            item {
                CenterAlignedTopAppBar(
                    title = {
                        Text(text = "Cart")
                    },
                    navigationIcon = {
                        IconButton(onClick = onClose) {
                            Icon(imageVector = Icons.Rounded.Close, contentDescription = null)
                        }
                    },
                    actions = {
                        IconButton(onClick = onClearCart) {
                            Icon(imageVector = Icons.Rounded.Delete, contentDescription = null)
                        }
                    }
                )
            }

            if (cartItems.isEmpty()) {
                item {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "Add Items first")
                    }
                }
            } else {
                items(cartItems) { cartItem ->
                    CartItem(
                        cartObject = cartItem,
                        onRemove = { onRemoveFromCart(cartItem.item) }
                    )
                }
            }

        }



        Button(
            onClick = {
                println("cart items : ${cartItems.size} $cartItems")
            },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
                .padding(24.dp)
                .align(Alignment.BottomCenter),
            contentPadding = ButtonDefaults.TextButtonContentPadding
        ) {
            Text(
                modifier = Modifier.padding(6.dp),
                text = buildAnnotatedString {
                    withStyle(SpanStyle()) {
                        append("Proceed to checkout")
                    }
                }
            )
        }

    }

}


@Preview(showBackground = true)
@Composable
private fun CartScreenPrev() {
    LpgGasAppTheme {
        CartScreen(
            cartItems = listOf(),
            onRemoveFromCart = { },
        )
    }
}


