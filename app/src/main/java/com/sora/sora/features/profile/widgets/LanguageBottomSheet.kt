package com.sora.sora.features.profile.widgets

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sora.sora.R
import com.sora.sora.core.customButtons.CustomButton
import com.sora.sora.core.customText.CustomMontserratText
import com.sora.sora.core.hFactor
import com.sora.sora.core.vFactor

import com.sora.sora.ui.theme.PrimaryColor

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
        onDismissRequest = onDismiss,
        containerColor = Color.White,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp) // optional rounded corners
    ) {
        Column(
            modifier = Modifier.padding(horizontal = hFactor(24), vertical = vFactor(0))
        ) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                CustomMontserratText(
                    text = "Select Language",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryColor,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = "Cross",
                    tint = Color.Gray,
                    modifier = Modifier
                        .padding(end = 5.dp)
                        .clickable { onDismiss() }
                )
            }
            Spacer(modifier = Modifier.height(vFactor(5)))

            languages.forEachIndexed {
                    index, language ->
                ProfileMenuItem(
                    showArrow = false,
                    textFontSize = 22.sp,
                    iconRes = languageIcons[index],

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .pointerInput(Unit) {
                            // Custom touch handling for Skip Button without ripple effect
                            detectTapGestures {
                                Log.d("ProfileScreen", "My orders clicked")
                            }
                        }
                        .padding(start = 8.dp, end = 0.dp),


                    title = language,
                    onClick = {
                        // Log.d("ProfileScreen", "My orders clicked")

                    },
                    trailingContent = {
                        if (selectedLanguage.value == language) Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(
                                    Color(0x1A4CAF50)

                                ).padding(5.dp)
                                .size(15.dp)


                        ) {
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = "Selected",
                                tint = Color(0XFF07BD74)

                            )
                        } else Box(modifier = Modifier.size(0.dp))
                    }
                )
                Spacer(modifier = Modifier.height(vFactor(3)))
                if (selectedLanguage.value == language)
                    CommonDivider()
            }

            Spacer(modifier = Modifier.height(16.dp))


            // Save Button
            CustomButton(
                label = "Save",
                onClick = { onDismiss },

                modifier = Modifier.padding(bottom = 8.dp) // Optional custom modifier
            )

            Spacer(modifier = Modifier.height(10.dp))

        }
    }
}