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
fun AboutUsScreen()
{

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
                title = "About Us",
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
            )  {
                // Back Button Section

                Spacer(modifier = Modifier.height(45.dp))
                QuestionAnswer(
                    question = "About Us - Sora",
                    answer = "Sora is a premium e-commerce platform specializing in toys, kids' products, and accessories. We offer a wide range of high-quality items designed to bring joy and excitement to children while ensuring safety and convenience for parents.\n"+

                            "At Sora, we believe in providing high-quality, safe, and innovative products that cater to every child’s needs. Our carefully curated selection features the latest trends in toys, educational items, and everyday essentials for kids of all ages.",

                    )
                QuestionAnswer(
                    question = "Why Choose Sora?",
                    answer = "• Wide Range of Products – From fun-filled toys to must-have kids' accessories, we have it all!\n\n" +
                            "• Safe & Trusted – All our products meet safety standards to ensure your child’s well-being.\n\n" +
                            "• Seamless Shopping – Enjoy a hassle-free shopping experience with easy ordering, secure payments, and fast delivery.\n\n" +
                            "• Customer-Centric Approach – We prioritize customer satisfaction, offering excellent support and smooth returns.",
                )

                Spacer(modifier = Modifier.height(16.dp))
            }

        }


    }
}


/**
 * Composable function to display a question and its answer in the FAQ screen.
 *
 * @param question the question to be displayed
 * @param answer the answer to the question
 */

