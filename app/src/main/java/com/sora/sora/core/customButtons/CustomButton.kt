package com.sora.sora.core.customButtons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sora.sora.core.customText.CustomMontserratText
import com.sora.sora.ui.theme.PrimaryColor
import com.sora.sora.ui.theme.TextFieldColor3

//@Composable
//fun CustomButton(
//    label: String, // The text that will be displayed on the button
//    onClick: () -> Unit, // Action on button click
//    required: Boolean = false, // Optional or required button state
//    modifier: Modifier = Modifier, // Optional modifier to customize the button's appearance
//    containerColor: Color = PrimaryColor, // Default button color
//    textColor: Color = Color.White, // Text color (default: White)
//    secondaryButton : Boolean = false,
//) {
//    Button(
//        onClick = onClick,
//        modifier = modifier
//            .fillMaxWidth()
//            .height(65.dp),
//        colors = if(secondaryButton) ButtonDefaults.buttonColors(
////            containerColor = Color(0xFFECD6B0)
//            containerColor = TextFieldColor3
//        )
//
//        else  ButtonDefaults.buttonColors(containerColor = containerColor),
//        shape = MaterialTheme.shapes.medium
//    ) {
//        // Button Text
//        Text(
//            text = if (required) label else label, // Append '*' for required fields
//            color = if (secondaryButton) Color(0xFFDB5A5A) else textColor,
//            fontSize = 16.sp,
//            fontWeight = FontWeight.Bold
//        )
//    }
//}
@Composable
fun CustomButton(
    label: String, // The text that will be displayed on the button
    onClick: () -> Unit, // Action on button click
    required: Boolean = false, // Optional or required button state
    modifier: Modifier = Modifier, // Optional modifier to customize the button's appearance
    containerColor: Color = PrimaryColor, // Default button color
    textColor: Color = Color.White, // Text color (default: White)

    secondaryButton: Boolean = false, // Whether this is a secondary button
    icon: Int? = null // Optional icon resource (null if no icon)

) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(65.dp),
        colors = if (secondaryButton) ButtonDefaults.buttonColors(
            containerColor = TextFieldColor3
        ) else ButtonDefaults.buttonColors(containerColor = containerColor),
        shape = RoundedCornerShape(20.dp)
    ) {
        // Row to hold icon and text (if an icon is provided)
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            // If icon is provided, add it
            if (icon != null) {
                Image(
                    painter = painterResource(id = icon),

                    contentDescription = "",
                    modifier = Modifier.size(20.dp)


                )
                Spacer(modifier = Modifier.width(10.dp)) // Spacer between icon and text
            }

            // Button Text
            Text(
                text = if (required) "$label *" else label, // Append '*' for required fields
                color = if (secondaryButton) Color(0xFFDB5A5A) else textColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}




@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    backgroundColor: Color = PrimaryColor, // Default value for the background color
    textColor: Color = Color.White, // Default value for the text color
    height: Int = 57, // Default height
    modifier: Modifier = Modifier, // Default empty modifier
    borderColor: Color? = null, // Optional border color
//    shape: androidx.compose.foundation.shape.CornerBasedShape = MaterialTheme.shapes.large // Default shape
    shape: RoundedCornerShape = RoundedCornerShape(20.dp),
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(height.dp)
            .then(if (borderColor != null) Modifier.border(2.dp, borderColor, shape) else Modifier), // Apply border if given
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        shape = shape
    ) {
        CustomMontserratText(
            text = text, color = textColor, fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}
