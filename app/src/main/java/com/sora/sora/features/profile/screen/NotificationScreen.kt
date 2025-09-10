package com.sora.sora.features.profile.screen


import EmptyCartScreen
import android.content.Context
import android.media.AudioManager
import android.media.SoundPool
import android.util.Log
import java.io.FileDescriptor
import android.content.res.AssetFileDescriptor
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ContentScale.Companion.Fit
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sora.sora.R
import com.sora.sora.core.CustomAppBar
import com.sora.sora.core.customText.CustomMontserratText
import com.sora.sora.core.hFactor
import com.sora.sora.core.navigations.NavigationManager.navController
import com.sora.sora.core.vFactor
import com.sora.sora.features.cart_screen.controller.CartController
import com.sora.sora.features.dashboard.CartScreenMainView
import com.sora.sora.features.profile.controllers.NotificationController
import com.sora.sora.ui.theme.AppGray
import com.sora.sora.ui.theme.AppTextGray
import com.sora.sora.ui.theme.ProductCardColor
import com.sora.sora.ui.theme.SecondaryColor
import com.sora.sora.ui.theme.SecondaryColor100
import com.sora.sora.ui.theme.TextFieldBackgroundColors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
fun NotificationScreen() {
    val controller = remember { NotificationController() }
    var showBackPressed by remember { mutableStateOf(false) }
    val isNotificationEmpty by controller.isEmptyStateTrue.collectAsState()
    val isRefreshing by controller.isRefreshing.collectAsState()
    val scope = rememberCoroutineScope()

    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            scope.launch {
                controller.refreshCart()
            }
        }
    )
    Box {
         Column(
            modifier = Modifier
                .fillMaxSize()
                .pullRefresh(pullRefreshState)
        ) {
             CustomAppBar(
                 title = "Notifications",
                 isBackButton = true,
                 onBackClick = {
                     // Handle back click, navigate back or pop from the navigation stack
                     navController.popBackStack()
                 },
             )
             if (isNotificationEmpty) {
                 NotificationEmptyStateView()
             } else {


                 Column(
                     modifier = Modifier
                         .fillMaxSize()
                         .background(Color.White)
                         .padding(horizontal = hFactor(20))// Background for the entire screen
                 ) {

                     // Image Background
                     Spacer(modifier = Modifier.height(vFactor(16)))

                     // Notifications List
                     val notifications = listOf(
                         NotificationItem(
                             icon = R.drawable.ic_notification,
                             title = "Big Savings Alert!",
                             description = "Get up to 50% off on your favorite toys & kids' accessories. Hurry, limited time only",
                             time = "10 hr ago"
                         ),
                         //                NotificationItem(
                         //                    icon = R.drawable.ic_truck,
                         //                    title = "Order Delivered!",
                         //                    description = "Yay! Your order #12345 has been delivered. We hope your little one loves it! Let us know what you think!",
                         //                    time = "Yesterday"
                         //                ),
                         NotificationItem(
                             icon = R.drawable.ic_favorite_outline,
                             title = "New Arrivals Just Landed!",
                             description = "Discover the latest toys & trendy accessories for your little ones. Shop now!",
                             time = "Yesterday"
                         )
                     )

                     LazyColumn(
                         modifier = Modifier.fillMaxSize(),
                         verticalArrangement = Arrangement.spacedBy(vFactor(12))
                     ) {
                         items(notifications) { item ->
                             NotificationCard(item)
                         }
                     }
                 }
             }


        }

        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}


@Composable
fun  NotificationItemsScreen() {

}

@Composable
fun NotificationEmptyStateView() {
    // State for shake animation
    var shake by remember { mutableStateOf(false) }
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    // Start shaking when the composable is displayed
    LaunchedEffect(Unit) {
        // Start the shaking animation as soon as the screen is shown
        shake = true
    }

    // Shake animation (rotation)
    val rotation by animateFloatAsState(

        targetValue = if (shake) -15f else 15f, // Rotate back and forth
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 300 // Duration of one full shake cycle (increased for smoothness)
                0f at 0 with EaseInOut // Start at 0 degrees with smooth easing
                15f at 150 with EaseInOut // Rotate to 15 degrees at 400ms
                -15f at 300 with EaseInOut // Rotate to -15 degrees at 800ms
            },

            repeatMode = RepeatMode.Restart // Restart the animation after each cycle
        )
    )


    // Layout of the notification with shaking effect

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,

            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .systemBarsPadding()
                .padding(horizontal = 16.dp, vertical = 8.dp)


        ) {

            Box(
                modifier = Modifier

                    .width(screenWidth * 0.185f)
                    .height(screenHeight * 0.085f)
                    .shadow(elevation = 8.dp, shape = CircleShape, clip = false)
                    .background(color = ProductCardColor, shape = CircleShape),

                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_notification), // Replace with your icon
                    contentDescription = "Notification Icon",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(35.dp)
                        .rotate(rotation) // Apply rotation animation
                )
            }

            Spacer(modifier = Modifier.height(15.dp))
            CustomMontserratText(
                text = "Your Favourite is empty",
                fontSize = (screenWidth.value * 0.043).sp,
                color = Color.Black.copy(alpha = 0.8f),
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(6.dp))
            CustomMontserratText(
                text = "Start liking your favorite products",
                fontSize = (screenWidth.value * 0.0376).sp,
                color = AppTextGray,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center
            )
        }

}

@Preview(showBackground = true)
@Composable
fun PreviewNotificationEmptyStateView() {
    NotificationEmptyStateView()
}

data class NotificationItem(
    val icon: Int,
    val title: String,
    val description: String,
    val time: String
)

@Composable
fun NotificationCard(item: NotificationItem) {
    Card(
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
       // border = CardDefaults.outlinedCardBorder(false),
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Color.Gray.copy(alpha = 0.1f),
                shape = RoundedCornerShape(size = 15.dp)
            )
    ) {
        Row(
            modifier = Modifier.padding(vertical = vFactor(12), horizontal = hFactor(8)),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .width(36.dp)
                    .height(36.dp)
                    .background(
                        color = SecondaryColor100,
                        shape = RoundedCornerShape(size = 20.dp)
                    )
                    .wrapContentSize(Alignment.Center)
            ) {
                Icon(
                    painter = painterResource(id = item.icon),
                    contentDescription = null,
                    tint = SecondaryColor,
                    modifier = Modifier
                        .width(28.dp)
                        .height(28.dp)
                )
            }
            Spacer(modifier = Modifier.width(hFactor(12)))

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

