
package com.sora.sora.features.profile.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.size.Scale
import com.sora.sora.R
import com.sora.sora.core.CustomAppBar
import com.sora.sora.core.customButtons.CustomButton
import com.sora.sora.core.customText.CustomMontserratText
import com.sora.sora.core.navigations.NavigationManager.navController
import com.sora.sora.core.vFactor
import com.sora.sora.features.profile.widgets.DeleteAccountBottomSheet
import com.sora.sora.ui.components.AppTextField2
import com.sora.sora.ui.theme.AppGray
import com.sora.sora.ui.theme.PrimaryColor
import com.sora.sora.ui.theme.TextFieldBackgroundColors
import com.sora.sora.ui.theme.TextFieldBorderColors
import com.sora.sora.ui.theme.TextHintColor


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun EditProfileScreen() {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    val showBottomSheet = remember { mutableStateOf(false) }

    var selectedCountry by remember { mutableStateOf(Country("🇸🇦", "+966", "Saudi Arabia")) }

    val scrollState = rememberScrollState()



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .verticalScroll(scrollState)
    ) {
        CustomAppBar(
            title = "Edit Profile",
            onBackClick = { navController.popBackStack() },
            //modifier = Modifier.align(Alignment.TopCenter)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()

                .padding(horizontal = 16.dp)
                .statusBarsPadding()

                .background(color = Color.White),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Profile Image + Camera Icon
            Box(
                Modifier
                    .width(87.dp)
                    .height(88.dp)
                    .background(
                        color = Color(0x1A8A4C3D),
                        shape = RoundedCornerShape(size = 9999.dp)
                    )
                    .align(Alignment.CenterHorizontally)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_temp_profile_pic), // Replace with the actual image resource
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(88.dp)
                        .clip(CircleShape)
                )
                Box(
                    modifier = Modifier
                        .padding(1.2.dp)
                        .width(24.dp)
                        .height(24.dp)
                        .clip(CircleShape)
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
            Spacer(modifier = Modifier.height(vFactor(15)))

            // Name field
            AppTextField2(
                value = name,
                onValueChange = { name = it },
                placeholder = "Faisal Alajmi",
                keyboardType = KeyboardType.Text
            )

            // Phone field with country selector
            PhoneNumberInputField(
                selectedCountry = selectedCountry,
                onCountrySelected = { selectedCountry = it },
                phoneNumber = phone,
                onPhoneNumberChange = { phone = it }
            )

            // Email field
            AppTextField2(
                value = email,
                onValueChange = { email = it },
                placeholder = "Faisalajmi95@gmail.com",
                keyboardType = KeyboardType.Email
            )

            Spacer(modifier = Modifier.height(16.dp)) // Spacer before buttons
        }
        Spacer(modifier = Modifier.weight(1f))

        // Buttons at the bottom
        Column(
            modifier = Modifier
                .fillMaxWidth()
               // .align(Alignment.BottomCenter)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CustomButton(
                label = "Update",
                onClick = { /* TODO: Handle update logic */ }
            )

            CustomButton(
                label = "Delete Account?",
                onClick = { showBottomSheet.value = true },
                secondaryButton = true
            )
        }
        Spacer(modifier = Modifier.height(20.dp)) // Spacer
    }

    // Show bottom sheet if required
    if (showBottomSheet.value) {
        DeleteAccountBottomSheet(
            orderId = "orderId",
            onDismiss = { showBottomSheet.value = false },
            onCancelOrder = {
                showBottomSheet.value = false
            }
        )
    }
}


@Composable
fun PhoneNumberInputField(
    selectedCountry: Country,
    onCountrySelected: (Country) -> Unit,
    phoneNumber: String,
    onPhoneNumberChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    val countries = listOf(
        Country("🇸🇦", "+966", "Saudi Arabia"),
        Country("🇺🇸", "+1", "United States"),
        Country("🇬🇧", "+44", "United Kingdom"),
        Country("🇮🇳", "+91", "India")
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // País selector
        Box(
            modifier = Modifier
                .height(52.dp)
                .wrapContentWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(TextFieldBackgroundColors)
                .border(1.dp, TextFieldBorderColors, RoundedCornerShape(12.dp))
                .clickable { expanded = true }
                .padding(horizontal = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                CustomMontserratText(
                    text = "${selectedCountry.flag} ${selectedCountry.code}",
                    color = Color.Black,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Start,
                )

                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Expand Country List",
                    tint = Color.Black.copy(alpha = 0.4f),
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .size(20.dp)
                )
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        // Campo de texto para número
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = onPhoneNumberChange,
            placeholder = {
                CustomMontserratText(
                    text = "Phone number",
                    color = TextHintColor,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Start
                )
            },
            modifier = Modifier
                .weight(1f)
                .height(52.dp)
                .clip(RoundedCornerShape(12.dp)) // Aplicar el clip a la caja del contorno
                .background(TextFieldBackgroundColors)
                .border(
                    width = 1.dp,
                    color =  Color.Transparent,  // Set border color based on focus
                    shape = RoundedCornerShape(12.dp)  // Same shape for border
                ),

            // Fondo del campo de texto

            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedBorderColor = TextFieldBorderColors,
                unfocusedBorderColor = Color.Transparent
            ),


            shape = RoundedCornerShape(12.dp), // Aplica el borde redondeado aquí también
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),


        )


        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            countries.forEach { country ->
                DropdownMenuItem(
                    text = { Text("${country.flag} ${country.name} (${country.code})") },
                    onClick = {
                        onCountrySelected(country)
                        expanded = false
                    }
                )
            }
        }
    }
}


// Clase Country
data class Country(
    val flag: String,
    val code: String,
    val name: String
)
