package com.sora.sora.core

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sora.sora.R
import com.sora.sora.core.customText.CustomMontserratText
import com.sora.sora.core.navigations.NavigationManager.navController

@Composable
fun CustomTopBar(
    title: String? = null, // Optional title, defaults to null (no title)
    space: Dp = 40.dp, // Optional title, defaults to null (no title)
    modifier: Modifier = Modifier, // Nullable modifier for the Row
    onBackClick: (() -> Unit)? = null // Optional callback for the back button
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth() // Ensures Row takes full width
    ) {
        // Back button on the left
        Image(
            painter = painterResource(id = R.drawable.img_back_circular),
            contentDescription = "Back",
            modifier = Modifier
                .size(40.dp)
                .clickable { if(onBackClick != null) onBackClick?.invoke() else{
                    navController.popBackStack()  //
                }  } // If onBackClick is provided, it will be invoked
        )

        // Spacer for the middle portion
        Spacer(modifier = Modifier.width(space)) // Space between back button and title

        // Center the title
        title?.let {
            CustomMontserratText(
                text = it,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.weight(1f) // Ensures title takes remaining space and is centered
            )
        }
    }
}

/**Working but algnment is not good */
//@Composable
//fun   CustomTopBar(
//    title: String? = null, // Optional title, defaults to null (no title)
//    modifier: Modifier = Modifier, // Nullable modifier for the Row
//    onBackClick: (() -> Unit)? = null // Optional callback for the back button
//) {
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        modifier = modifier.fillMaxWidth() // Ensures Row takes full width
//    ) {
//        Image(
//            painter = painterResource(id = R.drawable.img_back_circular),
//            contentDescription = "Back",
//            modifier = Modifier
//                .size(45.dp)
//                .clickable { onBackClick?.invoke() } // If onBackClick is provided, it will be invoked
//        )
//        Spacer(modifier = Modifier.width(16.dp)) // Space between back button and title
//
//        // Conditionally display title
//        title?.let {
//            Text(
//                text = it,
//                fontSize = 24.sp,
//                fontWeight = FontWeight.SemiBold,
//                color = Color.Black,
//                modifier = Modifier.weight(1f) // Ensures title takes remaining space
//            )
//        }
//    }
//}


/** Partically Working as alginment not fixed and no parameter */
//@Composable
//fun  CustomTopBar(modifier: Modifier = Modifier) {
//    // Back Button Section
//    Row(
//        verticalAlignment = Alignment.CenterVertically, // Align items vertically in the center
//        modifier = modifier.fillMaxWidth() // Ensures that Row takes the full width
//    ) {
//        Image(
//            painter = painterResource(id = R.drawable.img_back_circular),
//            contentDescription = "Back",
//            modifier = Modifier
//                .size(45.dp)
//                .clickable { /* Handle back button click */ }
//        )
//        Spacer(modifier = Modifier.width(48.dp))
//        Text(
//            text = "Add New Address",
//            fontSize = 24.sp,
//            fontWeight = FontWeight.SemiBold,
//            color = Color.Black
//        )
//    }
//
//}