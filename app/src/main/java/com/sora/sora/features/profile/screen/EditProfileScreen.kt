package com.sora.sora.features.profile.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sora.sora.R
import com.sora.sora.core.customButtons.CustomButton
import com.sora.sora.features.profile.widgets.DeleteAccountBottomSheet
import com.sora.sora.ui.components.AppTextField2
import com.sora.sora.ui.components.AppTextField3
import com.sora.sora.ui.theme.PrimaryColor
import com.sora.sora.ui.theme.PrimaryColorFaded

@Composable
fun EditProfileScreen() {
//    // States
//    var name by remember { mutableStateOf("Leslie Alexander") }
//    var email by remember { mutableStateOf("lesliealexandar@mail.com") }
//    var phone by remember { mutableStateOf("(308) 555-0121") }
    // States
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    val countryCodes = listOf("+49", "+1", "+44")
    var selectedCountryCode by remember { mutableStateOf(countryCodes[0]) }
    var expandedCountryCode by remember { mutableStateOf(false) }

    // Scroll state
    val scrollState = rememberScrollState()
    val showBottomSheet = remember { mutableStateOf(false) }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .verticalScroll(scrollState) // Allow the content to scroll
    ) {
        // Content
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 45.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Back button + Title Row
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(
                    onClick = { /* Handle back click */ },
                    modifier = Modifier
                        .size(55.dp)
                        .clip(CircleShape)
                        .background(PrimaryColorFaded)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }
                Text(
                    text = "Edit Profile",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 40.dp)
                )
            }

            // Profile Image + Camera Icon
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_temp_profile_pic), // Replace with the actual image resource
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .border(2.dp, PrimaryColor, CircleShape)
                )
                Box(
                    modifier = Modifier
                        .size(35.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.White, CircleShape)
                        .background(PrimaryColor)
                        .align(Alignment.BottomEnd),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_camera), // Replace with the actual camera icon
                        contentDescription = "Camera",
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            // Name field
            AppTextField2(
                value = name,
                onValueChange = { name = it },
                placeholder = "Leslie Alexander",
                keyboardType = KeyboardType.Text,
                modifier = Modifier.fillMaxWidth(),
                prefixIcon = painterResource(id = R.drawable.ic_profile)
            )

            // Email field
            AppTextField2(
                value = email,
                onValueChange = { email = it },
                placeholder = "you@mail.com",
                keyboardType = KeyboardType.Email,
                modifier = Modifier.fillMaxWidth(),
                prefixIcon = painterResource(id = R.drawable.ic_mail)
            )

            // Country Code and Phone field using AppTextField3
            AppTextField3(
                value = phone,
                onValueChange = { phone = it },
                placeholder = "(308) 555-0121",
                keyboardType = KeyboardType.Phone,
                modifier = Modifier.fillMaxWidth(),
                prefix = {
                    Box {
                        Row(
                            modifier = Modifier
                                .clickable { expandedCountryCode = true }
                                .padding(horizontal = 8.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter =  painterResource(id = R.drawable.ic_kuwait_flag),
                                contentDescription = null,
                                modifier = Modifier.size(32.dp),
                                tint = Color.Unspecified // Ensure the icon is not tinted gray
                            )
                            Icon(
                                imageVector = Icons.Filled.ArrowDropDown,
                                contentDescription = "Select Country Code"
                            )
                            Box(
                                modifier = Modifier
                                    .width(1.dp)
                                    .fillMaxHeight()
                                    .background(Color.LightGray)
                            ){}
                        }

                    }
                }
            )


            Spacer(modifier = Modifier.height(32.dp))

            CustomButton(
                label = "Update", // The text that will be displayed on the button
                onClick = { /* Handle button click */ }, // Action on button click
            )

//            Spacer(modifier = Modifier.height(16.dp))

            CustomButton(
                label = "Delete Account?", // The text that will be displayed on the button
                onClick = {
                    showBottomSheet.value = true
                }, // Action on button click
                secondaryButton = true,
            )

        }
    }
    if (showBottomSheet.value) {
        DeleteAccountBottomSheet(
            orderId = "orderId",
            onDismiss = { showBottomSheet.value = false },
            onCancelOrder = { orderId ->
                // Handle order cancellation logic here
                showBottomSheet.value = false // Close bottom sheet after canceling
            }
        )
    }
}
