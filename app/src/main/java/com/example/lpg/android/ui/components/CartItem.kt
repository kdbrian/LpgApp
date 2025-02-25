package com.example.lpg.android.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.lpg.android.R
import com.example.lpg.android.data.model.CartObject
import com.example.lpg.android.ui.theme.LpgGasAppTheme
import com.example.lpg.android.util.formatTo2DecimalPlaces

@Composable
fun CartItem(
    modifier: Modifier = Modifier,
    cartObject: CartObject,
    onRemove: () -> Unit = {},
) {

    var isExpanded by remember { mutableStateOf(true) }
    val (count, setCount) = remember { mutableIntStateOf(cartObject.count) }
    val total = remember(count) {
        derivedStateOf {
            count * cartObject.item.price
        }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { isExpanded = !isExpanded }
                .padding(8.dp),
            verticalAlignment = Alignment.Top
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                AsyncImage(
                    model = cartObject.item.imageUrl,
                    placeholder = painterResource(R.drawable.gas_demo),
                    contentDescription = cartObject.item.name,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(6.dp))
                )

                Column {
                    Text(
                        text = "Size : ${cartObject.item.name}",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = "Price : ${cartObject.item.price.formatTo2DecimalPlaces()}",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(text = "Units : $count", style = MaterialTheme.typography.bodySmall)
                    Text(
                        text = "Total : KES ${total.value.formatTo2DecimalPlaces()}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            Spacer(Modifier.weight(1f))

            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = {
                        if (count > 1) {
                            setCount(count - 1)
                            cartObject.count = count
                        } else if (count == 1) {
                            onRemove()
                        }
                    }) {
                        Icon(
                            painter = painterResource(R.drawable.remove_24dp),
                            contentDescription = null
                        )
                    }
                    Text(text = "$count")
                    IconButton(onClick = {
                        setCount(count + 1)
                        cartObject.count = count
                    }) {
                        Icon(
                            imageVector = Icons.Rounded.Add,
                            contentDescription = null
                        )
                    }
                }

            }

        }

    }


}

@Preview(showBackground = true)
@Composable
private fun CartItemPrev() {
    LpgGasAppTheme {
//        CartItem()
    }
}


