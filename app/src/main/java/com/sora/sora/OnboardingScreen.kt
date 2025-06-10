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
        OnboardingData(R.drawable.img_onboarding1,),
        OnboardingData(R.drawable.img_onboarding2, ),
        OnboardingData(R.drawable.img_onboarding3, ),
        OnboardingData(R.drawable.img_onboarding4,),
        OnboardingData(R.drawable.img_onboarding5, )
    )

    var currentPage by remember { mutableStateOf(0) }
    val pagerState = rememberPagerState(initialPage = currentPage)
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(state = pagerState, count = pages.size) { page ->
            val currentPageData = pages[page]

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                // Fade transition for the content
                AnimatedVisibility(
                    visible = true,
                    enter = fadeIn(animationSpec = tween(durationMillis = 500)),
                    exit = fadeOut(animationSpec = tween(durationMillis = 300))
                ) {
                    Image(
                        painter = painterResource(id = currentPageData.image),
                        contentDescription = "image_$currentPage",
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                    )
                }
            }
        }

        // Skip button on the top-right
                Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 50.dp, end = 16.dp)
                .pointerInput(Unit) {
                    // Custom touch handling for Skip Button without ripple effect
                    detectTapGestures {
                        // Navigate to next screen (skipping onboarding)
                        navController.navigate(Dest.Welcome::class.toRoute()) {
                            popUpTo(Dest.OnboardingScreen::class.toRoute()) { inclusive = true }
                        }
                    }
                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_skip),  // Use your PNG for Skip button
                contentDescription = "Skip",
                modifier = Modifier.size(70.dp)
            )
        }

        // Bottom section with Next and Skip buttons
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp)
        ) {
            if (pagerState.currentPage == pages.size - 1) {
                // Show 'Get Started' button on last page
                OutlinedButton(
                    onClick = {
                        navController.navigate("next_screen") // replace with the actual route
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp)
                        .height(48.dp),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(text = "Get Started")
                }
            } else {
                // Show Skip and Next buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextButton(onClick = {
//                        pagerState.currentPage = pages.size - 1
                    }) {
                        Text("Skip")
                    }

                    TextButton(onClick = {
                        if (pagerState.currentPage < pages.size - 1) {
//                            pagerState.currentPage++
                        }
                    }) {
                        Text("Next")
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 24.dp, top = 315.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            pages.forEachIndexed { index, _ ->
                Box(
                    modifier = Modifier
                        .size(width = if (index == pagerState.currentPage) 20.dp else 6.dp, height = 6.dp)
                        .clip(CircleShape)
                        .background(if (index == pagerState.currentPage) PrimaryColor else Color.LightGray)
                        .animateContentSize()
                )
            }
        }

        // Next Button (Arrow button) in the top-right
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 24.dp, top = 310.dp)
                .size(48.dp)
                .clip(MaterialTheme.shapes.medium)
                .background(PrimaryColor) // Arrow button background color
                .clickable {
                    // Move to the next page when Next button is clicked
                    coroutineScope.launch {
                        if (pagerState.currentPage < pages.size - 1) {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        } else {
                            // Navigate to the welcome screen when the last page is reached
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
                modifier = Modifier.size(24.dp)
            )
        }

    }
}




