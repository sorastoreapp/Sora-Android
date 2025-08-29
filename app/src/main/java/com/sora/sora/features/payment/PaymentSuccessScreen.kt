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

                .padding(top = 56.dp) //
                .statusBarsPadding()
                .padding(horizontal = 20.dp,)
                .align(Alignment.Center)

                .verticalScroll(rememberScrollState())//  Makes the content scrollable
        ) {


                CustomMontserratText(
                    text = "Payment Successful",
                    fontSize = 20.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,


                    )
            Spacer(modifier = Modifier.height(vFactor(20)))
            CustomMontserratText(
                    text = "Thank you! Your payment was successful",
                fontSize = 14.sp,

                fontWeight = FontWeight(400),
                color = AppTextGray,

                textAlign = TextAlign.Center,


                    )
            Spacer(modifier = Modifier.height(vFactor(20)))
            PriceRow(label = "Order No.", value = "OR1235NRM")
            Spacer(modifier = Modifier.height(vFactor(12)))
            PriceRow(label = "Transaction Id.", value = "TNR258MLR58")
            Spacer(modifier = Modifier.height(vFactor(12)))
            PriceRow(label = "Amount", value = "KD 12.00")

            Spacer(modifier = Modifier.height(vFactor(24)))
            CustomButton(
                label = "Done",
                onClick = { /* TODO: Handle update logic */ }
            )






        }

        // Background image at the bottom
        Image(
            painter = painterResource(id = R.drawable.img_payment_lady_bags), // Replace with your image
            contentDescription = "Background",

            modifier = Modifier
                .statusBarsPadding()
                .padding(top = vFactor(144))
                .width(195.86896.dp)
                .height(250.dp)
                .align(Alignment.TopCenter)

                .fillMaxWidth()


            , // Adjust the height as needed
            contentScale = ContentScale.FillWidth
        )
        Image(
            painter = painterResource(id = R.drawable.img_bg_top_transparent), // Replace with your image
            contentDescription = "Background",

            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
            //  .height(300.dp)
            , // Adjust the height as needed
            contentScale = ContentScale.FillWidth
        )
    }
}
