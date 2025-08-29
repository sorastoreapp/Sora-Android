package com.sora.sora.core.controller

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

// Singleton controller that holds the global state
object GlobalController {
    // Use mutableStateOf to hold and observe the selected index value
    private var _selectedIndex = mutableStateOf(0)

    // Provide a public getter to access the selectedIndex value
    var selectedIndex: State<Int> = _selectedIndex

    // Provide a public setter to update the selectedIndex value
    fun updateSelectedIndex(value: Int) {
        _selectedIndex.value = value
    }
}
