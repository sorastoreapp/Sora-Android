package com.sora.sora.features.profile.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
fun PrivacyPolicyScreen() {
    val context = LocalContext.current
    var showBackPressed by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(Color.White) // Background for the entire screen
    ) {
        // Column for text content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
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
                    text = "Privacy Policies",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Introduction section
            CustomMontserratText(
                text = "Introduction",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            CustomMontserratText(
                text = "Welcome to Sora Store, a premier e-commerce platform in Kuwait. We are committed to protecting your privacy and ensuring the security of your personal data. This Privacy Policy explains how we collect, use, disclose, and safeguard your information when you use our mobile application. Please read this policy carefully to understand our practices.",
                fontSize = 14.sp,
                color = AppTextGray
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Acceptance of Privacy Policy section
            CustomMontserratText(
                text = "Acceptance of Privacy Policy",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            CustomMontserratText(
                text = "By using Sora Store, you consent to the collection and use of your information in accordance with this Privacy Policy. If you do not agree with the terms, please do not use the application.",
                fontSize = 14.sp,
                color = AppTextGray
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Eligibility section
            CustomMontserratText(
                text = "Eligibility",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            CustomMontserratText(
                text = "• The application is intended for individuals aged 12 years and above. Users under the age of 18 require parental or guardian consent to use the application.\n" +
                        "• By using the app, you confirm that you meet this age requirement and have the legal capacity to agree to these terms.",
                fontSize = 14.sp,
                color = AppTextGray
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Information We Collect section
            CustomMontserratText(
                text = "Information We Collect",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            CustomMontserratText(
                text = "We collect the following categories of information:",
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
