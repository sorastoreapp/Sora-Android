package com.sora.sora.features.profile.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sora.sora.core.customButtons.CustomButton
import com.sora.sora.core.customText.CustomMontserratText
import com.sora.sora.core.hFactor
import com.sora.sora.core.vFactor
import com.sora.sora.ui.theme.AppTextGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteAccountBottomSheet(
    orderId: String,
    onDismiss: () -> Unit,
    onCancelOrder: (String) -> Unit
) {
    ModalBottomSheet(

        onDismissRequest = onDismiss,

        containerColor = Color.White,
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
        ) {

            // Confirmation Message -
            CustomMontserratText(
                text = "Delete Your Account",
                fontSize = 18.sp,
                color = Color.Black,
                fontWeight = FontWeight(600),
                modifier = Modifier
                    .padding(horizontal = 16.dp,)
                    .padding( bottom = 16.dp ,)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(vFactor(16)))

            // Additional message
            CustomMontserratText(
                text = "Deleting your account will erase all your data, including orders and preferences.",
                fontSize = 14.sp,
                color = AppTextGray,
                modifier = Modifier
                    .padding(horizontal = 8.dp,)

                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(vFactor(24)))
            CustomButton(
                label = "Cancel",
                onClick = onDismiss,

                modifier = Modifier.padding(bottom = 8.dp)
            )

            Spacer(modifier = Modifier.width(hFactor(16)))

            CustomButton(
                label = "Delete account",
                onClick = {
                    // Show the bottom sheet when the Cancel Order button is clicked
                    onCancelOrder(orderId)

                },

                containerColor = Color(0xFFDB5A5A).copy(alpha = 0.05f),
                textColor = Color(0xFFDB5A5A),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            // Button Row with "Yes, cancel my order" and "Back" options

            Spacer(modifier = Modifier.height(vFactor(16)))
        }
    }
}
