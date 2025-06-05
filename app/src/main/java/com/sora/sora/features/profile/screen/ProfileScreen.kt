package com.sora.sora.features.profile.screen

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sora.sora.R
import com.sora.sora.core.customButtons.CustomButton
import com.sora.sora.ui.theme.PrimaryColor

@Composable
fun ProfileScreen() {
    val context = LocalContext.current
    var notificationsEnabled by remember { mutableStateOf(true) }
    var selectedLanguage by remember { mutableStateOf("English") }
    var showLanguageSheet by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            // Add top padding for status bar
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        // Header Row
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Profile",
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_notification),
                contentDescription = "Notifications",
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp)
                    .size(width = 40.dp, height = 40.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Profile card
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_temp_profile_pic), // Your profile photo drawable
                contentDescription = "Profile Photo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color(0xFF8A4C3D), CircleShape)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Leslie Alexander",
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(height = 6.dp))
                Text(
                    text = "(308) 555-0121",
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }

            IconButton(
                onClick = { Log.d("ProfileScreen", "Edit profile clicked") }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_edit_bar),
                    contentDescription = "Edit Profile",
                    modifier = Modifier.size(30.dp),
                    colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(Color(0xFF8A4C3D))
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Section Title
        Text(
            text = "Account",
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            color = Color(0xFF8A4C3D)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Account items
        ProfileMenuItem(
            iconRes = R.drawable.ic_orders,
            title = "My orders",
            onClick = { Log.d("ProfileScreen", "My orders clicked") }
        )
        DottedDivider(color = Color.Gray.copy(alpha = 0.5f))

        ProfileMenuItem(
            iconRes = R.drawable.ic_address,
            title = "My Addresses",
            onClick = { Log.d("ProfileScreen", "My Addresses clicked") }
        )
        DottedDivider(color = Color.Gray.copy(alpha = 0.5f))

        ProfileMenuItem(
            iconRes = R.drawable.ic_notifications,
            title = "Notifications",
            trailingContent = {
                Switch(
                    checked = notificationsEnabled,
                    onCheckedChange = {
                        notificationsEnabled = it
                        Log.d("ProfileScreen", "Notifications toggled: $it")
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color(0xFF8A4C3D),
                        checkedTrackColor = Color(0xFFCFAE9B), // lighter brown for track when ON
                        uncheckedThumbColor = Color.White,
                        uncheckedTrackColor = Color.Gray.copy(alpha = 0.4f), // more visible track when OFF

                        disabledCheckedThumbColor = Color(0xFF8A4C3D).copy(alpha = 0.5f),
                        disabledCheckedTrackColor = Color(0xFFCFAE9B).copy(alpha = 0.3f),
                        disabledUncheckedThumbColor = Color.White.copy(alpha = 0.5f),
                        disabledUncheckedTrackColor = Color.Gray.copy(alpha = 0.2f)
                    ),
                )
            },
            onClick = { /* No action on row click, toggle controls state */ }
        )
        DottedDivider(color = Color.Gray.copy(alpha = 0.5f))

        ProfileMenuItem(
            iconRes = R.drawable.ic_language,
            title = "Languages",
            trailingText = "English",
            onClick = {
                showLanguageSheet = true
                // Language selection bottom sheet
            }
        )
        if (showLanguageSheet) {
            LanguageSelectionBottomSheet(
                onDismiss = { showLanguageSheet = false },
                onLanguageSelected = { language ->
                    selectedLanguage = language
                    showLanguageSheet = false
                })
        }
        DottedDivider(color = Color.Gray.copy(alpha = 0.5f))

        ProfileMenuItem(
            iconRes = R.drawable.ic_country,
            title = "Country",
            trailingText = "Kuwait",
            onClick = { Log.d("ProfileScreen", "Country clicked") }
        )
        DottedDivider(color = Color.Gray.copy(alpha = 0.5f))
        Spacer(modifier = Modifier.height(32.dp))

        // Others section title
        Text(
            text = "Others",
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            color = Color(0xFF8A4C3D)
        )

        Spacer(modifier = Modifier.height(16.dp))

        ProfileMenuItem(
            iconRes = R.drawable.ic_tnc,
            title = "Terms & Conditions",
            onClick = { Log.d("ProfileScreen", "Terms & Conditions clicked") }
        )
        DottedDivider(color = Color.Gray.copy(alpha = 0.5f))

        ProfileMenuItem(
            iconRes = R.drawable.ic_pp,
            title = "Privacy policies",
            onClick = { Log.d("ProfileScreen", "Privacy policies clicked") }
        )
        DottedDivider(color = Color.Gray.copy(alpha = 0.5f))

        ProfileMenuItem(
            iconRes = R.drawable.ic_faq,
            title = "FAQ’s",
            onClick = { Log.d("ProfileScreen", "FAQ clicked") }
        )
        DottedDivider(color = Color.Gray.copy(alpha = 0.5f))

        ProfileMenuItem(
            iconRes = R.drawable.ic_about_us,
            title = "About Us",
            onClick = { Log.d("ProfileScreen", "About Us clicked") }
        )

        DottedDivider(color = Color.Gray.copy(alpha = 0.5f))

        ProfileMenuItem(
            iconRes = R.drawable.ic_contact_us,
            title = "Contact Us",
            onClick = { Log.d("ProfileScreen", "Contact Us clicked") }
        )
        DottedDivider(color = Color.Gray.copy(alpha = 0.5f))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(87.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_logout),
                contentDescription = "Logout",
                modifier = Modifier.size(80.dp)
            )
        }
        Spacer( modifier = Modifier.height(70.dp))
    }

}

