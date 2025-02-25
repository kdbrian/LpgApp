package com.example.lpg.android.ui.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.lpg.android.R
import com.example.lpg.android.data.model.GasItem
import com.example.lpg.android.util.formatTo2DecimalPlaces

@Composable
fun ProductDetailsBottomSheet(
    modifier: Modifier = Modifier,
    gasItem: GasItem,
    onAddToCart: (GasItem) -> Unit = {},
    onClose: () -> Unit = {}
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp),
    ) {

        Box {
            IconButton(onClick = onClose) {
                Icon(imageVector = Icons.Rounded.Close, contentDescription = null)
            }
            AsyncImage(
                model = gasItem.imageUrl,
                placeholder = painterResource(R.drawable.gas_demo),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
        }

        Spacer(Modifier.height(12.dp))

        Text(text = gasItem.name, style = MaterialTheme.typography.titleMedium)

        Text(text = buildAnnotatedString {
            withStyle(
                SpanStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.LightGray
                )
            ) {
                append(gasItem.currency)
            }

            append(" ")

            withStyle(
                SpanStyle(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Light,
                )
            ) {
                append(gasItem.price.formatTo2DecimalPlaces())
            }
        })

        Button(
            onClick = { onAddToCart(gasItem) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Add to cart")
        }

        Spacer(Modifier.height(8.dp))

        Text(text = "More like this", style = MaterialTheme.typography.labelLarge)

        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(3) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .padding(4.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .background(shape = RoundedCornerShape(12.dp), color = Color.LightGray)
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.60f)
                            .height(12.dp)
                            .background(shape = RoundedCornerShape(12.dp), color = Color.LightGray)
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.20f)
                            .height(12.dp)
                            .background(shape = RoundedCornerShape(12.dp), color = Color.LightGray)
                    )


                }

            }
        }
    }

}