package com.sora.sora.features.profile.address.screen


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sora.sora.core.CustomAppBar
import com.sora.sora.core.customButtons.CustomButton
import com.sora.sora.core.customText.CustomMontserratText
import com.sora.sora.core.hFactor
import com.sora.sora.core.navigations.NavigationManager.navController
import com.sora.sora.core.vFactor
import com.sora.sora.features.profile.screen.Country
import com.sora.sora.features.profile.screen.PhoneNumberInputField
import com.sora.sora.ui.components.AppTextField2
import com.sora.sora.ui.theme.PrimaryColor
import com.sora.sora.ui.theme.TextFieldBackgroundColors
import com.sora.sora.ui.theme.TextFieldBorderColors
import com.sora.sora.ui.theme.TextHintColor

/**working butw save toast not showing [error]*/
//@Composable
//fun AddNewAddressScreen() {
//
//    // Form field states
//    var name by remember { mutableStateOf("") }
//    var phoneNumber by remember { mutableStateOf("") }
//    var governorate by remember { mutableStateOf("") }
//    var area by remember { mutableStateOf("") }
//    var block by remember { mutableStateOf("") }
//    var houseNo by remember { mutableStateOf("") }
//    var additionalDetails by remember { mutableStateOf("") }
//
//    val governorates = listOf("Kuwait City", "Hawally", "Salmiya", "Jahra")
//    var expandedGov by remember { mutableStateOf(false) }
//
//    val areas = listOf("Area 1", "Area 2", "Area 3", "Area 4")
//    var expandedArea by remember { mutableStateOf(false) }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(25.dp)
//            .statusBarsPadding()
//    ) {
////        // Back Button Section
////        Row(verticalAlignment = Alignment.CenterVertically) {
////            IconButton(onClick = { /* Handle back click */ }) {
////                Icon(
////                    imageVector = Icons.Filled.ArrowBack,
////                    contentDescription = "Back",
////                    modifier = Modifier
////                        .background(Color.Gray.copy(alpha = 0.2f), shape = CircleShape)
////                        .padding(10.dp)
////                )
////            }
////            Spacer(modifier = Modifier.width(48.dp))
////            Text(
////                text = "Add New Address",
////                fontSize = 24.sp,
////                fontWeight = FontWeight.SemiBold,
////                color = Color.Black
////            )
////        }
//
//        CustomTopBar(title = "Add New Address")
//
//        Spacer(modifier = Modifier.height(24.dp))
//
//        // Name Field
//        AppTextField2(
//            placeholder = "Enter Name",
//            value = name,
//            onValueChange = { name = it },
//            keyboardType = KeyboardType.Text,
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Phone Number Field
//        AppTextField2(
//            placeholder = "Phone number",
//            value = phoneNumber,
//            onValueChange = { phoneNumber = it },
//            keyboardType = KeyboardType.Phone,
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Governorate Dropdown
//        CustomDropdown(
//            label = "Governorate (City)",
//            items = governorates,
//            selectedItem = governorate,
//            onItemSelected = { governorate = it },
//            expanded = expandedGov,
//            onExpandChanged = { expandedGov = it },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Area Dropdown
//        CustomDropdown(
//            label = "Area",
//            items = areas,
//            selectedItem = area,
//            onItemSelected = { area = it },
//            expanded = expandedArea,
//            onExpandChanged = { expandedArea = it },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Block Field
//        AppTextField2(
//            placeholder = "Block",
//            value = block,
//            onValueChange = { block = it },
//            keyboardType = KeyboardType.Text,
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // House No Field
//        AppTextField2(
//            placeholder = "House No",
//            value = houseNo,
//            onValueChange = { houseNo = it },
//            keyboardType = KeyboardType.Text,
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Additional Details Field
//        AppTextField2(
//            placeholder = "Any additional details",
//            value = additionalDetails,
//            onValueChange = { additionalDetails = it },
//            keyboardType = KeyboardType.Text,
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(32.dp))
//
//        // Save Button
//        CustomButton(
//            label = "Save",
//            onClick = { /* Handle Save click */ },
//            required = true,
//            modifier = Modifier.padding(bottom = 8.dp) // Optional custom modifier
//                .clickable(
//                    onClick = {
//                        // Handle Save click
//                        // Show the Toast inside the Composable context
//                        val context = LocalContext.current
//                        Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
//                    },
//                )
//
//        )
//
//        Spacer(modifier = Modifier.height(5.dp))
//
//    }
//
//}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun CustomDropdown(
//    label: String,
//    items: List<String>,
//    selectedItem: String,
//    onItemSelected: (String) -> Unit,
//    expanded: Boolean,
//    onExpandChanged: (Boolean) -> Unit,
//    modifier: Modifier = Modifier
//) {
//    // Dropdown Menu
//    Box(modifier = modifier) {
//        OutlinedTextField(
//            value = selectedItem,
//            onValueChange = {},
//            label = { Text(label) },
//            modifier = Modifier.fillMaxWidth(),
//            readOnly = true,
//            trailingIcon = {
//                Icon(
//                    imageVector = Icons.Filled.ArrowDropDown,
//                    contentDescription = "Dropdown Arrow"
//                )
//            },
//            colors = TextFieldDefaults.outlinedTextFieldColors(
//                focusedBorderColor = PrimaryColor,
//                unfocusedBorderColor = Color.Gray
//            ),
//            shape = RoundedCornerShape(12.dp)
//        )
//
//        DropdownMenu(
//            expanded = expanded,
//            onDismissRequest = { onExpandChanged(false) }
//        ) {
//            items.forEach { item ->
//                DropdownMenuItem(
//                    onClick = {
//                        onItemSelected(item)
//                        onExpandChanged(false)
//                    },
//                    text = { Text(item) }
//                )
//            }
//        }
//    }
//
//    // Handle the dropdown expansion state when clicked
//    Modifier.clickable { onExpandChanged(true) }
//}




