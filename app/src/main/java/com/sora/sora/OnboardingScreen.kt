package com.sora.sora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sora.sora.R
import com.sora.sora.core.navigations.Dest
import com.sora.sora.core.navigations.toRoute
import com.sora.sora.ui.theme.PrimaryColor
import com.sora.sora.ui.theme.SoraTheme
import kotlinx.serialization.Serializable

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


//@Preview(showBackground = true)
@Composable
fun OnboardingScreen(navController: NavController) {
    val pages = listOf(
        R.drawable.onboarding1,
        R.drawable.onboarding2,
    )
    var currentPage by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectHorizontalDragGestures { change, dragAmount ->
                    if (dragAmount < -50 && currentPage < pages.lastIndex) {
                        currentPage++
                    } else if (dragAmount > 50 && currentPage > 0) {
                        currentPage--
                    }
                }
            }
    ) {
        Image(
            painter = painterResource(id = pages[currentPage]),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )

        Row(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 24.dp, bottom = 64.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            pages.forEachIndexed { index, _ ->
                Box(
                    modifier = Modifier
                        .size(width = if (index == currentPage) 20.dp else 6.dp, height = 6.dp)
                        .clip(CircleShape)
                        .background(if (index == currentPage) PrimaryColor else Color.LightGray)
                        .animateContentSize()
                )
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 24.dp, bottom = 64.dp)
//                    .offset(x = (-50).dp, y = 150                       .dp) // pushes up and left from center
                .size(48.dp)
                .clip(MaterialTheme.shapes.medium)
//                    .clip( )
                .background(PrimaryColor) // arrow button bg color
                .clickable {  if (currentPage < pages.lastIndex) {
                    currentPage++
                } else {
                    // Last page: navigate to WelcomeScreen using your sealed class routes
                    navController.navigate(Dest.Welcome::class.toRoute()) {
                        // Pop onboarding from backstack so user can't go back
                        popUpTo(Dest.OnboardingScreen::class.toRoute()) { inclusive = true }
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

//        if (currentPage < pages.lastIndex) {
//
//        }
    }
}
