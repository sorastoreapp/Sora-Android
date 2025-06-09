package com.sora.sora.features.profile.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sora.sora.R
import com.sora.sora.core.customText.CustomMontserratText
import com.sora.sora.ui.theme.AppTextGray

@Composable
fun AboutUsScreen() {
    val context = LocalContext.current
    var showBackPressed by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(Color.White) // Background for the entire screen
    ) {
        // Column for text content with vertical scrolling enabled
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()) // Makes the content scrollable
        ) {
            // Back Button Section
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { showBackPressed = true }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier
                            .background(Color.Gray.copy(alpha = 0.2f), shape = CircleShape)
                            .padding(10.dp)
                    )
                }
                Spacer(modifier = Modifier.width(48.dp))
                CustomMontserratText(
                    text = "About Us",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Introduction section
            CustomMontserratText(
                text = "About Us – Sora",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            CustomMontserratText(
                text = "Welcome to Sora, your go-to destination for premium toys, kids' products, and accessories! We are dedicated to bringing joy and excitement to children while ensuring convenience and reliability for parents.\n ",
                fontSize = 14.sp,
                color = AppTextGray
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Why Choose Sora? section
            CustomMontserratText(
                text = "Why Choose Sora?",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            CustomMontserratText(
                text = "• Wide Range of Products – From fun-filled toys to must-have kids' accessories, we have it all!\n\n" +
                        "• Safe & Trusted – All our products meet safety standards to ensure your child’s well-being.\n\n" +
                        "• Seamless Shopping – Enjoy a hassle-free shopping experience with easy ordering, secure payments, and fast delivery.\n\n" +
                        "• Customer-Centric Approach – We prioritize customer satisfaction, offering excellent support and smooth returns.",
                fontSize = 14.sp,
                color = AppTextGray
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        // Background image at the bottom
        Image(
            painter = painterResource(id = R.drawable.img_bg_bottom_transparent), // Replace with your image
            contentDescription = "Background",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(300.dp), // Adjust the height as needed
            contentScale = ContentScale.Fit
        )
    }
}
