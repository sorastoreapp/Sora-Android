package com.sora.sora.core
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sora.sora.R
import com.sora.sora.core.customText.CustomMontserratText
import com.sora.sora.core.navigations.NavigationManager.navController
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.sora.sora.ui.theme.SecondaryColor
import com.sora.sora.ui.theme.SecondaryColor100
import kotlinx.coroutines.delay
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember


@Composable
fun CustomAppBar(
    navController: NavController? = null,
    title: String? = null,
    isBackButton: Boolean = true,
    backgroundColor: Color = Color.White,
    backIconColor: Color = SecondaryColor,
    titleColor: Color = Color.Black,
    onBackClick: (() -> Unit)? = null,
    bottomPadding: Dp = 0.dp,
    modifier: Modifier = Modifier
) {
    // Prevent double-tap crashes
    var backEnabled by remember { mutableStateOf(true) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .statusBarsPadding()
            .padding(start = 16.dp, end = 16.dp, bottom = bottomPadding,top = 8.dp)

    ) {
        if (isBackButton) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Circular back button
                Box(
                    modifier = Modifier
                        .size(45.dp)
                        .clip(CircleShape)
                        .background(SecondaryColor100),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(
                        onClick = {
                            if (!backEnabled) return@IconButton
                            backEnabled = false

                            // Prefer custom handler if provided
                            if (onBackClick != null) {
                                onBackClick()
                            } else {
                                // popBackStack() returns false if nothing to pop
                                val popped = navController?.popBackStack() ?: false
                                if (!popped) navController?.navigateUp()
                            }

                            // Usually we navigate away; no need to re-enable.
                            // If you want the button to be usable again on the same screen,
                            // re-enable after a short delay:
                            // LaunchedEffect(Unit) {
                            //     delay(350)
                            //     backEnabled = true
                            // }
                        },
                        enabled = backEnabled
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = backIconColor,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }

        title?.let {
            CustomMontserratText(
                text = it,
                color = titleColor,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(end = 40.dp)
            )
        }
    }
}





@Composable
fun CustomTopBar2(
    title: String? = null, // Optional title, defaults to null (no title)
    space: Dp = 40.dp, // Optional title, defaults to null (no title)
    modifier: Modifier = Modifier, // Nullable modifier for the Row
    onBackClick: (() -> Unit)? = null // Optional callback for the back button
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth() // Ensures Row takes full width
    ) {
        // Back button on the left
        Image(
            painter = painterResource(id = R.drawable.img_back_circular),
            contentDescription = "Back",
            modifier = Modifier
                .size(40.dp)
                .pointerInput(Unit) {
                    // Custom touch handling for Skip Button without ripple effect
                    detectTapGestures {
                        // Navigate to next screen (skipping onboarding)
                        if(onBackClick != null) onBackClick?.invoke() else{
                            navController.popBackStack()  //
                        }
                    }
                }
        )

        // Spacer for the middle portion
        Spacer(modifier = Modifier.width(space)) // Space between back button and title

        // Center the title
        title?.let {
            CustomMontserratText(
                text = it,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.weight(1f) // Ensures title takes remaining space and is centered
            )
        }
    }
}

