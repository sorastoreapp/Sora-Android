package com.sora.sora.features.favourites.widgets

//
//  FavoriteEmptyStateView.kt
//  SoraStore
//
//  Created by Developer Bazaar Technologies on 02/07/25.
//


import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sora.sora.R
import com.sora.sora.core.customText.CustomMontserratText
import com.sora.sora.ui.theme.AppTextGray
import com.sora.sora.ui.theme.PrimaryColor
import com.sora.sora.ui.theme.ProductCardColor


@Composable
fun FavoriteEmptyStateView() {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    // Animation states
    var isStarted by remember { mutableStateOf(false) }

    val scaleAnim = rememberInfiniteTransition()
    val scale by scaleAnim.animateFloat(
        initialValue = 1.0f,
        targetValue = 1.15f,
        animationSpec = infiniteRepeatable(
            animation = tween(900, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val opacity by scaleAnim.animateFloat(
        initialValue = 1.0f,
        targetValue = 0.85f,
        animationSpec = infiniteRepeatable(
            animation = tween(900, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val rotation by scaleAnim.animateFloat(
        initialValue = -3f,
        targetValue = 3f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {


        Spacer(modifier = Modifier.height(90.dp))


        Box(
            modifier = Modifier
                .size(screenWidth * 0.27f)
                .shadow(elevation = 8.dp, shape = CircleShape, clip = false)
                .background(color = ProductCardColor, shape = CircleShape),

            contentAlignment = Alignment.Center
        ) {
            androidx.compose.material.Icon(
                painter = painterResource(id = R.drawable.ic_unselected_favorite),
                tint = PrimaryColor,
                contentDescription = "location",
                modifier = Modifier
                        .size(screenWidth * 0.094f)
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        alpha = opacity
                        rotationZ = rotation
                    },
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
