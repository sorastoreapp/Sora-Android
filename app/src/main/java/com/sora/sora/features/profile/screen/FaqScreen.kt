package com.sora.sora.features.profile.screen

import android.util.Log
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
fun FaqScreen() {
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
                    text = "FAQ'S",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Introduction section
            CustomMontserratText(
                text = "What is Sora?",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            CustomMontserratText(
                text = "Sora is a trusted e-commerce platform that sells toys, kids' products, and accessories.",
                fontSize = 14.sp,
                color = AppTextGray
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Acceptance of Privacy Policy section
            CustomMontserratText(
                text = "Where does Sora operate?",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            CustomMontserratText(
                text = "Currently, Sora is designed for residents of Kuwait and follows the local laws and regulations.",
                fontSize = 14.sp,
                color = AppTextGray
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Eligibility section
            CustomMontserratText(
                text = "Who can use the Sora app?",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            CustomMontserratText(
                text = "• You must be at least 18 years old to use the app independently.\n" +
                        "• Users under 18 can use the app with parental consent.\n"+
                        "• The app is intended for individuals aged 12 and above (as per the privacy policy). \n",

                fontSize = 14.sp,
                color = AppTextGray
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Information We Collect section
            CustomMontserratText(
                text = "How do I create an account?",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            CustomMontserratText(
                text = "Simply download the Sora app, sign up with your details, and start shopping!",
                fontSize = 14.sp,
                color = AppTextGray
            )

            Spacer(modifier = Modifier.height(16.dp))
            // Information We Collect section
            CustomMontserratText(
                text = "How do I place an order?",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            CustomMontserratText(
                text = "Browse products, add items to your cart, and proceed to checkout with a secure payment option.",
                fontSize = 14.sp,
                color = AppTextGray
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomMontserratText(
                text = "What payment methods does Sora accept?",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            CustomMontserratText(
                text = "Sora supports various payment methods, including credit/debit cards and other secure online payment options.",
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
