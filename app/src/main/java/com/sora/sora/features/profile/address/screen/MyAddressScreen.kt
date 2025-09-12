@file:OptIn(ExperimentalMaterialApi::class)

package com.sora.sora.features.profile.address.screen

import android.util.Log
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material3.*
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sora.sora.R
import com.sora.sora.core.customButtons.CustomButton
import com.sora.sora.core.customText.CustomMontserratText
import com.sora.sora.core.hFactor
import com.sora.sora.core.navigations.Dest
import com.sora.sora.core.navigations.NavigationManager.navController
import com.sora.sora.core.navigations.toRoute
import com.sora.sora.core.vFactor
import com.sora.sora.features.profile.address.controller.AddressController
import com.sora.sora.ui.theme.AppTextGray
import com.sora.sora.ui.theme.LightBrown
import com.sora.sora.ui.theme.PrimaryColor
import com.sora.sora.ui.theme.SecondaryColor100
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// Data class for Address
data class Address(
    val name: String,
    val address: String,
    val phone: String
)

// Main screen for displaying My Addresses

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAddressesScreen() {
    val controller = remember { AddressController() }
    val scope = rememberCoroutineScope()

    val isAddressEmpty by controller.isEmptyStateTrue.collectAsState()
    val isRefreshing by controller.isRefreshing.collectAsState()
    val addresses = listOf(
        Address(name = "Nasser AL-Quraini", address = "House 12, Street 4, Block 5 Jabriya, Hawally  Kuwait ", phone = "+965 9876 5432"),
        Address(name = "Michael Thompson", address = "4321 Ocean Drive, Floor 3, Hawally, Kuwait, 15002", phone = "+965 9765 4321"),
        Address(name = "Sophia Martinez", address = "7890 Pearl Tower, Apartment 21A, Fahaheel, Kuwait, 30010", phone = "+965 9543 6789"),
        Address(name = "Sophia Martinez", address = "7890 Pearl Tower, Apartment 21A, Fahaheel, Kuwait, 30010", phone = "+965 9543 6789"),
        Address(name = "Sophia Martinez", address = "7890 Pearl Tower, Apartment 21A, Fahaheel, Kuwait, 30010", phone = "+965 9543 6789"),
        Address(name = "Sophia Martinez", address = "7890 Pearl Tower, Apartment 21A, Fahaheel, Kuwait, 30010", phone = "+965 9543 6789")
    )
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            scope.launch {
                controller.refreshAddress()
            }
        }
    )

    Scaffold(
        backgroundColor = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .pullRefresh(pullRefreshState),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(start = 16.dp, end = 16.dp),
                title = {
                    CustomMontserratText(
                        text = "My Addresses",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    Row(
                        modifier = Modifier
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onPress = { offset ->
                                        navController?.popBackStack()
                                    }
                                )
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(45.dp)
                                .clip(CircleShape)
                                .background(SecondaryColor100)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = PrimaryColor,
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .size(24.dp)
                            )
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(paddingValues), // This ensures padding is applied properly
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = hFactor(16))
                    .verticalScroll(rememberScrollState()) // Make the entire content scrollable
            ) {
                if (isAddressEmpty) {
                    // Show address list
                    Spacer(modifier = Modifier.height(vFactor(10)))
                    addresses.forEach { address ->
                        AddressCard(address = address)
                        Spacer(modifier = Modifier.height(vFactor(12)))
                    }
                } else {
                    // Show empty state
                    AnimatedIconWithText(modifier = Modifier.fillMaxWidth())
                }

                Spacer(modifier = Modifier.height(vFactor(35))) // Pushes the "Add new address" button to the bottom

                // "Add new address" button
                CustomButton(
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = hFactor(12)),

                    icon = R.drawable.ic_add_brown,
                    label = "Add new address",
                    onClick = {
                        navController.navigate(Dest.AddNewAddressScreen::class.toRoute())
                    },
                    containerColor = LightBrown,
                    textColor = PrimaryColor
                )
                Spacer(modifier = Modifier.height(vFactor(35)))
            }

            // Pull-to-refresh indicator
            PullRefreshIndicator(
                refreshing = isRefreshing,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter),
                contentColor = PrimaryColor
            )
        }
    }
}


