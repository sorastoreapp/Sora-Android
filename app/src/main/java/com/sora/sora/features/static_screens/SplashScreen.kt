package com.sora.sora.features.static_screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.delay
import com.sora.sora.R
import com.sora.sora.core.navigations.Dest
import com.sora.sora.core.navigations.NavigationManager
import com.sora.sora.core.navigations.toRoute

@Composable
fun SplashScreen() {
    // Use LaunchedEffect to handle the delay and navigation
    LaunchedEffect(Unit) {
        // Delay for 2 seconds before navigating
        delay(2000)
       var navController =  NavigationManager.navController;
        navController.navigate(Dest.OnboardingScreen::class.toRoute())

    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_splash_screen),
            contentDescription = "Splash Screen",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}
