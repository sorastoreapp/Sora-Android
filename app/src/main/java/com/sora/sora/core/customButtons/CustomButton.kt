package com.sora.sora.core.customButtons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sora.sora.core.customText.CustomMontserratText
import com.sora.sora.core.navigations.Dest
import com.sora.sora.core.navigations.toRoute
import com.sora.sora.features.dashboard.Product
import com.sora.sora.ui.theme.PrimaryColor
import com.sora.sora.ui.theme.PrimaryColorFaded
import com.sora.sora.core.navigations.NavigationManager
import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.unit.dp
import com.sora.sora.core.AppTexts
import kotlinx.coroutines.delay

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sora.sora.R
import com.sora.sora.core.customText.CustomMontserratText
import com.sora.sora.core.widgets.AnimatedAddToCart
import com.sora.sora.ui.theme.PrimaryColor
import com.sora.sora.ui.theme.TextFieldColor3
import kotlinx.coroutines.delay

//@Composable
//fun CustomButton(
//    label: String, // The text that will be displayed on the button
//    onClick: () -> Unit, // Action on button click
//    required: Boolean = false, // Optional or required button state
//    modifier: Modifier = Modifier, // Optional modifier to customize the button's appearance
//    containerColor: Color = PrimaryColor, // Default button color
//    textColor: Color = Color.White, // Text color (default: White)
//    secondaryButton : Boolean = false,
//) {
//    Button(
//        onClick = onClick,
//        modifier = modifier
//            .fillMaxWidth()
//            .height(65.dp),
//        colors = if(secondaryButton) ButtonDefaults.buttonColors(
////            containerColor = Color(0xFFECD6B0)
//            containerColor = TextFieldColor3
//        )
//
//        else  ButtonDefaults.buttonColors(containerColor = containerColor),
//        shape = MaterialTheme.shapes.medium
//    ) {
//        // Button Text
//        Text(
//            text = if (required) label else label, // Append '*' for required fields
//            color = if (secondaryButton) Color(0xFFDB5A5A) else textColor,
//            fontSize = 16.sp,
//            fontWeight = FontWeight.Bold
//        )
//    }
//}
@Composable
fun CustomButton(
    label: String, // The text that will be displayed on the button
    onClick: () -> Unit, // Action on button click
    required: Boolean = false, // Optional or required button state
    modifier: Modifier = Modifier, // Optional modifier to customize the button's appearance
    containerColor: Color = PrimaryColor, // Default button color
    textColor: Color = Color.White, // Text color (default: White)

    secondaryButton: Boolean = false, // Whether this is a secondary button
    icon: Int? = null // Optional icon resource (null if no icon)

) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(65.dp),
        colors = if (secondaryButton) ButtonDefaults.buttonColors(
            containerColor = TextFieldColor3
        ) else ButtonDefaults.buttonColors(containerColor = containerColor),
        shape = RoundedCornerShape(20.dp)
    ) {
        // Row to hold icon and text (if an icon is provided)
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            // If icon is provided, add it
            if (icon != null) {
                Image(
                    painter = painterResource(id = icon),

                    contentDescription = "",
                    modifier = Modifier.size(20.dp)


                )
                Spacer(modifier = Modifier.width(10.dp)) // Spacer between icon and text
            }

            // Button Text
            Text(
                text = if (required) "$label *" else label, // Append '*' for required fields
                color = if (secondaryButton) Color(0xFFDB5A5A) else textColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}




@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    backgroundColor: Color = PrimaryColor, // Default value for the background color
    textColor: Color = Color.White, // Default value for the text color
    height: Int = 70, // Default height
    modifier: Modifier = Modifier, // Default empty modifier
    borderColor: Color? = null, // Optional border color
//    shape: androidx.compose.foundation.shape.CornerBasedShape = MaterialTheme.shapes.large // Default shape
    shape: RoundedCornerShape = RoundedCornerShape(20.dp),
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(height.dp)
            .then(if (borderColor != null) Modifier.border(2.dp, borderColor, shape) else Modifier), // Apply border if given
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        shape = shape
    ) {
        CustomMontserratText(
            text = text, color = textColor, fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}


@Composable
fun CountButton(
    modifier: Modifier = Modifier, // Default empty modifier
//    shape: androidx.compose.foundation.shape.CornerBasedShape = MaterialTheme.shapes.large // Default shape
    shape: RoundedCornerShape = RoundedCornerShape(20.dp),
) {

    var qty by remember { mutableIntStateOf(0) }
    var onFavorite = { /* … */ }
    var onShare = { /* … */ }
    var onAdd = { qty++ }
    var onRemove = { if (qty > 0) qty-- }
    var quantity = qty
    var color: Color = PrimaryColor
    var maxQty: Int = 5
    var minQty: Int = 0
    var height: Int = 57 // Default height

    var expanded by remember { mutableStateOf(false) }
    var pressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(if (pressed) 0.95f else 1f, label = "press-scale")

    // Collapse when qty hits min
    LaunchedEffect(quantity) {
        if (quantity <= minQty) expanded = false
    }

    Button(
        onClick = {
            expanded = true
        },
        modifier = modifier
            .fillMaxWidth()
            .height(height.dp)
            .border(2.dp, PrimaryColor, shape), // Apply border if given
        colors = ButtonDefaults.buttonColors(containerColor = if(!expanded) Color.White else PrimaryColor),
        shape = shape
    ) {
        // *** Animated [- qty +] control ***
        // *** Animated [- qty +] control ***
        Box(modifier = modifier.graphicsLayer { scaleX = scale; scaleY = scale }) {

            // EXPANDED [- qty +]
            androidx.compose.animation.AnimatedVisibility(
                visible = expanded, // <- only when expanded
                enter = expandHorizontally(
                    animationSpec = spring(stiffness = Spring.StiffnessMediumLow, dampingRatio = 0.85f)
                ) + fadeIn(),
                exit = shrinkHorizontally(
                    animationSpec = spring(stiffness = Spring.StiffnessMediumLow, dampingRatio = 0.85f)
                ) + fadeOut()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier
                        .height(36.dp)
                        .background(color, RoundedCornerShape(10.dp))
                        .padding(horizontal = 12.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_remove),
                        contentDescription = "Decrease",
                        tint = Color.White,
                        modifier = Modifier
                            .size(25.dp)
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onPress = { /* No animation on press */ },
                                    onTap = {
                                        if (quantity > minQty) {
                                            pressed = true
                                            onRemove()
                                            pressed = false
                                        }
                                    }
                                )
                            }
                    )

                    Text(
                        text = quantity.toString(),
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )

                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = "Increase",
                        tint = Color.White,
                        modifier = Modifier
                            .size(25.dp)
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onPress = { /* No animation on press */ },
                                    onTap = {
                                        if (quantity < maxQty) {
                                            if (!expanded) expanded = true
                                            pressed = true
                                            onAdd()
                                            pressed = false
                                        }
                                    }
                                )
                            }
                    )
                }
            }

            // COLLAPSED (quantity pill) when qty > 0
            androidx.compose.animation.AnimatedVisibility(
                visible = !expanded && quantity > minQty,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Box(
                    modifier = Modifier
                        .height(36.dp)
                        .background(color, RoundedCornerShape(10.dp))
                        .padding(horizontal = 16.dp)
                        .clickable { expanded = true }, // tap to reopen controls
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = quantity.toString(),
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            // COLLAPSED (+) when qty == 0
            androidx.compose.animation.AnimatedVisibility(
                visible = !expanded && quantity == minQty,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Box(
                    modifier = Modifier
                        .size( if(expanded)36.dp else   100.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .clickable {
                            if (quantity < maxQty) {
                                expanded = true
                                onAdd()
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    CustomMontserratText(
                        text = "Add To Cart", color = PrimaryColor, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
