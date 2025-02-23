package com.example.lpg.android.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.lpg.android.ui.theme.LpgGasAppTheme

@Composable
fun ProductCard(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier.fillMaxWidth().padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AsyncImage(
            model = "https://thispersondoesnotexist.com/",
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(12.dp))
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.LightGray
                    )
                ) {
                    append("KES")
                }


                withStyle(
                    SpanStyle(
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Light,
                    )
                ) {
                    append(" 1,000")
                }
            })

            Text(text = "13Kg", style = MaterialTheme.typography.titleMedium)
        }

    }


}


@Composable
fun ProductCardLoading(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(shape = RoundedCornerShape(12.dp), color = Color.LightGray)
        )


        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.20f)
                    .height(24.dp)
                    .background(shape = RoundedCornerShape(12.dp), color = Color.LightGray)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
                    .background(shape = RoundedCornerShape(12.dp), color = Color.LightGray)
            )
        }


        Box(
            modifier = Modifier
                .fillMaxWidth(0.75f)
                .height(24.dp)
                .background(shape = RoundedCornerShape(12.dp), color = Color.LightGray)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth(0.75f)
                .height(24.dp)
                .background(shape = RoundedCornerShape(12.dp), color = Color.LightGray)
        )

    }
}

@Preview(showBackground = true)
@Composable
private fun ProductCardPrev() {
    LpgGasAppTheme {
        ProductCard()
    }
}

