package com.example.lpg.android.data.model

import android.annotation.SuppressLint
import androidx.compose.runtime.Stable
import com.example.lpg.android.util.formatToDateTime
import java.time.LocalDateTime
import kotlin.random.Random
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
import kotlin.uuid.toJavaUuid

@Stable
data class OrderModel @OptIn(ExperimentalUuidApi::class) constructor(
    val orderId: String = Uuid.random().toJavaUuid().toString(),
    val total: Double,
    @SuppressLint("NewApi") val datePlaced: String = LocalDateTime.now().formatToDateTime(),
    val items: Int,
) {
    companion object {
        val default = OrderModel(
            total = Random.nextDouble(599.99, 2999.99),
            items = 1,
        )

        val defaultItems = List(12) {
            OrderModel(
                total = Random.nextDouble(599.99, 2999.99),
                items = Random.nextInt(1, 10)
            )
        }
    }
}
