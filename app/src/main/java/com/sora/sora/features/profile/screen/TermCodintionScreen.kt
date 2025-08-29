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
import androidx.compose.ui.graphics.ColorFilter
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
import com.sora.sora.ui.theme.AppGray
import com.sora.sora.ui.theme.AppTextGray

@Preview(showBackground = true)
@Composable
fun TermsAndConditionsScreen() {
    val context = LocalContext.current
    var showBackPressed by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()

            .background(Color.White) // Background for the entire screen
    ) {
        CustomAppBar(
            title = "Terms & Conditions",
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
                .padding(top = 76.dp) //

                .padding(horizontal = 20.dp, )
                .verticalScroll(rememberScrollState())//  Makes the content scrollable
        ) {
            // Back Button Section


            Spacer(modifier = Modifier.height(vFactor(45)))
            QuestionAnswer(
                question = "Introduction",
                answer = "Welcome to Sora Store, your trusted e-commerce platform in Kuwait. By accessing or using our mobile application, you agree to comply with and be bound by these Terms and Conditions. These terms govern your use of the app and establish your legal rights and obligations. Please read them thoroughly before using the application."
            )
            QuestionAnswer(
                question = "Acceptance of Terms",
                answer = "By using Sora Store, you confirm that you have read, understood, and agreed to these Terms and Conditions, along with our Privacy Policy. If you disagree with any part of these terms, please refrain from using our application."
            )
            QuestionAnswer(
                question = "Eligibility",
                answer = "• You must be at least 18 years of age to use this application. Users under 18 may only use the app with parental consent.\n" +
                        "• The application is designed for residents of Kuwait and is subject to the laws and regulations of Kuwait.\n" +
                        "• Users are responsible for ensuring that their use of the application complies with local laws and regulations."
            )





            // Acceptance of terms section



        }
        BackgrountOtherImage(
            modifier = Modifier.align(Alignment.BottomCenter)
                .fillMaxWidth()
        )


    }
}
@Composable
fun BackgrountOtherImage(
    modifier: Modifier = Modifier,
){
    Image(
        painter = painterResource(id = R.drawable.img_bg_bottom_transparent), // Replace with your image
        contentDescription = "Background",
        modifier = modifier,
//        modifier = Modifier
//            .align(Alignment.BottomCenter)
//            .fillMaxWidth(),
        // .height(300.dp), // Adjust the height as needed
        contentScale = ContentScale.FillWidth
    )
}
