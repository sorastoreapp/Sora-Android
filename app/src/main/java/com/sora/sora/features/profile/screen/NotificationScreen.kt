package com.sora.sora.features.profile.screen


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
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
import com.sora.sora.core.hFactor
import com.sora.sora.core.navigations.NavigationManager.navController
import com.sora.sora.core.vFactor
import com.sora.sora.ui.theme.AppGray
import com.sora.sora.ui.theme.AppTextGray
import com.sora.sora.ui.theme.SecondaryColor

@Preview(showBackground = true)
@Composable
fun NotificationScreen() {
    var showBackPressed by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = hFactor(20))// Background for the entire screen
    ) {
        CustomAppBar(
            title = "Notifications",
            onBackClick = {
                // Handle back click, navigate back or pop from the navigation stack
                navController.popBackStack()
            },

            )
        // Image Background
        Spacer(modifier = Modifier.height(8.dp))

        // Notifications List
        val notifications = listOf(
            NotificationItem(
                icon = Icons.Default.ShoppingCart,
                title = "Big Savings Alert!",
                description = "Get up to 50% off on your favorite toys & kids' accessories. Hurry, limited time only",
                time = "10 hr ago"
            ),
            NotificationItem(
                icon = Icons.Default.ShoppingCart,
                title = "Order Delivered!",
                description = "Yay! Your order #12345 has been delivered. We hope your little one loves it! Let us know what you think!",
                time = "Yesterday"
            ),
            NotificationItem(
                icon = Icons.Default.FavoriteBorder,
                title = "New Arrivals Just Landed!",
                description = "Discover the latest toys & trendy accessories for your little ones. Shop now!",
                time = "Yesterday"
            )
        )

        LazyColumn (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(notifications) { item ->
                NotificationCard(item)
            }
        }
    }
}

data class NotificationItem(
    val icon: ImageVector,
    val title: String,
    val description: String,
    val time: String
)

@Composable
fun NotificationCard(item: NotificationItem) {
    Card(
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(vertical = vFactor(12), horizontal = hFactor(8)),
            verticalAlignment = Alignment.Top
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_email),
                contentDescription = null,
                modifier = Modifier
                    .width(36.dp)
                    .height(36.dp)
                    .background(color = Color(0x0DB58353), shape = RoundedCornerShape(size = 20.dp))

                    .padding(start = 5.dp, top = 8.dp, end = 5.dp, bottom = 8.dp)
            )
            Icon(
                imageVector = item.icon,
                contentDescription = item.title,
                tint = SecondaryColor, // Accent color (light brown)
                modifier = Modifier
                    .size(36.dp)
                    .padding( 8.dp)
            )
            Column(
                modifier = Modifier.weight(1f)
            ) {
                CustomMontserratText(
                    text = item.title,

                    fontSize = 14.sp,
                    fontWeight = FontWeight(500),
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(vFactor(12)))
                CustomMontserratText(
                    text = item.description,
                    fontSize = 13.sp,
                    color = AppTextGray
                )
                Spacer(modifier = Modifier.height(vFactor(12)))
                CustomMontserratText(
                    text = item.time,
                    fontSize = 12.sp,
                    color = AppTextGray
                )

        }}
    }
}