// Composable to display each Address Card
@Composable
fun AddressCard(address: Address) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    Card(
        modifier = Modifier
            .fillMaxWidth(),

        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = hFactor(16), vertical = vFactor(6))
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Spacer(modifier = Modifier.width(2.dp)) // Push icons to the right
                CustomMontserratText(
                    text = address.name,
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.weight(1f)) // Push icons to the right

                // Edit Icon (Green)
                IconButton(
                    onClick = { Log.d("ProfileScreen", "Edit profile clicked") }
                ) {
                    Box(
                        modifier = Modifier
                            .size(35.dp) // Set the size of the icon container
                            .background(
                                Color.LightGray.copy(alpha = 0.2f),
                                shape = CircleShape
                            ) // Set the filled primary color and circular shape
                            .padding(8.dp) // Padding inside the circle to ensure the icon has space around it
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onTap = {
                                        navController.navigate(Dest.AddNewAddressScreen::class.toRoute())

                                    })
                            }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_edit_icon),
                            contentDescription = "Edit Profile",
                            modifier = Modifier
                                .fillMaxSize() // Make the image fill the size of the container
                        )
                    }
                }

            }
            Spacer(modifier = Modifier.height(vFactor(5)))


            // Address Details with Location Icon
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = screenWidth - 200.dp),

                horizontalArrangement = Arrangement.Start // Align all children to the start

            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_map_pin),
                    contentDescription = "Location Icon",
                    modifier = Modifier.size(18.dp),
                    tint = PrimaryColor // Set the tint color to PrimaryColor
                )
                Spacer(modifier = Modifier.width(8.dp))
                CustomMontserratText(
                    text = address.address,
                    fontSize = 16.sp,
                    color = AppTextGray,
                    fontWeight = FontWeight(400),
                    maxLines = 3,
//                    fontStyle = FontStyle.Italic,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.height(vFactor(20)))

            // Phone Number with Phone Icon and Edit/Delete Icons
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_call_outline),
                    contentDescription = "Phone Icon",
                    modifier = Modifier.size(18.dp),
                    tint = PrimaryColor // Set the tint color to PrimaryColor
                )
                Spacer(modifier = Modifier.width(8.dp))
                CustomMontserratText(
                    text = address.phone,
                    fontSize = 16.sp,
                    color = AppTextGray,
                    fontWeight = FontWeight(400),

//
                )
            }
            Spacer(modifier = Modifier.height(vFactor(10)))
        }
    }
}
@Composable
fun AnimatedIconWithText(
    modifier: Modifier = Modifier,
    width: Dp = 400.dp // Puedes ajustar esto según la pantalla
) {
    val wPx = with(LocalDensity.current) { width.toPx() }

    // Estados para las animaciones
    var showIcon by remember  { mutableStateOf(false) }
    var showText by remember { mutableStateOf(false) }
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp


    val scaleEffect by rememberInfiniteTransition(label = "scale").animateFloat(
        initialValue = 1f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(900, easing = EaseInOut),
            repeatMode = RepeatMode.Reverse
        ), label = "scale"
    )

    val rotationAngle by rememberInfiniteTransition(label = "rotation").animateFloat(
        initialValue = 0f,
        targetValue = 15f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = EaseInOut),
            repeatMode = RepeatMode.Reverse
        ), label = "rotation"
    )

    val offsetY by rememberInfiniteTransition(label = "offsetY").animateFloat(
        initialValue = 0f,
        targetValue = -15f,
        animationSpec = infiniteRepeatable(
            animation = tween(900, easing = EaseInOut),
            repeatMode = RepeatMode.Reverse
        ), label = "offsetY"
    )

    val opacity by rememberInfiniteTransition(label = "opacity").animateFloat(
        initialValue = 1f,
        targetValue = 0.8f,
        animationSpec = infiniteRepeatable(
            animation = tween(500, easing = EaseInOut),
            repeatMode = RepeatMode.Reverse
        ), label = "opacity"
    )

    // Animación de aparición
    val iconScale by animateFloatAsState(
        targetValue = if (showIcon) 1f else 0.5f,
        animationSpec = tween(durationMillis = 800),
        label = "iconScale"
    )

    val iconAlpha by animateFloatAsState(
        targetValue = if (showIcon) 1f else 0f,
        animationSpec = tween(durationMillis = 800),
        label = "iconAlpha"
    )

    val textAlpha by animateFloatAsState(
        targetValue = if (showText) 1f else 0f,
        animationSpec = tween(durationMillis = 800),
        label = "textAlpha"
    )

    // Lanzar las animaciones con retrasos
    LaunchedEffect(Unit) {
        showIcon = true
        delay(300)
        showText = true
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Icono animado dentro de un círculo
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(width * 0.171f)
                .graphicsLayer() {
                    scaleX = iconScale
                    scaleY = iconScale
                    alpha = iconAlpha
                }
                .background(
                    color = LightBrown,
                    shape = CircleShape
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_map_pin),
                contentDescription = null,
                modifier = Modifier
                    .size(width * 0.094f)
                    .graphicsLayer {
                        scaleX = scaleEffect
                        scaleY = scaleEffect
                        rotationZ = rotationAngle
                        translationY = offsetY
                        alpha = opacity
                    },
                contentScale = ContentScale.Fit
            )
        }

        // Textos animados
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(15.dp))
            CustomMontserratText(
                text = "You don't have any address yet",
                fontSize = (screenWidth.value * 0.043).sp,
                color = Color.Black.copy(alpha = 0.8f),
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )

            CustomMontserratText(
                text = "Your address help us deliver your order",
                fontSize = (screenWidth.value * 0.0376).sp,
                color = AppTextGray,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}
