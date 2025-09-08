package com.sora.sora.features.profile.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
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
import com.sora.sora.core.vFactor
import com.sora.sora.ui.theme.AppTextGray
import com.sora.sora.ui.theme.SecondaryColor
import com.sora.sora.ui.theme.SecondaryColor100

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrivacyPolicyScreen()
{
    val context = LocalContext.current
    var showBackPressed by remember { mutableStateOf(false) }

    Scaffold (
        containerColor = Color.White,


     modifier = Modifier
         .background(Color.White)
         .padding(
             bottom = WindowInsets.systemBars
                 .asPaddingValues()
                 .calculateBottomPadding()
         )
         .fillMaxSize(),

        topBar = {
            CustomAppBar(
                title = "Privacy Policy",
                isBackButton = true,
                onBackClick = {
                    // Handle back click, navigate back or pop from the navigation stack
                    navController.popBackStack()
                },
                bottomPadding = 20.dp
            )
        }

    ) {
            paddingValues ->

        // Column for text content
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Background image at the bottom
            BackgrountOtherImage(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 70.dp) //
                    .padding(horizontal = 20.dp)
                    .verticalScroll(rememberScrollState())//  Makes the content scrollable
            ) {
                // Back Button Section


                Spacer(modifier = Modifier.height(vFactor(45)))
                QuestionAnswer(
                    question = "Introduction",
                    answer = "Welcome to Sora Store, a premier e-commerce platform in Kuwait. We are committed to protecting your privacy and ensuring the security of your personal data. This Privacy Policy explains how we collect, use, disclose, and safeguard your information when you use our mobile application. Please read this policy carefully to understand our practices."
                )
                QuestionAnswer(
                    question = "Acceptance of Privacy Policy",
                    answer = "By using Sora Store, you consent to the collection and use of your information in accordance with this Privacy Policy. If you do not agree with the terms, please do not use the application."
                )
                QuestionAnswer(
                    question = "Eligibility",
                    answer = "• The application is intended for individuals aged 12 years and above. Users under the age of 18 require parental or guardian consent to use the application.\n" +
                            "• By using the app, you confirm that you meet this age requirement and have the legal capacity to agree to these terms."
                )



                QuestionAnswer(
                    question = "Information We Collect",
                    answer = "We collect the following categories of information:"
                )


            }

        }


    }
}
