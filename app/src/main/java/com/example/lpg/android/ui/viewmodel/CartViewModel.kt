package com.example.lpg.android.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lpg.android.data.model.CartObject
import com.example.lpg.android.data.model.GasItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CartViewModel : ViewModel() {


    private val _cartItems = MutableStateFlow<List<CartObject>>(emptyList())
    val cartItems: StateFlow<List<CartObject>> = _cartItems.asStateFlow()

    private val _cartTotal = MutableStateFlow(0.0)
    val cartTotal: StateFlow<Double> = _cartTotal.asStateFlow()

    /**
     * Add item to cart or increment count if already present
     * */
    fun addItem(item: GasItem) {
        val existingItem = _cartItems.value.find { it.item.id == item.id }
        if (existingItem != null) {
            val updatedItems = _cartItems.value.map {
                if (it.item.id == item.id) it.copy(count = it.count + 1) else it
            }
            _cartItems.value = updatedItems
        } else {
            _cartItems.value += CartObject(item)
        }
        calculateCartTotal()
    }

    /**
     * Remove item from cart or decrement count if count > 1
     * */
    fun removeItem(item: GasItem) {
        val existingItem = _cartItems.value.find { it.item.id == item.id }
        if (existingItem != null) {
            val updatedItems = _cartItems.value.mapNotNull {
                if (it.item.id == item.id) {
                    // Decrement count or remove if count becomes 0
                    val newCount = it.count - 1
                    if (newCount > 0) it.copy(count = newCount) else null
                } else {
                    it
                }
            }
            _cartItems.value = updatedItems
        }
        calculateCartTotal()
    }

    fun clearCart() {
        _cartItems.value = emptyList()
        calculateCartTotal()
    }

    /**
     * Calculate the total cost of items in the cart and update the
     */
    private fun calculateCartTotal() {
        val total = _cartItems.value.sumOf { it.item.price * it.count }
        _cartTotal.value = total
    }


    @Suppress("UNCHECKED_CAST")
    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CartViewModel() as T
        }
    }
}
