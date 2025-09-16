import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.*
import com.sora.sora.R
import com.sora.sora.core.navigations.Dest
import com.sora.sora.core.navigations.NavigationManager
import com.sora.sora.core.navigations.toRoute
import kotlinx.coroutines.delay

//@Composable
//fun SplashScreen() {
//    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash_json))
//    val progress by animateLottieCompositionAsState(
//        composition = composition,
//        iterations = 1 // Play the animation only once
//    )
//
//    // Navigation effect
//    LaunchedEffect(progress) {
//        if (progress == 1f) { // Animation has finished
//            NavigationManager.navController.navigate(Dest.OnboardingScreen::class.toRoute()) {
//                popUpTo(Dest.SplashScreen::class.toRoute()) { inclusive = true }
//            }
//        }
//    }
//
//    Box(
//        modifier = Modifier.fillMaxSize().background(Color.White),
//        contentAlignment = Alignment.Center
//    ) {
//        LottieAnimation(
//            composition = composition,
//            progress = progress
//        )
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun PreviewSplashScreen() {
//    SplashScreen()
//}
@Composable
fun SplashScreen() {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.splash_json)
    )
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = 1 // play only once
    )

    // Delay navigation by 2 seconds
    LaunchedEffect(Unit) {
        delay(2000)
        NavigationManager.navController.navigate(Dest.OnboardingScreen::class.toRoute()) {
            popUpTo(Dest.SplashScreen::class.toRoute()) { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            composition = composition,
            progress = progress
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSplashScreen() {
    SplashScreen()
}


/****/
//package com.sora.sora.features.static_screens
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.tooling.preview.Preview
//import com.airbnb.lottie.compose.*
//import com.sora.sora.R
//
//@Composable
//fun SplashScreen() {
//    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash_json))
//    val progress by animateLottieCompositionAsState(composition)
//
//    Box(
//        modifier = Modifier.fillMaxSize().background(Color.White),
//        contentAlignment = Alignment.Center
//    ) {
//        LottieAnimation(
//            composition = composition,
//            progress = progress ,
//        )
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun PreviewSplashScreen() {
//    SplashScreen()
//}


/****/
//@Composable
//fun SplashScreen() {
//    // Use LaunchedEffect to handle the delay and navigation
//    LaunchedEffect(Unit) {
//        // Delay for 2 seconds before navigating
//        delay(2000)
//       var navController =  NavigationManager.navController;
//        navController.navigate(Dest.OnboardingScreen::class.toRoute())
//
//    }
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.White)
//    ) {
//        Image(
//            painter = painterResource(id = R.drawable.img_splash_screen),
//            contentDescription = "Splash Screen",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier.fillMaxSize()
//        )
//    }
//}


