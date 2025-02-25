package com.example.lpg.android.data.model


import androidx.compose.runtime.Stable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.random.Random
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
import kotlin.uuid.toJavaUuid

@Serializable
@Stable
data class GasItem(
    val currency: String,
    val id: String,
    @SerialName("image_url") val imageUrl: String,
    @SerialName("name") val name: String,
    @SerialName("price") val price: Double,
){

    companion object{
        @OptIn(ExperimentalUuidApi::class)
        val default = GasItem(
                currency = "KES",
                id = Uuid.random().toJavaUuid().toString().split("-").first(),
                imageUrl = "https://thispersondoesnotexist.com/",
                name = "12Kg",
                price = Random.nextDouble(299.99, 999.99)
            )

        @OptIn(ExperimentalUuidApi::class)
        val defaultItems = List(10){
            GasItem(
                currency = "KES",
                id = Uuid.random().toJavaUuid().toString().split("-").first(),
                imageUrl = "https://thispersondoesnotexist.com/",
                name = "12Kg",
                price = Random.nextDouble(299.99, 1899.99)
            )

        }
    }
}