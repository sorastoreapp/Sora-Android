
package com.sora.sora.features.profile.screen

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import coil.compose.rememberAsyncImagePainter
import coil.size.Scale
import com.sora.sora.R
import com.sora.sora.core.CustomAppBar
import com.sora.sora.core.customButtons.CustomButton
import com.sora.sora.core.customText.CustomMontserratText
import com.sora.sora.core.hFactor
import com.sora.sora.core.navigations.NavigationManager.navController
import com.sora.sora.core.vFactor
import com.sora.sora.features.profile.widgets.DeleteAccountBottomSheet
import com.sora.sora.ui.components.AppTextField2
import com.sora.sora.ui.components.AppTextFieldWithSuffix
import com.sora.sora.ui.theme.AppGray
import com.sora.sora.ui.theme.AppTextGray
import com.sora.sora.ui.theme.PrimaryColor
import com.sora.sora.ui.theme.TextFieldBackgroundColors
import com.sora.sora.ui.theme.TextFieldBorderColors
import com.sora.sora.ui.theme.TextHintColor
import java.io.File


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

    Scaffold (
        containerColor = Color.White,


        modifier = Modifier
            .background(Color.White)
            .padding(
                bottom = WindowInsets.systemBars
                    .asPaddingValues()
                    .calculateBottomPadding()
            )
            .fillMaxSize(),

        topBar = {
            CustomAppBar(
                title = "Edit Profile",
                isBackButton = true,
                onBackClick = {
                    // Handle back click, navigate back or pop from the navigation stack
                    navController.popBackStack()
                },
            )
        }

    ) {
            paddingValues ->

        // Column for text content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(top = 80.dp)
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(vFactor(30)))

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = vFactor(10))
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
                    // Use ProfileImagePicker composable to handle image selection
                    ProfileImagePicker()
                }


                Spacer(modifier = Modifier.height(1.dp))

                AppTextFieldWithSuffix(
                    value = name,
                    onValueChange = { name = it },
                    placeholder = "Full Name",
                    keyboardType = KeyboardType.Text,
                    suffix = {
                        Icon(painter = painterResource(id = R.drawable.ic_profile2), contentDescription = "Some Icon",tint = PrimaryColor)
                    },
                    modifier = Modifier.fillMaxWidth()
                )


                PhoneNumberInputField(
                    selectedCountry = selectedCountry,
                    onCountrySelected = { selectedCountry = it },
                    phoneNumber = phone,
                    onPhoneNumberChange = { phone = it }
                )

                // Email field
                AppTextFieldWithSuffix(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = "Email",
                    suffix = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_email),
                            contentDescription = "Email",
                            tint = PrimaryColor
                        )
                    },
                    keyboardType = KeyboardType.Email,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp)) // Spacer before buttons
            }
            Spacer(modifier = Modifier.weight(1f))

            // Buttons at the bottom
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CustomButton(
                    label   = "Update",
                    onClick = {
                    navController.popBackStack()
                    }
                )

                CustomButton(
                    label = "Delete Account?",
                    onClick = { showBottomSheet.value = true },
                    secondaryButton = true
                )
            }
            Spacer(modifier = Modifier.height(20.dp)) // Spacer
        }


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
fun ProfileImagePicker() {
    val context = LocalContext.current

    // State to store the selected image URI
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    // State for showing the dialog
    var showDialog by remember { mutableStateOf(false) }

    // Launcher to pick from gallery
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    // Launcher to take a photo using the camera
    val cameraImageUri = remember { mutableStateOf<Uri?>(null) }
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) imageUri = cameraImageUri.value
    }

    // Function to launch the camera
    fun launchCamera() {
        val file = File(context.cacheDir, "profile_picture_${System.currentTimeMillis()}.jpg")
        val uri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider", // Ensure this is correct
            file
        )
        cameraImageUri.value = uri
        // Granting permissions for the URI
        context.grantUriPermission(
            context.packageName,
            uri,
            Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
        )
        cameraLauncher.launch(uri)
    }

    // Dialog to select between Gallery and Camera
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Select an option") },
            text = { Text("Choose where to pick the image from") },
            confirmButton = {
                TextButton(
                    onClick = {
                        galleryLauncher.launch("image/*")
                        showDialog = false
                    }
                ) {
                    Text("Gallery")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        launchCamera()
                        showDialog = false
                    }
                ) {
                    Text("Camera")
                }
            }
        )
    }

    Box(
        Modifier
            .width(87.dp)
            .height(88.dp)
            .background(Color(0x1A8A4C3D), shape = RoundedCornerShape(9999.dp))
    ) {
        if (imageUri != null) {
            Image(
                painter = rememberAsyncImagePainter(imageUri),
                contentDescription = "Profile Picture",
                modifier = Modifier.size(90.dp).clip(CircleShape)
            )
        } else {
            // Default placeholder if no image is selected
            Image(
                painter = painterResource(R.drawable.img_temp_profile_pic),
                contentDescription = "Profile Picture",
                modifier = Modifier.size(90.dp).clip(CircleShape)
            )
        }

        // Camera icon button to open the dialog
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(30.dp)
                .background(PrimaryColor)
                .border(2.dp, Color.White, CircleShape)
                .padding(8.dp)
                .align(Alignment.BottomEnd)
                .clickable {
                    // Show the dialog to choose between Gallery and Camera
                    showDialog = true
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_camera),
                contentDescription = "Camera",
                tint = Color.White,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}


