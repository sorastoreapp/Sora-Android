package com.sora.sora

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sora.sora.core.navigations.Dest
import com.sora.sora.core.navigations.toRoute
import com.sora.sora.ui.theme.PrimaryColor
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import androidx.compose.animation.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.layout.ContentScale
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


/**WORKING CODE WITHOUT SMOOTH TRANSITION**/
//@Composable
//fun OnboardingScreen(navController: NavController) {
//    val pages = listOf(
//        R.drawable.img_onboarding1,
//        R.drawable.img_onboarding2,
//        R.drawable.img_onboarding3,
//        R.drawable.img_onboarding4,
//        R.drawable.img_onboarding5,
//    )
//    var currentPage by remember { mutableStateOf(0) }
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .pointerInput(Unit) {
//                detectHorizontalDragGestures { change, dragAmount ->
//                    if (dragAmount < -70 && currentPage < pages.lastIndex) {
//                        currentPage++
//                    } else if (dragAmount > 70 && currentPage > 0) {
//                        currentPage--
//                    }
//                }
//            }
//    ) {
//        Image(
//            painter = painterResource(id = pages[currentPage]),
//            contentDescription = null,
//            modifier = Modifier.fillMaxSize()
//        )
//
//        Box(
//            modifier = Modifier
//                .align(Alignment.TopEnd)
//                .padding(top = 50.dp, end = 16.dp)
//                .pointerInput(Unit) {
//                    // Custom touch handling for Skip Button without ripple effect
//                    detectTapGestures {
//                        // Navigate to next screen (skipping onboarding)
//                        navController.navigate(Dest.Welcome::class.toRoute()) {
//                            popUpTo(Dest.OnboardingScreen::class.toRoute()) { inclusive = true }
//                        }
//                    }
//                }
//        ) {
//            Image(
//                painter = painterResource(id = R.drawable.img_skip),  // Use your PNG for Skip button
//                contentDescription = "Skip",
//                modifier = Modifier.size(70.dp)
//            )
//        }
//
//        Row(
//            modifier = Modifier
//                .align(Alignment.TopStart)
//                .padding(start = 24.dp, top = 315.dp),
//            horizontalArrangement = Arrangement.spacedBy(8.dp)
//        ) {
//            pages.forEachIndexed { index, _ ->
//                Box(
//                    modifier = Modifier
//                        .size(width = if (index == currentPage) 20.dp else 6.dp, height = 6.dp)
//                        .clip(CircleShape)
//                        .background(if (index == currentPage) PrimaryColor else Color.LightGray)
//                        .animateContentSize()
//                )
//            }
//        }
//
//        Box(
//            modifier = Modifier
//                .align(Alignment.TopEnd)
//                .padding(end = 24.dp, top = 310.dp)
////                    .offset(x = (-50).dp, y = 150                       .dp) // pushes up and left from center
//                .size(48.dp)
//                .clip(MaterialTheme.shapes.medium)
////                    .clip( )
//                .background(PrimaryColor) // arrow button bg color
//                .clickable {  if (currentPage < pages.lastIndex) {
//                    currentPage++
//                } else {
//                    // Last page: navigate to WelcomeScreen using your sealed class routes
//                    navController.navigate(Dest.Welcome::class.toRoute()) {
//                        // Pop onboarding from backstack so user can't go back
//                        popUpTo(Dest.OnboardingScreen::class.toRoute()) { inclusive = true }
//                    }
//                }
//                },
//            contentAlignment = Alignment.Center
//        ) {
//            Icon(
//                imageVector = Icons.Default.ArrowForward,
//                contentDescription = "Next",
//                tint = Color.White,
//                modifier = Modifier.size(24.dp)
//            )
//        }
//
////        if (currentPage < pages.lastIndex) {
////
////        }
//    }
//}

//@Serializable
//class OnboardingActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            SoraTheme {
//                Surface(modifier = Modifier.fillMaxSize()) {
//                    OnboardingScreen()
//                }
//            }
//        }
//    }
//}

/**Showing white image and transition not working **/


data class OnboardingData(
    val image: Int,
)

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(navController: NavController) {
    val pages = listOf(
        OnboardingData(R.drawable.img_onboarding1),
        OnboardingData(R.drawable.img_onboarding2)
    )

    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
    ) {
        HorizontalPager(state = pagerState, count = pages.size) { page ->
            val currentPageData = pages[page]

            Image(
                painter = painterResource(id = currentPageData.image),
                contentDescription = "image_$page",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        // Bottom row containing indicators and next button
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(bottom = 40.dp, start = 24.dp, end = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Page indicators
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                pages.forEachIndexed { index, _ ->
                    Box(
                        modifier = Modifier
                            .width(if (index == pagerState.currentPage) 40.dp else 20.dp)
                            .height(if (index == pagerState.currentPage) 10.dp else 8.dp)
                            .clip(CircleShape)
                            .background(if (index == pagerState.currentPage) PrimaryColor else Color.LightGray)
                            .animateContentSize()
                    )
                }
            }

            // Next Button (Arrow button)
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(MaterialTheme.shapes.medium)
                        .background(PrimaryColor)
                        .clickable {
                            coroutineScope.launch {
                                if (pagerState.currentPage < pages.size - 1) {
                                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                } else {
                                    navController.navigate(Dest.Welcome::class.toRoute()) {
                                        popUpTo(Dest.OnboardingScreen::class.toRoute()) { inclusive = true }
                                    }
                                }
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Next",
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                }

        }
    }
}




