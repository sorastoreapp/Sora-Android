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
fun FaqScreen() {
    val context = LocalContext.current
    var showBackPressed by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()

            .background(Color.White) // Background for the entire screen
    ) {

        CustomAppBar(
            title = "FAQ'S",
            onBackClick = {
                // Handle back click, navigate back or pop from the navigation stack
                navController.popBackStack()
            },
            modifier = Modifier.align(Alignment.TopCenter) // Aligning app bar at the top
        )

        // Column for text content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 56.dp,) //
                .statusBarsPadding()
                .padding(horizontal = 20.dp, )
                .verticalScroll(rememberScrollState())// Makes the content scrollable

        ) {
            // Back Button Section
            Spacer(modifier = Modifier.height(15.dp)) // Spacing before content
            QuestionAnswer(question = "What is Sora?", answer = "Sora is a trusted e-commerce platform that sells toys, kids' products, and accessories.")


            QuestionAnswer(question = "Where does Sora operate?", answer = "Currently, Sora is designed for residents of Kuwait and follows the local laws and regulations.")

       QuestionAnswer(question="Who can use the Sora app?", answer="• You must be at least 18 years old to use the app independently.\n" +
                        "• Users under 18 can use the app with parental consent.\n"+
                        "• The app is intended for individuals aged 12 and above (as per the privacy policy).\n")

       QuestionAnswer(question="How do I create an account?", answer="Simply download the Sora app, sign up with your details, and start shopping!")


QuestionAnswer(question="How do I place an order?", answer="Browse products, add items to your cart, and proceed to checkout with a secure payment option.")

QuestionAnswer(question="What payment methods does Sora accept?", answer="Sora supports various payment methods, including credit/debit cards and other secure online payment options.")

            Spacer(modifier = Modifier.height(100.dp)) // extra bottom padding

        }

        // Background image at the bottom
        BackgrountOtherImage(
            modifier = Modifier.align(Alignment.BottomCenter)
                .fillMaxWidth()
        )
    }
}

/**
 * Composable function to display a question and its answer in the FAQ screen.
 *
 * @param question the question to be displayed
 * @param answer the answer to the question
 */

@Composable
fun QuestionAnswer(question:String, answer:String) {
    Column(){
        CustomMontserratText(
            text = question,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Black
        )

        CustomMontserratText(
            text = answer,
            fontSize = 18.sp,
            color = Color.Black,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}
