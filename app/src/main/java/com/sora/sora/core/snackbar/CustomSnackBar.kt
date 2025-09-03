@file:Suppress("FunctionName")
package com.sora.sora.core.snackbar
import com.sora.sora.R
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.size.Dimension
import com.sora.sora.core.customText.CustomMontserratText
import com.sora.sora.ui.theme.IconBackgroundColor
import com.sora.sora.ui.theme.PrimaryColor
import com.sora.sora.ui.theme.SecondaryColor
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

enum class SnackBarType { Success, Error, Warning, Info }

@Composable
private fun SnackColor(type: SnackBarType): Color = when (type) {
    SnackBarType.Success -> PrimaryColor
    SnackBarType.Error   -> Color(0xFFD32F2F)
    SnackBarType.Warning -> Color(0xFFFF8F00)
    SnackBarType.Info    -> Color(0xFF1976D2)
}

data class SnackBarData(
    val title: String,
    val message: String,
    val type: SnackBarType = SnackBarType.Success,
    val iconRes: Int
)

@Composable
fun CenterExpandSnackBar(
    visible: Boolean,
    data: SnackBarData,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,

    // Sizes
    circleDiameter: Dp = 70.dp,          // container height when circular (also pill height)
    imageSize: Dp = 45.dp,               // fixed logo size (won’t change the circle size)
    targetWidthMax: Dp = 700.dp,         // max pill width
    minWidth: Dp = 65.dp,               // minimum width for the circle
    pillCornerPercent: Int = 50,         // pill roundness when expanded

    // Spacing/Visuals
    topPadding: Dp = 60.dp,
    horizontalMargin: Dp = 0.dp,
    containerShadowElevation: Dp = 12.dp,
    showImageBorder: Boolean = true,
    imageBorderWidth: Dp = 0.dp,
    imageBorderColor: Color = Color.White.copy(alpha = 0.6f),

    // Timing
    expandDurationMillis: Int = 1_000,   // 1s expand
    contentVisibleMillis: Int = 5_000,   // 4s hold
    shrinkDurationMillis: Int = 800,     // 0.8s shrink
    postShrinkHoldMillis: Int = 500,     // wait before disappear
    fadeOutMillis: Int = 500,            // fade-out duration

    // Logic
    textVisibilityDelayMillis: Int = 4000 // time after which the text disappears
) {
    val cfg = LocalConfiguration.current
    val screenW = cfg.screenWidthDp.dp
    val finalWidth = minOf(screenW - horizontalMargin * 2, targetWidthMax)

    // 0f => circle, 1f => fully-formed pill
    val progress = remember { Animatable(0f) }

    // Local visibility to drive fade-out at the end
    var localVisible by remember(visible) { mutableStateOf(visible) }

    // Track the text visibility with delay
    var textVisible by remember { mutableStateOf(false) }

    // Timeline: circle -> expand -> hold -> shrink -> fade-out -> onDismiss
    LaunchedEffect(visible) {
        if (visible) {
            localVisible = true
            progress.snapTo(0f)

            // Expand to pill
            progress.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = expandDurationMillis, easing = FastOutSlowInEasing)
            )

            // Hold content visible
            delay(contentVisibleMillis.toLong())

            // Show the text after the expansion phase
            textVisible = true

            // Delay before hiding the text
            delay(textVisibilityDelayMillis.toLong()) // Wait for 4000ms
            textVisible = false

            // Shrink back to circle
            progress.animateTo(
                targetValue = 0f,
                animationSpec = tween(durationMillis = shrinkDurationMillis, easing = FastOutSlowInEasing)
            )

            // Briefly stay circular, then fade out and notify parent
            delay(postShrinkHoldMillis.toLong())
            localVisible = false
            delay(fadeOutMillis.toLong())
            onDismiss()
        } else {
            localVisible = false
            progress.snapTo(0f)
        }
    }

    // Width interpolation: ensuring it never goes below minWidth, and grows up to targetWidthMax
    val widthPx = with(androidx.compose.ui.platform.LocalDensity.current) {
        val start = maxOf(minWidth.toPx(), circleDiameter.toPx())  // Minimum width logic
        val end = finalWidth.toPx()
        (start + (end - start) * progress.value).roundToInt()
    }
    val width = with(androidx.compose.ui.platform.LocalDensity.current) { widthPx.toDp() }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalMargin),
        contentAlignment = Alignment.TopCenter
    ) {
        AnimatedVisibility(
            visible = localVisible,
            enter = fadeIn(),
            exit  = fadeOut(tween(fadeOutMillis)),
            modifier = Modifier.padding(top = topPadding)
        ) {
            Surface(
                color = SnackColor(data.type),
                shape = if (progress.value < 0.001f) CircleShape else RoundedCornerShape(percent = pillCornerPercent),
                shadowElevation = containerShadowElevation,
                modifier = Modifier
                    .width(width)
                    .height(circleDiameter)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Fixed circular slot for the logo: the image never changes the box size
                    Box(
                        modifier = Modifier
                            .size(imageSize + 4.dp) // fixed logo size
                            .clip(CircleShape)
                            .background(IconBackgroundColor),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = data.iconRes),
                            contentDescription = null,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .size(imageSize)           // fixed logo size
                                .clip(CircleShape)
                                .background(IconBackgroundColor)
                                .then(
                                    if (showImageBorder)
                                        Modifier.border(imageBorderWidth, imageBorderColor, CircleShape)
                                    else Modifier
                                )
                        )
                    }

                    Spacer(Modifier.width(8.dp))

                    // Control text visibility based on progress
                    AnimatedVisibility(visible != textVisible) {
                        Column(Modifier.weight(1f)) {
                            if (data.title.isNotBlank()) {
                                CustomMontserratText(
                                    text = data.title,
                                    color = Color.White,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            if (data.message.isNotBlank()) {
                                CustomMontserratText(
                                    text = data.message,
                                    color = Color.White,
                                    fontSize = 14.sp,
                                )
                            }
                        }
                    }

                    Spacer(Modifier.weight(1f))

                    // Fixed position of the dismiss icon
                    AnimatedVisibility(visible != textVisible) {
                        IconButton(
                            onClick = {
                                localVisible = false
                                onDismiss()
                            },
                            modifier = Modifier.size(imageSize).clip(CircleShape).background(IconBackgroundColor).padding(4.dp),
                        ) {
                            Icon(
                                Icons.Default.Close,
                                contentDescription = "Dismiss",
                                tint = Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}




//@Composable
//fun CenterExpandSnackBar(
//    visible: Boolean,
//    data: SnackBarData,
//    onDismiss: () -> Unit,
//    modifier: Modifier = Modifier,
//
//    // Sizes
//    circleDiameter: Dp = 70.dp,          // container height when circular (also pill height)
//    imageSize: Dp = 45.dp,               // fixed logo size (won’t change the circle size)
//    targetWidthMax: Dp = 700.dp,         // max pill width
//    minWidth: Dp = 65.dp,               // minimum width for the circle
//    pillCornerPercent: Int = 50,         // pill roundness when expanded
//
//    // Spacing/Visuals
//    topPadding: Dp = 60.dp,
//    horizontalMargin: Dp = 0.dp,
//    containerShadowElevation: Dp = 12.dp,
//    showImageBorder: Boolean = true,
//    imageBorderWidth: Dp = 0.dp,
//    imageBorderColor: Color = Color.White.copy(alpha = 0.6f),
//
//    // Timing
//    expandDurationMillis: Int = 1_000,   // 1s expand
//    contentVisibleMillis: Int = 4_000,   // 4s hold
//    shrinkDurationMillis: Int = 800,     // 0.8s shrink
//    postShrinkHoldMillis: Int = 500,     // wait before disappear
//    fadeOutMillis: Int = 500,            // fade-out duration
//
//    // Logic
//    textRevealThreshold: Float = 0.35f   // when to show text while expanding (0..1)
//) {
//    val cfg = LocalConfiguration.current
//    val screenW = cfg.screenWidthDp.dp
//    val finalWidth = minOf(screenW - horizontalMargin * 2, targetWidthMax)
//
//    // 0f => circle, 1f => fully-formed pill
//    val progress = remember { Animatable(0f) }
//    val textVisible by remember { derivedStateOf { progress.value > textRevealThreshold } }
//
//    // Local visibility to drive fade-out at the end
//    var localVisible by remember(visible) { mutableStateOf(visible) }
//
//    // Timeline: circle -> expand -> hold -> shrink -> fade-out -> onDismiss
//    LaunchedEffect(visible) {
//        if (visible) {
//            localVisible = true
//            progress.snapTo(0f)
//
//            // Expand to pill
//            progress.animateTo(
//                targetValue = 1f,
//                animationSpec = tween(durationMillis = expandDurationMillis, easing = FastOutSlowInEasing)
//            )
//
//            // Hold content visible
//            delay(contentVisibleMillis.toLong())
//
//            // Shrink back to circle
//            progress.animateTo(
//                targetValue = 0f,
//                animationSpec = tween(durationMillis = shrinkDurationMillis, easing = FastOutSlowInEasing)
//            )
//
//            // Briefly stay circular, then fade out and notify parent
//            delay(postShrinkHoldMillis.toLong())
//            localVisible = false
//            delay(fadeOutMillis.toLong())
//            onDismiss()
//        } else {
//            localVisible = false
//            progress.snapTo(0f)
//        }
//    }
//
//    // Width interpolation: ensuring it never goes below minWidth, and grows up to targetWidthMax
//    val widthPx = with(androidx.compose.ui.platform.LocalDensity.current) {
//        val start = maxOf(minWidth.toPx(), circleDiameter.toPx())  // Minimum width logic
//        val end = finalWidth.toPx()
//        (start + (end - start) * progress.value).roundToInt()
//    }
//    val width = with(androidx.compose.ui.platform.LocalDensity.current) { widthPx.toDp() }
//
//    Box(
//        modifier = modifier
//            .fillMaxWidth()
//            .padding(horizontal = horizontalMargin),
//        contentAlignment = Alignment.TopCenter
//    ) {
//        AnimatedVisibility(
//            visible = localVisible,
//            enter = fadeIn(),
//            exit  = fadeOut(tween(fadeOutMillis)),
//            modifier = Modifier.padding(top = topPadding)
//        ) {
//            // Ensure shape changes based on progress
//            Surface(
//                color = SnackColor(data.type),
//                shape = if (progress.value < 0.001f) CircleShape else RoundedCornerShape(percent = pillCornerPercent),
//                shadowElevation = containerShadowElevation,
//                modifier = Modifier
//                    .width(width)
//                    .height(circleDiameter)
//
//            ) {
//                Row(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(horizontal = 10.dp),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                                        Box(
//                        modifier = Modifier
//                            .size(imageSize+4.dp) // fixed logo size
//                            .clip(CircleShape)
//                            .background(IconBackgroundColor)
//                        ,
//                                // reserved circle slot
//                        contentAlignment = Alignment.Center
//                    ) {
//                        Box(
//                            modifier = Modifier.padding(4.dp).align(Alignment.Center)
//                        ) {
//                            Image(
//                                painter = painterResource(id = data.iconRes),
//                                contentDescription = null,
//                                contentScale = ContentScale.Fit,
//                                modifier = Modifier
//                                    .size(imageSize)           // fixed logo size
//                                    .clip(CircleShape)
//                                    .background(IconBackgroundColor)
//                                    .then(
//                                        if (showImageBorder)
//                                            Modifier.border(
//                                                imageBorderWidth,
//                                                imageBorderColor,
//                                                CircleShape
//                                            )
//                                        else Modifier
//                                    )
//                            )
//                        }
//                    }
//
//                    Spacer(Modifier.width(8.dp))
//
//                    AnimatedVisibility(visible = textVisible) {
//                        Column(Modifier.weight(1f).width(screenW*0.6f)) {
//                            if (data.title.isNotBlank()) {
//                                CustomMontserratText(
//                                    text = data.title,
//                                    color = Color.White,
//                                    fontSize = 14.sp,
//                                    fontWeight = FontWeight.Bold
//                                )
//                            }
//                            if (data.message.isNotBlank()) {
//                                CustomMontserratText(
//                                    text = data.message,
//                                    color = Color.White,
//                                    maxLines = 2,
//                                    fontSize = 14.sp,
//                                )
//                            }
//                        }
//                    }
//
//                    Spacer(Modifier.weight(1f))
//
//                    AnimatedVisibility(visible = textVisible) {
//                        IconButton(
//                            onClick = {
//                                localVisible = false
//                                onDismiss()
//                            },
//                            modifier = Modifier.size(imageSize).clip(CircleShape).background(IconBackgroundColor).padding(4.dp),
//                        ) {
//                            Icon(
//                                Icons.Default.Close,
//                                contentDescription = "Dismiss",
//                                tint = Color.Black
//                            )
//                        }
//                    }
//                }
//            }
//        }
//    }
//}


@Composable
fun AnimatedTopSnackbarDemo() {
    var show by remember { mutableStateOf(true) } // trigger however you like

    CenterExpandSnackBar(
        visible = show,
        data = SnackBarData(
            title = "Success!",
            message = "Your account has been created.",
            type = SnackBarType.Success,
            iconRes = R.drawable.ic_sora_logo
        ),
//        dismissIconRes = Icons.Default.Close,
        onDismiss = { show = false }
    )
}