// EmptyCartScreen.kt

import android.annotation.SuppressLint
import android.graphics.drawable.shapes.PathShape
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.sora.sora.R
import com.sora.sora.core.customText.CustomMontserratText
import com.sora.sora.ui.theme.AppTextGray
import com.sora.sora.ui.theme.PrimaryColor
import com.sora.sora.ui.theme.SecondaryColor
import com.sora.sora.ui.theme.SecondaryColor100
import kotlinx.coroutines.delay
import kotlin.math.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmptyCartScreen(
    onShop: () -> Unit = {}
) {
    // Entry, idle, and button animation states
    var bagPopIn by remember { mutableStateOf(false) }
    var bagIdleAnim by remember { mutableStateOf(false) }
    var titleAnim by remember { mutableStateOf(false) }
    var descAnim by remember { mutableStateOf(false) }
    var btnAnim by remember { mutableStateOf(false) }

    // Bag spring/bob states
    val bagScale by animateFloatAsState(if (bagPopIn) 1f else 0.5f, spring(stiffness = 240f, dampingRatio = 0.8f), label = "BagScale")
    val bagAlpha by animateFloatAsState(if (bagPopIn) 1f else 0f, animationSpec = tween(400), label = "BagAlpha")
    val bagY by animateDpAsState(if (bagPopIn) 0.dp else (-40).dp, spring(stiffness = 240f), label = "BagY")
    val bagAngle by animateFloatAsState(if (bagPopIn) 0f else -20f, spring(stiffness = 160f), label = "BagAngle")

    // Idle bounce/breathing/rotation
    val infinite = rememberInfiniteTransition(label = "BagIdle")
    val idleBobY by infinite.animateFloat(initialValue = -5f, targetValue = 5f, animationSpec = infiniteRepeatable(animation = tween(1400, easing = FastOutSlowInEasing), repeatMode = RepeatMode.Reverse), label = "BobY")
    val idleR3d by infinite.animateFloat(initialValue = -7f, targetValue = 10f, animationSpec = infiniteRepeatable(animation = tween(2300, easing = LinearEasing), repeatMode = RepeatMode.Reverse), label = "IdleRotY")
    val idleScale by infinite.animateFloat(0.97f, 1.04f, infiniteRepeatable(tween(1700, easing = LinearEasing), RepeatMode.Reverse), label = "IdleScale")

    // Animate button pop & shimmer
    val buttonScale by animateFloatAsState(if (btnAnim) 1f else 0.78f, animationSpec = spring(stiffness = 180f), label = "BtnScale")
    val buttonAlpha by animateFloatAsState(if (btnAnim) 1f else 0f, animationSpec = tween(380), label = "BtnAlpha")
    val shimmerHighlight = Brush.linearGradient(listOf(Color.White.copy(alpha=0.2f), Color.White.copy(alpha=0.8f), Color.White.copy(alpha=0.2f)), start = Offset.Zero, end = Offset.Infinite)

    // Animate text
    val titleAlpha by animateFloatAsState(if (titleAnim) 1f else 0f, animationSpec = tween(400, 40), label = "TitleAlpha")
    val titleY by animateDpAsState(if (titleAnim) 0.dp else 22.dp, animationSpec = spring(), label = "TitleY")
    val descAlpha by animateFloatAsState(if (descAnim) 1f else 0f, animationSpec = tween(340, 180), label = "DescAlpha")
    val descY by animateDpAsState(if (descAnim) 0.dp else 19.dp, animationSpec = spring(), label = "DescY")

    // Pop-in timings
    LaunchedEffect(true) {
        delay(150)
        bagPopIn = true           // Bag pops in
        delay(500)
        titleAnim = true          // Title appears
        delay(220)
        descAnim = true           // Description appears
        delay(180)
        btnAnim = true            // Button shows up
        delay(540)
        bagIdleAnim = true
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,

        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .systemBarsPadding()
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        Box(contentAlignment = Alignment.Center, modifier = Modifier.size(140.dp)) {
            // Animated Bouncy Circle Behind Bag
            Canvas(modifier = Modifier
                .size(118.dp)
                .graphicsLayer {
                    scaleX = bagScale
                    scaleY = bagScale
                    alpha = bagAlpha
                    translationY = bagY.toPx()
                }
            ) {
                drawCircle(color = SecondaryColor100)
            }
            // Animated Bag Icon
            Icon(
                painter = painterResource(id = R.drawable.ic_cart_outlined), // replace with your bag icon resource
                tint = SecondaryColor,
                contentDescription = "Cart",
                modifier = Modifier
                    .size(80.dp)
                    .graphicsLayer {
                        scaleX = bagScale * idleScale
                        scaleY = bagScale * idleScale
                        alpha = bagAlpha
                        translationY = bagY.toPx() + if (bagIdleAnim) idleBobY else 0f
                        rotationZ = bagAngle
                        rotationY = if (bagIdleAnim) idleR3d else 0f
                    }
            )
            // Falling items with different shapes
            FallingItem(

                color = Color.Green,
                size = 16.dp,
                shape = CircleShape,       // Circle shape
                startOffset = Offset(-70f, -50f),
                endOffset = Offset(-70f, 90f),
                duration = 680,
                delay = 450
            )

            FallingItem(
                color = Color.Blue,
                size = 16.dp,
                shape = null,    // Rectangle shape
                startOffset = Offset(28f, -25f),
                endOffset = Offset(28f, 86f),
                duration = 590,
                delay = 620
            )
            FallingItem(
                color = Color.Red,
                size = 16.dp,
                shape = RectangleShape,        // Triangle shape (custom)
                startOffset = Offset(120f, 10f),
                endOffset = Offset(90f, 150f),
                duration = 760,
                delay = 840
            )

        }
        // Title
        CustomMontserratText(
            text = "Your Cart is Empty!",
            color = Color.Black,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .alpha(titleAlpha)
                .offset(y = titleY)
                .padding(vertical = 2.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        // Description
        CustomMontserratText(
            text = "Looks like you haven't added anything to your cart yet. Start exploring our amazing products!",
            color = AppTextGray,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .alpha(descAlpha)
                .offset(y = descY)
                .padding(horizontal = 60.dp)
                .padding(top = 2.dp)
        )
        // Button (scaling/shimmer/press effect)
        Spacer(modifier = Modifier.height(19.dp))
        AnimatedBounceButton(
            modifier = Modifier
                .alpha(buttonAlpha)
                .scale(buttonScale),
            text = "Start Shopping",
            onClick = onShop,

            shimmer = true // turn shimmer true if you want a shimmer effect
        )
        Spacer(modifier = Modifier.weight(2f))
    }

}




@Composable
fun FallingItem(
    color: Color,
    size: Dp,
    shape: Shape? ,       // New shape parameter
    startOffset: Offset,
    endOffset: Offset,
    duration: Int,
    delay: Int
) {
    var trigger by remember { mutableStateOf(false) }
    val density = LocalDensity.current
    val offsetAnim by animateOffsetAsState(
        targetValue = if (trigger) endOffset else startOffset,
        animationSpec = tween(durationMillis = duration, delayMillis = delay, easing = FastOutSlowInEasing),
        label = "falling-offset"
    )
    val alphaAnim by animateFloatAsState(if (trigger) 0f else 1f, animationSpec = tween(durationMillis = duration, delayMillis = delay, easing = LinearEasing), label = "falling-alpha")
    val rotAnim by animateFloatAsState(if (trigger) 360f else 0f, animationSpec = tween(durationMillis = duration, delayMillis = delay, easing = FastOutLinearInEasing), label = "falling-rot")
    LaunchedEffect(Unit) { trigger = true }

    // If icon is passed, render it. Otherwise, render shapes.
    Canvas(modifier = Modifier
        .size(size)
        .graphicsLayer {
            translationX = offsetAnim.x
            translationY = offsetAnim.y
            alpha = alphaAnim
            rotationZ = rotAnim
        }
    ) {
        when (shape) {
            CircleShape -> drawCircle(color = color)
            RectangleShape -> drawRect(color = color)
            else -> { // For Triangle, drawing as a path
                val path = Path().apply {
                    moveTo(size.toPx() / 2, 0f)
                    lineTo(0f, size.toPx())
                    lineTo(size.toPx(), size.toPx())
                    close()
                }
                drawPath(path, color)
            }
        }
    }
}


// Bounce/shimmer button (with optional shimmer effect)
@Composable
fun AnimatedBounceButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    shimmer: Boolean = false
) {
    var pressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(if (pressed) 0.95f else 1f, animationSpec = tween(120), label = "BounceScale")

    val shimmerBrush = rememberShimmerBrush(visible = shimmer)

    Button(
        onClick = onClick,
        modifier = modifier
            .then(if (shimmer) Modifier.background(shimmerBrush, CircleShape) else Modifier)
            .scale(scale),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
        contentPadding = PaddingValues(horizontal = 46.dp, vertical = 13.dp),
        elevation = ButtonDefaults.buttonElevation(7.dp, 3.dp)
    ) {
        CustomMontserratText(text, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Normal)
    }
}

// Shimmer Brush modifier (based on community recipes)
@Composable
fun rememberShimmerBrush(
    visible: Boolean = true
): Brush {
    if (!visible) return Brush.linearGradient(listOf(Color.Unspecified, Color.Unspecified))
    val transition = rememberInfiniteTransition(label = "shimmer")
    val translateAnim by transition.animateFloat(-300f, 900f, infiniteRepeatable(animation = tween(1300, easing = LinearEasing)))
    return Brush.linearGradient(
        colors = listOf(
            Color.White.copy(alpha = 0.36f),
            Color.White.copy(alpha = 0.89f),
            Color.White.copy(alpha = 0.36f)
        ),
        start = Offset(translateAnim, 0f),
        end = Offset(translateAnim + 300f, 300f)
    )
}
