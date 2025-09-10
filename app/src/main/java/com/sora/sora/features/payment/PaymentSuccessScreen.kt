package com.sora.sora.features.profile.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sora.sora.R
import com.sora.sora.core.CustomAppBar
import com.sora.sora.core.controller.GlobalController
import com.sora.sora.core.customButtons.CustomButton
import com.sora.sora.core.customText.CustomMontserratText
import com.sora.sora.core.navigations.Dest
import com.sora.sora.core.navigations.NavigationManager.navController
import com.sora.sora.core.vFactor
import com.sora.sora.features.dashboard.PriceRow
import com.sora.sora.ui.theme.AppTextGray

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentSuccessScreen() {
    val context = LocalContext.current
    var showBackPressed by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // Background for the entire screen
    ) {
        // Column for text content with vertical scrolling enabled
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .align(Alignment.Center)
                .verticalScroll(rememberScrollState()) // Makes the content scrollable
                .padding(top = 265.dp) // Add padding at the bottom for spacing
        ) {
            // Horizontally center the first text element
            CustomMontserratText(
                text = "Payment Successful",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center,  // Centers the text horizontally
                modifier = Modifier.fillMaxWidth()  // Ensures the text takes full width of its container
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Horizontally center the second text element
            CustomMontserratText(
                text = "Thank you! Your payment was successful",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Gray,
                textAlign = TextAlign.Center,  // Centers the text horizontally
                modifier = Modifier.fillMaxWidth()  // Ensures the text takes full width of its container
            )

            Spacer(modifier = Modifier.height(18.dp))

            PriceRow(label = "Order No.", value = "OR1235NRM")
            Spacer(modifier = Modifier.height(5.dp))
            PriceRow(label = "Transaction Id.", value = "TNR258MLR58")
            Spacer(modifier = Modifier.height(5.dp))
            PriceRow(label = "Amount", value = "KD 12.00")

            Spacer(modifier = Modifier.height(24.dp))

            CustomButton(
                label = "Done",
                onClick = {
                    GlobalController.updateSelectedIndex(0)
                    navController.popBackStack()
                }
            )
        }

        // Background image at the bottom
        Image(
            painter = painterResource(id = R.drawable.img_payment_lady_bags), // Replace with your image
            contentDescription = "Background",
            modifier = Modifier
                .statusBarsPadding()
                .padding(top = 144.dp)
                .width(195.dp) // Adjust the width
                .height(250.dp) // Adjust the height
                .align(Alignment.TopCenter)
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )

        // Another top background image (transparent overlay or gradient)
        Image(
            painter = painterResource(id = R.drawable.img_bg_top_transparent), // Replace with your image
            contentDescription = "Background",
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
    }
}


