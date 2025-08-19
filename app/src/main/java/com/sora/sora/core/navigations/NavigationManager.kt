package com.sora.sora.core.navigations

import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

object NavigationManager {
    lateinit var navController: NavController

    fun navigateTo(route: String) {
        navController.navigate(route)
    }

    /**
     * Replacement push – clears the previous back stack entry.
     * Same as Flutter's pushReplacement
     */
    fun navigateAndReplace(route: String) {
        navController.navigate(route) {
            popUpTo(navController.currentDestination?.id ?: -1) { inclusive = true }
            launchSingleTop = true
        }
    }
    /** Clear *entire* backstack and navigate – same as pushReplacementAndRemoveUntil */
    fun navigateAndClearStack(route: String) {
        navController.navigate(route) {
            popUpTo(0) { inclusive = true }   // removes everything from backstack
            launchSingleTop = true
        }
    }

}


//---------------how to use----------------
// navController.navigate(Dest.DashBoardScreen::class.toRoute())
// navController.navigateAndReplace(Dest.DashBoardScreen::class.toRoute()) //for push replacement
// NavigationManager.navigateAndClearStack(Dest.SignIn::class.toRoute())


