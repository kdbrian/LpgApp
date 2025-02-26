package com.example.lpg.android.domain.repository

import androidx.lifecycle.LiveData
import com.example.lpg.android.data.local.model.Order

interface OrderRepo {
    val orders : LiveData<List<Order>>
    suspend fun insertOrder(order: Order)
    suspend fun updateOrder(order: Order)
    suspend fun deleteOrder(order: Order)
    suspend fun deleteAllOrders()
}