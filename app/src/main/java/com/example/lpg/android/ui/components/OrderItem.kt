package com.example.lpg.android.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lpg.android.R
import com.example.lpg.android.data.model.OrderModel
import com.example.lpg.android.ui.theme.LpgGasAppTheme
import com.example.lpg.android.util.formatTo2DecimalPlaces

@Composable
fun OrderItem(
    modifier: Modifier = Modifier,
    orderModel: OrderModel,
) {


    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.Top
    ) {

        Image(
            painter = painterResource(R.drawable.gas_demo),
            contentDescription = null,
            Modifier
                .size(50.dp)
                .clip(shape = RoundedCornerShape(8.dp))
        )

        Spacer(Modifier.width(12.dp))

        Column {
            Text(text = "Items : ${orderModel.items}", style = MaterialTheme.typography.bodySmall)
            Text(
                text = "Total : KES ${orderModel.total.formatTo2DecimalPlaces()}",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "Placed On : ${orderModel.datePlaced}",
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(Modifier.weight(1f))

        Text(
            text = "Order : #${orderModel.orderId.split("-").first()}",
            style = MaterialTheme.typography.bodySmall,
            color = Color.LightGray
        )


    }


}

@Preview
@Composable
private fun OrderItemPrev() {
    LpgGasAppTheme {
        OrderItem(orderModel = OrderModel.default)
    }
}
