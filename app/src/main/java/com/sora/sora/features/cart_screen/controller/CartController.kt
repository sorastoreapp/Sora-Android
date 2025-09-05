package com.sora.sora.features.cart_screen.controller

import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CartController {

    // StateFlow for whether the cart is empty or not
    private val _isEmptyStateTrue = MutableStateFlow(false)
    val isEmptyStateTrue: StateFlow<Boolean> get() = _isEmptyStateTrue

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> get() = _isRefreshing

    // Simulate refreshing process
    suspend fun refreshCart() {
        _isRefreshing.value = true
        delay(1000) // Simulate network delay
        _isEmptyStateTrue.value = !_isEmptyStateTrue.value  // Toggle cart empty state
        _isRefreshing.value = false
    }
}
