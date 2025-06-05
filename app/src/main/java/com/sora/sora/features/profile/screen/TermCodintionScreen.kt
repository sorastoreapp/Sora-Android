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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sora.sora.R

@Composable
fun TermsAndConditionsScreen() {
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
                Text(
                    text = "Terms & Conditions",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Introduction section
            Text(
                text = "Introduction",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Welcome to Sora Store, your trusted e-commerce platform in Kuwait. By accessing or using our mobile application, you agree to comply with and be bound by these Terms and Conditions. These terms govern your use of the app and establish your legal rights and obligations. Please read them thoroughly before using the application.",
                fontSize = 14.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Acceptance of terms section
            Text(
                text = "Acceptance of Terms",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "By using Sora Store, you confirm that you have read, understood, and agreed to these Terms and Conditions, along with our Privacy Policy. If you disagree with any part of these terms, please refrain from using our application.",
                fontSize = 14.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Eligibility section
            Text(
                text = "Eligibility",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "• You must be at least 18 years of age to use this application. Users under 18 may only use the app with parental consent.\n" +
                        "• The application is designed for residents of Kuwait and is subject to the laws and regulations of Kuwait.\n" +
                        "• Users are responsible for ensuring that their use of the application complies with local laws and regulations.",
                fontSize = 14.sp,
                color = Color.Black
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
                .height(200.dp), // Adjust the height as needed
            contentScale = ContentScale.Crop
        )
    }
}
