
package com.sora.sora.features.profile.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sora.sora.R
import com.sora.sora.core.CustomTopBar2
import com.sora.sora.core.navigations.Dest
import com.sora.sora.core.navigations.NavigationManager.navController
import com.sora.sora.core.navigations.toRoute
import com.sora.sora.ui.theme.PrimaryColor
import com.sora.sora.ui.theme.PrimaryColorFaded
import com.sora.sora.ui.theme.TextFieldColor2

// Data class for Address
data class Address(
    val name: String,
    val address: String,
    val phone: String
)

// Main screen for displaying My Addresses
@Composable
fun MyAddressesScreen(

) {
    val addresses = listOf(
        Address(name = "Leslie Alexander", address = "1234 Palm Street, Apartment 5B, Downtown Kuwait, 13001", phone = "+965 9876 5432"),
        Address(name = "Michael Thompson", address = "4321 Ocean Drive, Floor 3, Hawally, Kuwait, 15002", phone = "+965 9765 4321"),
        Address(name = "Sophia Martinez", address = "7890 Pearl Tower, Apartment 21A, Fahaheel, Kuwait, 30010", phone = "+965 9543 6789")
    )

    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(Color.White)  // Set background color to white
                .statusBarsPadding()
        ) {
            // Back Button Section
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                IconButton(onClick = { /* Handle back click */ }) {
//                    Icon(
//                        imageVector = Icons.Filled.ArrowBack,
//                        contentDescription = "Back",
//                        modifier = Modifier
//                            .background(Color.Gray.copy(alpha = 0.2f), shape = CircleShape)
//                            .padding(10.dp)
//                    )
//                }
//                Spacer(modifier = Modifier.width(48.dp))
//                Text(
//                    text = "My Addresses",
//                    fontSize = 24.sp,
//                    fontWeight = FontWeight.SemiBold,
//                    color = Color.Black
//                )
//            }

            CustomTopBar2(title = "My Addresses")
            Spacer(modifier = Modifier.height(16.dp))

            Spacer(modifier = Modifier.height(16.dp))

            // Address List
            addresses.forEach { address ->
                AddressCard(address = address)
                Spacer(modifier = Modifier.height(12.dp))
            }

            AddNewAddressButton()

        }
    }
}


// Composable to display each Address Card
@Composable
fun AddressCard(address: Address) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.width(2.dp)) // Push icons to the right
                Text(
                    text = address.name,
                    fontSize = 16.sp,
                    color = Color.Black,
                    maxLines = 2,
                    fontWeight = FontWeight.Bold,
//                    fontStyle = FontStyle.Italic,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.weight(1f)) // Push icons to the right

                // Edit Icon (Green)
                IconButton(
                    onClick = { Log.d("ProfileScreen", "Edit profile clicked") }
                ) {
                    Box(
                        modifier = Modifier
                            .size(30.dp) // Set the size of the icon container
                            .background(
                                PrimaryColorFaded,
                                shape = CircleShape
                            ) // Set the filled primary color and circular shape
                            .padding(4.dp) // Padding inside the circle to ensure the icon has space around it
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_edit_bar),
                            contentDescription = "Edit Profile",
                            modifier = Modifier
                                .fillMaxSize() // Make the image fill the size of the container
                        )
                    }
                }

            }


            // Address Details with Location Icon
            Row(    horizontalArrangement = Arrangement.Start // Align all children to the start
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_location_blue),
                    contentDescription = "Location Icon",
                    modifier = Modifier.size(22.dp),
                    tint = PrimaryColor // Set the tint color to PrimaryColor
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = address.address,
                    fontSize = 14.sp,
                    color = Color.Black,
                    maxLines = 2,
//                    fontStyle = FontStyle.Italic,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Phone Number with Phone Icon and Edit/Delete Icons
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_call_green),
                    contentDescription = "Phone Icon",
                    modifier = Modifier.size(24.dp),
                    tint = PrimaryColor // Set the tint color to PrimaryColor
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = address.phone,
                    fontSize = 14.sp,
                    color = Color.Black,
                    maxLines = 2,
//                    fontStyle = FontStyle.Italic,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}


@Composable
fun AddNewAddressButton() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .padding(4.dp)
            .background(TextFieldColor2, shape = RoundedCornerShape(20.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 1.dp)
                .clickable {
                navController.navigate(Dest.AddNewAddressScreen::class.toRoute()) {
                    popUpTo(Dest.OrdersScreen::class.toRoute()) { inclusive = true }
                }
            },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center // Aligning content to the start
        ) {
            // Add Icon
            Icon(
                imageVector = Icons.Default.Add, // You can replace with your own icon
                contentDescription = "Add New Address Icon",
                modifier = Modifier.size(24.dp),
                tint = PrimaryColor
            )

            Spacer(modifier = Modifier.width(8.dp)) // Spacer between icon and text

            // Add new address text
            Text(
                text = "Add new address",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryColor,
            )
        }
    }
}



// Composable to display the "Add New Address" button
//@Composable
//fun AddNewAddressButton() {
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 12.dp)
//            .background(Color.White, shape = RoundedCornerShape(20.dp))
//    ) {
//        DottedBorder(modifier = Modifier.fillMaxSize(), color = PrimaryColor) // Applying dotted border
//
//        Row(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(vertical = 16.dp),
//            horizontalArrangement = Arrangement.Center,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            // Add Icon (Location Pin)
//            Image(
//                painter = painterResource(id = R.drawable.ic_location_add),
//                contentDescription = "Add New Address Icon",
//                modifier = Modifier.size(24.dp)
//            )
//            Spacer(modifier = Modifier.width(8.dp)) // Space between icon and text
//            // Add New Address Text
//            Text(
//                text = "Add new address",
//                fontSize = 16.sp,
//                fontWeight = FontWeight.Bold,
//                color = PrimaryColor
//            )
//        }
//    }
//
//}
//@Composable
//fun DottedBorder(modifier: Modifier = Modifier, color: Color) {
//    Canvas(modifier = modifier) {
//        val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
//        drawRoundRect(
//            color = color,
//            size = size,
//            style = Stroke(width = 2f, pathEffect = pathEffect),
////            cornerRadius = Size(20f, 20f) // Rounded corners
//        )
//    }
//}




