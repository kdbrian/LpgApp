package com.example.lpg.android.data.model

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Stable
import com.example.lpg.android.R

@Stable
sealed class PaymentMethod(
    val method: String,
    @DrawableRes val icon: Int,
) {

    data object MPESA : PaymentMethod(
        method = "Mpesa",
        icon = R.drawable.mpesa_logo
    )


    companion object {
        val paymentMethods = listOf(MPESA)
    }
}
