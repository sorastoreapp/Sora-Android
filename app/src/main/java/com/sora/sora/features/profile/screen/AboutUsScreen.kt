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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sora.sora.R
import com.sora.sora.core.CustomAppBar
import com.sora.sora.core.customText.CustomMontserratText
import com.sora.sora.core.navigations.NavigationManager.navController
import com.sora.sora.ui.theme.AppTextGray

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutUsScreen() {
    val context = LocalContext.current
    var showBackPressed by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()

            .background(Color.White) // Background for the entire screen
    ) {
        CustomAppBar(
            title = "About Us",
            onBackClick = {
                // Handle back click, navigate back or pop from the navigation stack
                navController.popBackStack()
            },
            modifier = Modifier.align(Alignment.TopCenter) // Aligning app bar at the top
        )
        // Column for text content with vertical scrolling enabled
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 56.dp) //
                .statusBarsPadding()
                .padding(horizontal = 20.dp, )
                .verticalScroll(rememberScrollState())//  Makes the content scrollable
        ) {
            // Back Button Section


            Spacer(modifier = Modifier.height(15.dp))

            // Introduction section
            CustomMontserratText(
                text = "About Us – Sora",
                fontSize = 16.sp,

                fontWeight = FontWeight(600),
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            CustomMontserratText(
                text = "Welcome to Sora, your go-to destination for premium toys, kids' products, and accessories! We are dedicated to bringing joy and excitement to children while ensuring convenience and reliability for parents.\n" +
                        "\n" +
                        "At Sora, we believe in providing high-quality, safe, and innovative products that cater to every child’s needs. Our carefully curated selection features the latest trends in toys, educational items, and everyday essentials for kids of all ages.",

                fontSize = 14.sp,
                color = AppTextGray,
                fontWeight = FontWeight(400)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Why Choose Sora? section
            CustomMontserratText(
                text = "Why Choose Sora?",
                fontSize = 16.sp,

                fontWeight = FontWeight(600),
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
