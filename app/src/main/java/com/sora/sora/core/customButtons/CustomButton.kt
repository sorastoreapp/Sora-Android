package com.sora.sora.core.customButtons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sora.sora.ui.theme.PrimaryColor

@Composable
fun CustomButton(
    label: String, // The text that will be displayed on the button
    onClick: () -> Unit, // Action on button click
    required: Boolean = false, // Optional or required button state
    modifier: Modifier = Modifier, // Optional modifier to customize the button's appearance
    containerColor: Color = PrimaryColor, // Default button color
    textColor: Color = Color.White, // Text color (default: White)
    secondaryButton : Boolean = false
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(65.dp),
        colors = if(secondaryButton) ButtonDefaults.buttonColors(
            containerColor = Color(0xFFFDF6F6))

        else  ButtonDefaults.buttonColors(containerColor = containerColor),
        shape = MaterialTheme.shapes.medium
    ) {
        // Button Text
        Text(
            text = if (required) "$label" else label, // Append '*' for required fields
            color = if (secondaryButton) Color(0xFFDB5A5A) else textColor,
            fontSize = 16.sp
        )
    }
}