@Composable
fun ProfileMenuItem(
    iconRes: Int,
    title: String,
    trailingText: String? = null,
    trailingContent: (@Composable () -> Unit)? = null,
    onClick: () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clickable { onClick() }
            .padding(horizontal = 8.dp)
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = title,
            modifier = Modifier
                .size(24.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )
        if (trailingText != null) {
            Text(
                text = trailingText,
                color = Color(0xFF8A4C3D),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.width(12.dp))
        }
        if (trailingContent != null) {
            trailingContent()
        }
    }
}

@Composable
fun DottedDivider(
    color: Color,
    dashWidth: Dp = 4.dp,
    dashGap: Dp = 4.dp,
    height: Dp = 1.dp
) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
    ) {
        val dashWidthPx = dashWidth.toPx()
        val dashGapPx = dashGap.toPx()
        var startX = 0f
        val y = size.height / 2

        while (startX < size.width) {
            drawLine(
                color = color,
                start = Offset(startX, y),
                end = Offset(startX + dashWidthPx, y),
                strokeWidth = height.toPx(),
                cap = StrokeCap.Round
            )
            startX += dashWidthPx + dashGapPx
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageSelectionBottomSheet(
    onDismiss: () -> Unit,
    onLanguageSelected: (String) -> Unit
) {
    val languages = listOf("English", "العربية")
    val languageIcons = listOf(R.drawable.ic_usa_flat, R.drawable.ic_kuwait_flag)
    val selectedLanguage = remember { mutableStateOf("English") }

    ModalBottomSheet(
        onDismissRequest = onDismiss
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = "Select Language",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryColor,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = "Cross",
                    tint = Color.Gray,
                    modifier = Modifier.padding(end = 5.dp).clickable { onDismiss() }
                )
            }

            languages.forEachIndexed { index, language ->
                ProfileMenuItem(
                    iconRes = languageIcons[index],
                    title = language,
                    onClick = { Log.d("ProfileScreen", "My orders clicked")
                    },
                    trailingContent = {
                        if (selectedLanguage.value == language) Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(
                                    Color.Green.copy(alpha = 0.05f)

                                )) {
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = "Selected",
                                tint = Color(0XFF07BD74)
                            )
                        } else Box(modifier = Modifier.size(0.dp))
                    }
                )
                DottedDivider(color = Color.Gray.copy(alpha = 0.5f))
            }

            Spacer(modifier = Modifier.height(16.dp))


            // Save Button
            CustomButton(
                label = "Continue",
                onClick = { onDismiss },
                required = true,
                modifier = Modifier.padding(bottom = 8.dp) // Optional custom modifier
            )

            Spacer(modifier = Modifier.height(5.dp))

        }
    }
}
