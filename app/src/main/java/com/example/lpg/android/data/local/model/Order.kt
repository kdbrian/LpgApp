package com.example.lpg.android.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.lpg.android.data.model.Address
import com.example.lpg.android.data.model.CartObject
import com.example.lpg.android.data.model.GasItem
import com.example.lpg.android.util.formatToDateTime
import java.time.LocalDateTime
import java.util.UUID
import kotlin.uuid.Uuid
import kotlin.uuid.toJavaUuid

@Entity(tableName = "orders")
data class Order(
    @PrimaryKey
    val orderId: String = UUID.randomUUID().toString(),
    val total: Double,
    val datePlaced: String = LocalDateTime.now().formatToDateTime(),
    val items: List<CartObject>,
    val paymentMethod: String,
    val address: Address
)
