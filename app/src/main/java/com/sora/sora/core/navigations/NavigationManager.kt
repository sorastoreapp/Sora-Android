package com.sora.sora.core.navigations

import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

object NavigationManager {
    lateinit var navController: NavController

    fun navigateTo(route: String) {
        navController.navigate(route)
    }
}