//@Composable
//fun ProfileImagePicker() {
//    val context = LocalContext.current
//
//    // State to store the selected image URI
//    var imageUri by remember { mutableStateOf<Uri?>(null) }
//
//    // State for showing the dialog
//    var showDialog by remember { mutableStateOf(false) }
//
//    // Launcher to pick from gallery
//    val galleryLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.GetContent()
//    ) { uri: Uri? ->
//        imageUri = uri
//    }
//
//    // Launcher to take a photo using the camera
//    val cameraImageUri = remember { mutableStateOf<Uri?>(null) }
//    val cameraLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.TakePicture()
//    ) { success ->
//        if (success) imageUri = cameraImageUri.value
//    }
//
//    // Function to launch the camera
//    fun launchCamera() {
//        val file = File(context.cacheDir, "profile_picture_${System.currentTimeMillis()}.jpg")
//        val uri = FileProvider.getUriForFile(
//            context,
//            "${context.packageName}.provider",
//            file
//        )
//        cameraImageUri.value = uri
//        cameraLauncher.launch(uri)
//    }
//
//    // Dialog to select between Gallery and Camera
//    if (showDialog) {
//        AlertDialog(
//            onDismissRequest = { showDialog = false },
//            title = { Text("Select an option") },
//            text = { Text("Choose where to pick the image from") },
//            confirmButton = {
//                TextButton(
//                    onClick = {
//                        galleryLauncher.launch("image/*")
//                        showDialog = false
//                    }
//                ) {
//                    Text("Gallery")
//                }
//            },
//            dismissButton = {
//                TextButton(
//                    onClick = {
//                        launchCamera()
//                        showDialog = false
//                    }
//                ) {
//                    Text("Camera")
//                }
//            }
//        )
//    }
//
//    Box(
//        Modifier
//            .width(87.dp)
//            .height(88.dp)
//            .background(Color(0x1A8A4C3D), shape = RoundedCornerShape(9999.dp))
//    ) {
//        if (imageUri != null) {
//            Image(
//                painter = rememberAsyncImagePainter(imageUri),
//                contentDescription = "Profile Picture",
//                modifier = Modifier.size(90.dp).clip(CircleShape)
//            )
//        } else {
//            // Default placeholder if no image is selected
//            Image(
//                painter = painterResource(R.drawable.img_temp_profile_pic),
//                contentDescription = "Profile Picture",
//                modifier = Modifier.size(90.dp).clip(CircleShape)
//            )
//        }
//
//        // Camera icon button to open the dialog
//        Box(
//            modifier = Modifier
//                .clip(CircleShape)
//                .size(30.dp)
//                .background(PrimaryColor)
//                .border(2.dp, Color.White, CircleShape)
//                .padding(8.dp)
//                .align(Alignment.BottomEnd)
//                .clickable {
//                    // Show the dialog to choose between Gallery and Camera
//                    showDialog = true
//                },
//            contentAlignment = Alignment.Center
//        ) {
//            Icon(
//                painter = painterResource(R.drawable.ic_camera),
//                contentDescription = "Camera",
//                tint = Color.White,
//                modifier = Modifier.size(18.dp)
//            )
//        }
//    }
//}

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
            .height(60.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // País selector
        Box(
            modifier = Modifier
                .height(60.dp)
                .wrapContentWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(TextFieldBackgroundColors)
                .border(1.dp, TextFieldBorderColors, RoundedCornerShape(20.dp))
                .clickable { expanded = true }
                .padding(horizontal = 15.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                CustomMontserratText(
                    text = "${selectedCountry.flag}   ${selectedCountry.code}",
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
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight(500),

                )
            },
            modifier = Modifier
                .weight(1f)
                .height(60.dp)
                .clip(RoundedCornerShape(20.dp)) // Aplicar el clip a la caja del contorno
                .background(
                    color = TextFieldBackgroundColors,
                    shape = RoundedCornerShape(20.dp) )
                .border(
                    width = 1.dp,
                    color = if (phoneNumber.isNotEmpty()) TextFieldBorderColors else Color.Transparent,
                    shape = RoundedCornerShape(20.dp)  // Same shape for border
                ),


            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedBorderColor = TextFieldBorderColors,
                unfocusedBorderColor = Color.Transparent
            ),


            shape = RoundedCornerShape(20.dp), // Aplica el borde redondeado aquí también
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            textStyle = LocalTextStyle.current.copy(
                textAlign = TextAlign.Start // Ensures the input text is left-aligned
            )



        )


        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false
            },
            modifier = Modifier.background(Color.White)

                .padding(horizontal = hFactor(10))
                .fillMaxWidth()

        ) {
            countries.forEach { country ->
                DropdownMenuItem(
                    colors = MenuDefaults.itemColors(textColor = AppTextGray, trailingIconColor = Color.White),
                    //modifier = Modifier.background(Color.White),
                    contentPadding = MenuDefaults.DropdownMenuItemContentPadding ,

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
