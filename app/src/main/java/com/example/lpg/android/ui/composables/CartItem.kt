package com.example.lpg.android.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lpg.android.R
import com.example.lpg.android.ui.theme.LpgGasAppTheme

@Composable
fun CartItem(
    modifier: Modifier = Modifier,
) {

    var isExpanded by remember { mutableStateOf(true) }
    val (count, setCount) = remember { mutableIntStateOf(1) }
    val total = remember {
        derivedStateOf {
            count * 3000.99
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
                Box(
//            model = "",
//            contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .background(color = Color.LightGray, shape = RoundedCornerShape(6.dp))
                )

                Column {
                    Text(text = "Size : 13 Kg", style = MaterialTheme.typography.bodySmall)
                    Text(text = "Units : $count", style = MaterialTheme.typography.bodySmall)
                    Text(
                        text = "Total : KES ${total.value}",
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
                        if (count > 1) setCount(count - 1)
                    }) {
                        Icon(
                            painter = painterResource(R.drawable.remove_24dp),
                            contentDescription = null
                        )
                    }
                    Text(text = "$count")
                    IconButton(onClick = {
                        setCount(count + 1)
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
        CartItem()
    }
}


