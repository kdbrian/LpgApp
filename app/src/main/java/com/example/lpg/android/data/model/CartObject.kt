package com.example.lpg.android.data.model

import androidx.compose.runtime.Stable

@Stable
data class CartObject(
    val item : GasItem,
    var count : Int = 1
)