/**WORKING CODE WITHOUT ADD NEW ADDRESS WIDGET*/
//package com.sora.sora.features.profile.screen
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.*
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ArrowBack
//import androidx.compose.material.icons.filled.Edit
//import androidx.compose.material.icons.filled.Delete
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.FontStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextOverflow
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.sora.sora.R
//import com.sora.sora.ui.theme.PrimaryColor
//import com.sora.sora.ui.theme.PrimaryColorFaded
//
//data class Address(
//    val name: String,
//    val address: String,
//    val phone: String
//)
//
//@Composable
//fun MyAddressesScreen() {
//    val addresses = listOf(
//        Address(name = "Leslie Alexander", address = "1234 Palm Street, Apartment 5B, Downtown Kuwait, 13001", phone = "+965 9876 5432"),
//        Address(name = "Michael Thompson", address = "4321 Ocean Drive, Floor 3, Hawally, Kuwait, 15002", phone = "+965 9765 4321"),
//        Address(name = "Sophia Martinez", address = "7890 Pearl Tower, Apartment 21A, Fahaheel, Kuwait, 30010", phone = "+965 9543 6789")
//    )
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//            .statusBarsPadding()
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
//            Row {
//
//                Text(
//                    text = "My Addresses",
//                    fontSize = 24.sp,
//                    fontWeight = FontWeight.SemiBold,
//                    color = Color.Black
//                )
//            }
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Add New Address Button
//        AddNewAddressButton()
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Address List
//        addresses.forEach { address ->
//            AddressCard(address = address)
//            Spacer(modifier = Modifier.height(12.dp))
//        }
//    }
//}
//
//@Composable
//fun AddNewAddressButton() {
//    Box(
//
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(100.dp)
//            .padding(horizontal = 12.dp)
//            .background(PrimaryColorFaded, shape = RoundedCornerShape(20.dp)),
//    ) {
//        Image(
//            painter = painterResource(id = R.drawable.ic_location_add), // Phone Icon
//            contentDescription = "Phone Icon",
//            modifier = Modifier.size(24.dp) // Increased icon size here
//        )
//
//        Text(
//            text = "Add new address",
//            fontSize = 16.sp,
//            fontWeight = FontWeight.Bold,
//            color = PrimaryColor
//        )
//    }
//}
//
//@Composable
//fun AddressCard(address: Address) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(150.dp),
//        shape = RoundedCornerShape(15.dp),
//        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
//        colors = CardDefaults.cardColors(containerColor = Color.White)
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp)
//        ) {
//            // Address Name
//            Text(
//                text = address.name,
//                fontWeight = FontWeight.Bold,
//                fontStyle = FontStyle.Italic, // Italicized style for name
//                fontSize = 16.sp,
//                color = Color.Black
//            )
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            // Address Details with Location Icon
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                Image(
//                    painter = painterResource(id = R.drawable.ic_location_blue), // Location Icon
//                    contentDescription = "Location Icon",
//                    modifier = Modifier.size(24.dp) // Increased icon size here
//                )
//                Spacer(modifier = Modifier.width(8.dp))
//                Text(
//                    text = address.address,
//                    fontSize = 14.sp,
//                    color = Color.Black,
//                    maxLines = 2,
//                    fontStyle = FontStyle.Italic, // Italicized style for address
//                    overflow = TextOverflow.Ellipsis
//                )
//            }
//
//            Spacer(modifier = Modifier.height(4.dp))
//
//            // Phone Number with Phone Icon and Edit/Delete Icons
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                Image(
//                    painter = painterResource(id = R.drawable.ic_call_green), // Phone Icon
//                    contentDescription = "Phone Icon",
//                    modifier = Modifier.size(24.dp) // Increased icon size here
//                )
//                Spacer(modifier = Modifier.width(8.dp))
//                Text(
//                    text = address.phone,
//                    fontSize = 14.sp,
//                    color = Color.Black,
//                    maxLines = 2,
//                    fontStyle = FontStyle.Italic, // Italicized style for phone number
//                    overflow = TextOverflow.Ellipsis
//                )
//
//                Spacer(modifier = Modifier.weight(1f)) // Push icons to the right
//
//                // Edit Icon (Green)
//                IconButton(
//                    onClick = { /* Handle Edit Click */ },
//                    modifier = Modifier.size(40.dp) // Increased size of the IconButton
//                ) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.ic_edit_green),
//                        contentDescription = "Edit Icon",
//                        tint = Color.Green,
//                        modifier = Modifier.size(30.dp)  // Increased size of the Icon itself
//                    )
//                }
//
//                // Delete Icon (Red)
//                IconButton(
//                    onClick = { /* Handle Delete Click */ },
//                    modifier = Modifier.size(40.dp) // Increased size of the IconButton
//                ) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.ic_delete_bucket),
//                        contentDescription = "Delete Icon",
//                        tint = Color.Red,
//                        modifier = Modifier.size(30.dp)  // Increased size of the Icon itself
//                    )
//                }
//            }
//        }
//    }
//}


