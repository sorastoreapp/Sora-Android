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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sora.sora.R
import com.sora.sora.core.customText.CustomMontserratText
import com.sora.sora.ui.theme.AppGray
import com.sora.sora.ui.theme.AppTextGray

@Preview(showBackground = true)
@Composable
fun NotificationScreen() {
    var showBackPressed by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // Background for the entire screen
    ) {
        // Image Background
        Image(
            painter = painterResource(id = R.drawable.img_temp_notification_screen),
            contentDescription = "Notification Screen Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(top = 30.dp)
                .fillMaxWidth()
                .fillMaxHeight() // Adjust height as needed
        )

        // Column for text content above the image
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Spacer(modifier = Modifier.height(30.dp))

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
                    text = "Notifications",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }
    }
}


