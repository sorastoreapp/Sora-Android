package com.sora.sora.features.profile.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sora.sora.core.customButtons.CustomButton
import com.sora.sora.core.customText.CustomMontserratText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteAccountBottomSheet(
    orderId: String,
    onDismiss: () -> Unit,
    onCancelOrder: (String) -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {

            // Confirmation Message -
            CustomMontserratText(
                text = "Delete Your Account",
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(horizontal = 16.dp,)
                    .padding( bottom = 16.dp ,)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            // Additional message
            CustomMontserratText(
                text = "Deleting your account will erase all your data, including orders and preferences.",
                fontSize = 13.sp,
                color = Color.Gray,
                modifier = Modifier
                    .padding(horizontal = 15.dp,)
                    .padding( bottom = 15.dp ,)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            CustomButton(
                label = "Cancel",
                onClick = onDismiss,
                required = true,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))

            CustomButton(
                label = "Delete account",
                onClick = {
                    // Show the bottom sheet when the Cancel Order button is clicked
                    onCancelOrder(orderId)

                },
                required = true,
                containerColor = Color(0xFFDB5A5A).copy(alpha = 0.05f),
                textColor = Color(0xFFDB5A5A),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            // Button Row with "Yes, cancel my order" and "Back" options
            Row(
                modifier = Modifier
                    .fillMaxWidth()
//                    .horizontalArrangement(Arrangement.SpaceEvenly)
            ) {



                Spacer(modifier = Modifier.width(8.dp))

            }
        }
    }
}
