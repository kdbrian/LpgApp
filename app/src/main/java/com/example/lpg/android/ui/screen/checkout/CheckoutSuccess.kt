package com.example.lpg.android.ui.screen.checkout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.lpg.android.R
import com.example.lpg.android.ui.theme.LpgGasAppTheme
import kotlinx.coroutines.delay

@Composable
fun CheckoutSuccess(
    modifier: Modifier = Modifier,
    onFinish: () -> Unit = {},
) {

    val isLoading = remember { mutableStateOf(true) }
    LaunchedEffect(isLoading) {
        if (isLoading.value) {
            delay(2000)
            isLoading.value = false
        }
    }

    when (isLoading.value) {
        true -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column {
                    CircularProgressIndicator()
                    Spacer(Modifier.height(12.dp))
                    Text(text = "Loading...Please wait")
                }
            }
        }

        false -> {
            ConstraintLayout(modifier = modifier.fillMaxSize()) {
                val (icon, supportText, Btn) = createRefs()

                Image(
                    painter = painterResource(R.drawable.checkout_success),
                    contentDescription = "checkout success",
                    modifier = Modifier
                        .size(50.dp)
                        .constrainAs(icon) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                )

                Column(
                    modifier = Modifier.constrainAs(supportText) {
                        top.linkTo(icon.bottom, 24.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    Text(
                        text = "Your order has been placed successfully",
                        style = MaterialTheme.typography.titleLarge
                    )

                    Text(
                        text = "You will receive a confirmation shortly, Thank you for shopping with us",
                        style = MaterialTheme.typography.bodyMedium.copy(textAlign = TextAlign.Center)
                    )

                    TextButton(
                        modifier = Modifier,
                        onClick = onFinish
                    ) {
                        Text(text = "Complete")
                    }

                }


            }
        }
    }
}

@Preview
@Composable
private fun CheckoutSuccessPrev() {
    LpgGasAppTheme {
        CheckoutSuccess()
    }
}
