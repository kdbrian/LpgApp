package com.example.lpg.android.data.model


import androidx.compose.runtime.Stable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Stable
data class GasItem(
    val currency: String,
    val id: String,
    @SerialName("image_url") val imageUrl: String,
    @SerialName("name") val name: String,
    @SerialName("price") val price: Double,
)