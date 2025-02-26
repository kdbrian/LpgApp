package com.example.lpg.android.data.local.repo

import androidx.lifecycle.LiveData
import com.example.lpg.android.data.local.dao.OrdersDao
import com.example.lpg.android.data.local.model.Order
import com.example.lpg.android.domain.repository.OrderRepo

class OrderRepository(private val ordersDao: OrdersDao) : OrderRepo {

    override val orders: LiveData<List<Order>> = ordersDao.getAllOrders()

    override suspend fun insertOrder(order: Order) {
        ordersDao.insertOrder(order)
    }

    override suspend fun updateOrder(order: Order) {
        ordersDao.updateOrder(order)
    }

    override suspend fun deleteOrder(order: Order) {
        ordersDao.deleteOrder(order)
    }

    override suspend fun deleteAllOrders() {
        ordersDao.deleteAllOrders()
    }
}