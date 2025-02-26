package com.example.lpg.android.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.lpg.android.data.local.model.Order

@Dao
interface OrdersDao {

    @Query("SELECT * FROM orders")
    fun getAllOrders(): LiveData<List<Order>>

    @Insert
    fun insertOrder(order: Order)

    @Delete
    fun deleteOrder(order: Order)

    @Update
    fun updateOrder(order: Order)

    @Query("SELECT * FROM orders WHERE orderId = :orderId")
    fun getOrderById(orderId: Int): Order?

    @Query("DELETE FROM orders")
    fun deleteAllOrders()

}