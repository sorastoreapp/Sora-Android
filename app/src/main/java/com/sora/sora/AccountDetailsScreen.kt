package com.sora.sora

import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sora.sora.R
import com.sora.sora.ui.components.AppTextField
import com.sora.sora.ui.theme.PrimaryColor
import com.sora.sora.ui.theme.PrimaryColorFaded
import com.sora.sora.ui.theme.TextFieldColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountDetailsScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onContinueClick: (AccountDetailsData) -> Unit
) {
    // States
    val countryCodes = listOf("+49", "+1", "+44")
    var expandedCountryCode by remember { mutableStateOf(false) }
    var selectedCountryCode by remember { mutableStateOf(countryCodes[0]) }

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var address1 by remember { mutableStateOf("") }
    var address2 by remember { mutableStateOf("") }
    var country by remember { mutableStateOf("") }
    var zipCode by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var state by remember { mutableStateOf("") }
    var expandedCountry by remember { mutableStateOf(false) }
    var expandedState by remember { mutableStateOf(false) }
    var agreed by remember { mutableStateOf(false) }

    // Scroll state
    val scrollState = rememberScrollState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        // Background Image fills the Box
        Image(
            painter = painterResource(id = R.drawable.account_details_bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Overlay a semi-transparent background (optional, to improve text visibility)
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(Color(0x66000000))
//        )

        // Content Column (scrolls along with background)
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
                    onClick = onBackClick,
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
                    text = "Account Details",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 40.dp)
                )
            }

            // Profile Image + Lock Icon
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile_placeholder),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .border(2.dp, PrimaryColor, CircleShape)
                )
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(PrimaryColor)
                        .align(Alignment.BottomEnd),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = "Locked",
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            // Form Fields (use your AppTextField or others)
            AppTextField(
                value = name,
                onValueChange = { name = it },
                placeholder = "Leslie Alexander",
                prefixIcon = painterResource(id = R.drawable.ic_profile),
                modifier = Modifier.fillMaxWidth()
            )

            // Email field
            AppTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = "you@mail.com",
                keyboardType = KeyboardType.Email,
                prefixIcon = painterResource(id = R.drawable.ic_mail),
                modifier = Modifier.fillMaxWidth()
            )

            // Phone number with country code selector
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(Color(0xFFF2F2F2))
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box {
                    Row(
                        modifier = Modifier
                            .clickable { expandedCountryCode = true }
                            .padding(horizontal = 8.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(selectedCountryCode, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = "Select Country Code"
                        )
                    }
                    DropdownMenu(
                        expanded = expandedCountryCode,
                        onDismissRequest = { expandedCountryCode = false }
                    ) {
                        countryCodes.forEach { code ->
                            DropdownMenuItem(
                                text = { Text(code) },
                                onClick = {
                                    selectedCountryCode = code
                                    expandedCountryCode = false
                                }
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.width(4.dp))
                Box(
                    modifier = Modifier
                        .width(1.dp)
                        .height(32.dp)
                        .background(Color.Gray)
                )
                Spacer(modifier = Modifier.width(4.dp))

                TextField(
                    value = phone,
                    onValueChange = { phone = it },
                    placeholder = { Text(text = "(308) 555-0121") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                )
            }

            // Address 1

//            AppTextField(
//                value = address1,
//                onValueChange = { address1 = it },
//                placeholder = "Address 1",
//                modifier = Modifier.fillMaxWidth(),
//                singleLine = true,
//                colors = TextFieldDefaults.outlinedTextFieldColors(
//                    focusedBorderColor = PrimaryColor,
//                    unfocusedBorderColor = Color.LightGray
//                )
//            )

            // Email field
            AppTextField(
                value = address1,
                onValueChange = { address1 = it },
                placeholder = "Address1",
                keyboardType = KeyboardType.Email,
                modifier = Modifier.fillMaxWidth()
            )

//            OutlinedTextField(
//                value = address1,
//                onValueChange = { address1 = it },
//                modifier = Modifier.fillMaxWidth(),
//                label = { Text("Address 1") },
//                singleLine = true,
//                colors = TextFieldDefaults.outlinedTextFieldColors(
//                    focusedBorderColor = PrimaryColor,
//                    unfocusedBorderColor = Color.LightGray
//                )
//            )

            // Email field
            AppTextField(
                value = address2,
                onValueChange = { address2 = it },
                placeholder = "Address2",
                keyboardType = KeyboardType.Email,
                modifier = Modifier.fillMaxWidth()
            )

            // Country selector dropdown
            // Country selector dropdown with background same as AppTextField
            Box {
                TextField(
                    value = country,
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(TextFieldColor, RoundedCornerShape(12.dp))
                        .clickable { expandedCountry = true },
                    readOnly = true,
                    placeholder = { Text("Select Country", color = Color.Gray) },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = "Select Country"
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = TextFieldColor,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
                DropdownMenu(
                    expanded = expandedCountry,
                    onDismissRequest = { expandedCountry = false }
                ) {
                    listOf("USA", "Germany", "France", "India").forEach { ctry ->
                        DropdownMenuItem(
                            text = { Text(ctry) },
                            onClick = {
                                country = ctry
                                expandedCountry = false
                            }
                        )
                    }
                }
            }

            // Zip Code
            AppTextField(
                value = zipCode,
                onValueChange = { zipCode = it },
                placeholder = "Enter Zip Code",
                keyboardType = KeyboardType.Email,
                modifier = Modifier.fillMaxWidth()
            )
            // city
            AppTextField(
                value = city,
                onValueChange = { city = it },
                placeholder = "Enter City",
                keyboardType = KeyboardType.Email,
                modifier = Modifier.fillMaxWidth()
            )

            // State selector dropdown
            Box {
                TextField(
                    value = state,
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(TextFieldColor, RoundedCornerShape(12.dp))
                        .clickable { expandedState = true },
                    readOnly = true,
                    placeholder = { Text("Select State", color = Color.Gray) },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = "Select State"
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = TextFieldColor,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
                DropdownMenu(
                    expanded = expandedState,
                    onDismissRequest = { expandedState = false }
                ) {
                    listOf("California", "Texas", "New York").forEach { st ->
                        DropdownMenuItem(
                            text = { Text(st) },
                            onClick = {
                                state = st
                                expandedState = false
                            }
                        )
                    }
                }
            }
            // Terms checkbox and multi-line text
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Checkbox(
                    checked = agreed,
                    onCheckedChange = { agreed = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = PrimaryColor
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "I agree to the ",
                        fontSize = 14.sp
                    )
                    Row {
                        Text(
                            text = "Terms & Conditions",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryColor,
                            modifier = Modifier.clickable { /* TODO: handle click */ }
                        )
                        Text(
                            text = " and ",
                            fontSize = 14.sp
                        )
                        Text(
                            text = "Privacy Policy",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryColor,
                            modifier = Modifier.clickable { /* TODO: handle click */ }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(5.dp))

            Button(
                onClick = {
                    if (agreed) {
                        val data = AccountDetailsData(
                            name, email, selectedCountryCode + phone, address1,
                            address2, country, zipCode, city, state, agreed
                        )
                        onContinueClick(data)
                    } else {
                        // Show message or handle not agreed case
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor)
            ) {
                Text("Continue", color = Color.White, fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}


/***Not working */
data class AccountDetailsData(
    val name: String,
    val email: String,
    val phone: String,
    val address1: String,
    val address2: String,
    val country: String,
    val zipCode: String,
    val city: String,
    val state: String,
    val agreedTerms: Boolean
)

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun AccountDetailsScreen(
//    modifier: Modifier = Modifier,
//    onBackClick: () -> Unit,
//    onContinueClick: (AccountDetailsData) -> Unit
//) {
//    // States
//    val countryCodes = listOf("+49", "+1", "+44")
//    var expandedCountryCode by remember { mutableStateOf(false) }
//    var selectedCountryCode by remember { mutableStateOf(countryCodes[0]) }
//
//    var name by remember { mutableStateOf("") }
//    var email by remember { mutableStateOf("") }
//    var phone by remember { mutableStateOf("") }
//    var address1 by remember { mutableStateOf("") }
//    var address2 by remember { mutableStateOf("") }
//    var country by remember { mutableStateOf("") }
//    var zipCode by remember { mutableStateOf("") }
//    var city by remember { mutableStateOf("") }
//    var state by remember { mutableStateOf("") }
//    var expandedCountry by remember { mutableStateOf(false) }
//    var expandedState by remember { mutableStateOf(false) }
//    var agreed by remember { mutableStateOf(false) }
//
//    Box(modifier = modifier.fillMaxSize()) {
//        // Background Image - full width & height
//        Image(
//            painter = painterResource(id = R.drawable.account_details_bg),
//            contentDescription = null,
//            modifier = Modifier.fillMaxSize(),
//            contentScale = ContentScale.Crop
//        )
//
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .align(Alignment.TopCenter)
//                .padding(horizontal = 24.dp, vertical = 90.dp)
//                .verticalScroll(rememberScrollState()),
//            verticalArrangement = Arrangement.spacedBy(16.dp)
//        ) {
//            // Top Title
//
//            // Back button
//            Box(modifier = Modifier.fillMaxSize()) {
//                Row(
//                    modifier = Modifier
//                        .padding(start = 16.dp, top = 55.dp)
//                        .shadow(4.dp, CircleShape)
//                        .align(Alignment.TopStart) // This works because Row is direct child of Box
//                ) {
//                    IconButton(
//                        onClick = { /* ... */ },
//                        modifier = Modifier
//                            .size(55.dp)
//                            .clip(CircleShape)
//                            .background(PrimaryColorFaded)
//                    ) {
//                        Icon(
//                            imageVector = Icons.Filled.ArrowBack,
//                            contentDescription = "Back",
//                            tint = Color.Black
//                        )
//                    }
//                }
//            }
//
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun PreviewAccountDetailsScreen() {
//    AccountDetailsScreen(
//        onBackClick = { /* Handle back */ },
//        onContinueClick = { data -> /* Handle data */ }
//    )
//}