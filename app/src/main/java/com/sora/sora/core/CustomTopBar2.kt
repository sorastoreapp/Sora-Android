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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.sora.sora.ui.theme.SecondaryColor
import com.sora.sora.ui.theme.SecondaryColor100

@Composable
fun CustomAppBar(
    navController: NavController? = null,  // NavController is nullable, as the back button is optional
    title: String? = null,  // Title is nullable, it may or may not appear
    isBackButton: Boolean? = true,  // Title is nullable, it may or may not appear
    backgroundColor: Color = Color.White,  // Optional background color for the AppBar
    backIconColor: Color = SecondaryColor,  // Back icon color, default is black
    titleColor: Color = Color.Black,  // Title color, default is black
    onBackClick: (() -> Unit)? = null,  // onBackClick is nullable for flexible back action handling
    modifier: Modifier = Modifier  // To allow further customization
) {
    Box(
        modifier =  modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(top = 55.dp, start = 16.dp, end = 16.dp),
    ) {
        // Back button
        if (isBackButton == true) {
            Row(
                modifier = Modifier
                    .clickable {
                        if (onBackClick == null) {
                            navController?.popBackStack()
                        } else {
                            onBackClick()
                        }
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
              if(isBackButton)  Box(
                    modifier = Modifier
                        .size(45.dp)
                        .clip(CircleShape)
                        .background(SecondaryColor100)

                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = backIconColor,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(24.dp)
                    )
                }
            }
        }

        // Title (only shown if title is not null)
        title?.let {
            CustomMontserratText(
                text = it,

                    color = if (titleColor != null) titleColor else Color.Black,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight(600),

                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(start = if (onBackClick != null) 50.dp else 0.dp)  // Add offset for back button if present
                    .padding(end = 40.dp),

            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCustomAppBar() {
    CustomAppBar(
        navController = null,
        title = "Welcome",
        onBackClick = { /* Handle back click */ }
    )
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