@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewAddressScreen() {
    // Form field states
    var name by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var governorate by remember { mutableStateOf("") }
    var area by remember { mutableStateOf("") }
    var block by remember { mutableStateOf("") }
    var houseNo by remember { mutableStateOf("") }
    var street by remember { mutableStateOf("") }
    var additionalDetails by remember { mutableStateOf("") }
    var selectedCountry by remember { mutableStateOf(Country("🇸🇦", "+966", "Saudi Arabia")) }

    val governorates = listOf("Kuwait City", "Hawally", "Salmiya", "Jahra")
    var expandedGov by remember { mutableStateOf(false) }

    val areas = listOf("Area 1", "Area 2", "Area 3", "Area 4")
    var expandedArea by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        CustomAppBar(

            title = "Add New Address",
            onBackClick = {
                // Handle back click, navigate back or pop from the navigation stack
                navController.popBackStack()
            },

        )
        Column(
            modifier = Modifier
                .fillMaxSize()
            //    .statusBarsPadding()
                .padding(horizontal = hFactor(20))
        ) {
            Spacer(modifier = Modifier.height(vFactor(24)))

            // Name Field
            AppTextField2(
                placeholder = "Full Name",
                value = name,
                onValueChange = { name = it },
                keyboardType = KeyboardType.Text,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))
            PhoneNumberInputField(
                selectedCountry = selectedCountry,
                onCountrySelected = { selectedCountry = it },
                phoneNumber = phoneNumber,
                onPhoneNumberChange = { phoneNumber = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Governorate Dropdown
            CustomDropdown(
                label = "Governorate (City)",
                items = governorates,
                selectedItem = governorate,
                onItemSelected = { governorate = it },
                expanded = expandedGov,
                onExpandChanged = { expandedGov = it },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

//            // Area Dropdown
//            CustomDropdown(
//                label = "Area",
//                items = areas,
//                selectedItem = area,
//                onItemSelected = { area = it },
//                expanded = expandedArea,
//                onExpandChanged = { expandedArea = it },
//                modifier = Modifier.fillMaxWidth()
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))

    //        Area Field
            AppTextField2(
                placeholder = "Area",
                value = area,
                onValueChange = { area = it },
                keyboardType = KeyboardType.Text,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))


    //         Block Field
            AppTextField2(
                placeholder = "Block",
                value = block,
                onValueChange = { block = it },
                keyboardType = KeyboardType.Text,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))


            // House No Field
            AppTextField2(
                placeholder = "Street",
                value = street,
                onValueChange = { street = it },
                keyboardType = KeyboardType.Text,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))
            AppTextField2(
                placeholder = "House No",
                value = houseNo,
                onValueChange = { houseNo = it },
                keyboardType = KeyboardType.Text,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Additional Details Field
            AppTextField2(
                placeholder = "Any additional details",
                value = additionalDetails,
                onValueChange = { additionalDetails = it },
                keyboardType = KeyboardType.Text,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(vFactor(40)))

            // Save Button
            CustomButton(
                label = "Save",
                onClick = {
                    Toast.makeText(context, "New Address Saved", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()  //
                },

                modifier = Modifier.padding(bottom = 8.dp) // Optional custom modifier
            )

            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDropdown(
    label: String,
    items: List<String>,
    selectedItem: String,
    onItemSelected: (String) -> Unit,
    expanded: Boolean,
    onExpandChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { onExpandChanged(!expanded) } // Toggle dropdown on click
        ) {
            TextField(
                value = selectedItem,
                onValueChange = {},
                modifier = Modifier
                    .menuAnchor() // ⬅️ important: anchor the dropdown
                    .fillMaxWidth()
                    .height(52.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        color = TextFieldBackgroundColors,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = if (selectedItem.isNotEmpty()) TextFieldBorderColors else Color.Transparent,
                        shape = RoundedCornerShape(12.dp)
                    ),
                readOnly = true,
                singleLine = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                placeholder = {
                    CustomMontserratText(
                        text = label,
                        color = TextHintColor,
                        fontWeight = FontWeight(500),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Start,
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    focusedLabelColor = Color.Transparent,
                    cursorColor = PrimaryColor,
                    containerColor = TextFieldBackgroundColors,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
            )

            // Dropdown items
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { onExpandChanged(false) }
            ) {
                items.forEach { item ->
                    DropdownMenuItem(
                        text = {
                            CustomMontserratText(
                                text = item,
                                color = PrimaryColor,
                                fontSize = 16.sp
                            )
                        },
                        onClick = {
                            onItemSelected(item)
                            onExpandChanged(false)
                        }
                    )
                }
            }
        }
    }
}

/**Working code withou dropdown*/
//@Composable
//fun AddNewAddressScreen() {
//
//    // Form field states
//    var name by remember { mutableStateOf("") }
//    var phoneNumber by remember { mutableStateOf("") }
//    var governorate by remember { mutableStateOf("") }
//    var area by remember { mutableStateOf("") }
//    var block by remember { mutableStateOf("") }
//    var houseNo by remember { mutableStateOf("") }
//    var additionalDetails by remember { mutableStateOf("") }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//            .statusBarsPadding()
//            .background(Color.White)
//    ) {
//        // Back Button Section
//        Row(verticalAlignment = Alignment.CenterVertically) {
//            IconButton(onClick = { /* Handle back click */ }) {
//                Icon(
//                    imageVector = Icons.Filled.ArrowBack,
//                    contentDescription = "Back",
//                    modifier = Modifier
//                        .background(Color.Gray.copy(alpha = 0.2f), shape = CircleShape)
//                        .padding(10.dp)
//                )
//            }
//            Spacer(modifier = Modifier.width(48.dp))
//            Text(
//                text = "Add New Address",
//                fontSize = 24.sp,
//                fontWeight = FontWeight.SemiBold,
//                color = Color.Black
//            )
//        }
//
//        Spacer(modifier = Modifier.height(24.dp))
//
//        // Name Field
//        AppTextField2(
//            placeholder = "Enter Name",
//            value = name,
//            onValueChange = { name = it },
//            keyboardType = KeyboardType.Text,
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Phone Number Field
//        AppTextField2(
//            placeholder = "Phone number",
//            value = phoneNumber,
//            onValueChange = { phoneNumber = it },
//            keyboardType = KeyboardType.Phone,
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Governorate (City) Field
//        AppTextField2(
//            placeholder = "Governorate (City)",
//            value = governorate,
//            onValueChange = { governorate = it },
//            keyboardType = KeyboardType.Text,
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Area Field
//        AppTextField2(
//            placeholder = "Area",
//            value = area,
//            onValueChange = { area = it },
//            keyboardType = KeyboardType.Text,
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Block Field
//        AppTextField2(
//            placeholder = "Block",
//            value = block,
//            onValueChange = { block = it },
//            keyboardType = KeyboardType.Text,
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // House No Field
//        AppTextField2(
//            placeholder = "House No",
//            value = houseNo,
//            onValueChange = { houseNo = it },
//            keyboardType = KeyboardType.Text,
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Additional Details Field
//        AppTextField2(
//            placeholder = "Any additional details",
//            value = additionalDetails,
//            onValueChange = { additionalDetails = it },
//            keyboardType = KeyboardType.Text,
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(32.dp))
//
//        // Save Button
//        Button(
//            onClick = { /* Handle save click */ },
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(57.dp),
//            colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
//            shape = MaterialTheme.shapes.medium
//        ) {
//            Text(text = "Save", color = Color.White, fontSize = 16.sp)
//        }
//        Spacer(modifier = Modifier.height(5.dp))
//    }
//}



