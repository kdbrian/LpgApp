package com.example.lpg.android.ui.screen.checkout

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lpg.android.data.model.Address
import com.example.lpg.android.data.model.PaymentMethod
import com.example.lpg.android.ui.nav.AddressInfo
import com.example.lpg.android.ui.theme.LpgGasAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckOutScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {

    val selectedPaymentMethod = remember { mutableIntStateOf(0) }
    val paymentMethod = remember {
        derivedStateOf {
            PaymentMethod.paymentMethods[selectedPaymentMethod.intValue]
        }
    }
    val pickupLocation = remember { mutableStateOf<Address>(Address.default) }


    Column(
        modifier = modifier.fillMaxSize()
    ) {

        CenterAlignedTopAppBar(
            title = {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Checkout")
                    Text(
                        text = "Fill in your adress and payment details below",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            },
            navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        )

        Spacer(Modifier.height(32.dp))

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {

                Text(
                    text = "Summary",
                    style = MaterialTheme.typography.labelLarge.copy(fontSize = 20.sp)
                )

                Text(text = "Total : ", style = MaterialTheme.typography.labelMedium)
                Text(text = "Shipping Fee : ", style = MaterialTheme.typography.labelMedium)

            }


            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {

                Text(
                    text = "Payment Method",
                    style = MaterialTheme.typography.labelLarge.copy(fontSize = 20.sp)
                )

                Surface(
                    onClick = {},
                    shape = RoundedCornerShape(12.dp), border = BorderStroke(
                        color = Color.LightGray,
                        width = 1.dp
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = Color.Transparent
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Image(
                            painter = painterResource(paymentMethod.value.icon),
                            contentDescription = paymentMethod.value.method,
                            modifier = Modifier.size(50.dp)
                        )


                        Text(
                            text = paymentMethod.value.method,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )

                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                            contentDescription = null
                        )
                    }

                }


            }

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {

                Text(
                    text = "Delivery Adress",
                    style = MaterialTheme.typography.labelLarge.copy(fontSize = 20.sp)
                )

                Surface(
                    onClick = {
                        navController.navigate(AddressInfo)
                    },
                    shape = RoundedCornerShape(12.dp), border = BorderStroke(
                        color = Color.LightGray,
                        width = 1.dp
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = Color.Transparent
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Image(
                            imageVector = Icons.Rounded.LocationOn,
                            contentDescription = paymentMethod.value.method,
                            modifier = Modifier.size(35.dp)
                        )


                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "Pick address",
                                style = MaterialTheme.typography.titleMedium
                            )

                            Text(
                                text = pickupLocation.value.toString(),
                                style = MaterialTheme.typography.bodySmall,
                                textAlign = TextAlign.Center
                            )
                        }

                        Icon(
                            imageVector = Icons.Rounded.Edit,
                            contentDescription = null
                        )
                    }
                }

            }


            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Complete")

            }

        }

    }

}

@Preview(showBackground = true)
@Composable
private fun CheckOutScreenPrev() {
    LpgGasAppTheme {
        CheckOutScreen(navController = rememberNavController())
    }
}
