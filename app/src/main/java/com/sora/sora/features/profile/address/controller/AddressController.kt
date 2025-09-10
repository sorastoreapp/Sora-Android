package com.sora.sora.features.profile.address.controller



import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.delay

class AddressController {

    // StateFlow for whether the cart is empty or not
    private val _isEmptyStateTrue = MutableStateFlow(false)
    val isEmptyStateTrue: StateFlow<Boolean> get() = _isEmptyStateTrue

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> get() = _isRefreshing

    // Simulate refreshing process
    suspend fun refreshAddress() {
        _isRefreshing.value = true
        delay(1000) // Simulate network delay


        _isEmptyStateTrue.value = !_isEmptyStateTrue.value

        _isRefreshing.value = false
    }
}

