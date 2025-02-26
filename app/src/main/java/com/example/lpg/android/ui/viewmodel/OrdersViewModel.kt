package com.example.lpg.android.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.lpg.android.data.local.dao.OrdersDao
import com.example.lpg.android.data.local.model.Order
import com.example.lpg.android.data.local.repo.OrderRepository
import com.example.lpg.android.domain.repository.OrderRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrdersViewModel(private val repository: OrderRepo) : ViewModel() {

    val orders = repository.orders

    private val ioDispatcher = Dispatchers.IO

    fun addOrder(order: Order) {
        viewModelScope.launch(ioDispatcher){
            repository.insertOrder(order)
        }
    }

    fun deleteOrder(order: Order) {
        viewModelScope.launch(ioDispatcher){
            repository.deleteOrder(order)
        }
    }

    fun clearAllOrders() {
        viewModelScope.launch(ioDispatcher){
            repository.deleteAllOrders()
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val dao : OrdersDao) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(OrdersViewModel::class.java)) {
                return OrdersViewModel(OrderRepository(dao)) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}