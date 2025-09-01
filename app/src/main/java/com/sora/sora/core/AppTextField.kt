package com.sora.sora.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sora.sora.R
import com.sora.sora.core.customText.CustomMontserratText
import com.sora.sora.ui.theme.PrimaryColor
import com.sora.sora.ui.theme.PrimaryColor100
import com.sora.sora.ui.theme.SecondaryColor100
import com.sora.sora.ui.theme.TextFieldBackgroundColors
import com.sora.sora.ui.theme.TextFieldBorderColors
import com.sora.sora.ui.theme.TextFieldColor

import com.sora.sora.ui.theme.TextHintColor



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTextFieldWithSuffix(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isPassword: Boolean = false,  // New parameter to determine if the field is for a password
    prefixIcon: Painter? = null,
    suffix: @Composable (() -> Unit)? = null,
    height: Dp? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    modifier: Modifier = Modifier
) {
    // State for password visibility
    var passwordVisible by remember { mutableStateOf(false) }

    // Determine the keyboard type and text obscuration based on `isPassword` and `passwordVisible`//

    val visualTransformation = if (isPassword && !passwordVisible) {
        PasswordVisualTransformation()  // Hide the text
    } else {
        VisualTransformation.None  // Show the text
    }

    // Suffix for password visibility toggle
    val passwordSuffix: @Composable (() -> Unit)? = if (isPassword) {
        {
            Icon(
                painter = painterResource(
                    id = if (passwordVisible) R.drawable.ic_eye_close else R.drawable.ic_eye_open
                ),
                contentDescription = "Password Visibility Toggle",
                tint = PrimaryColor,
                modifier = Modifier
                    .size(20.dp)
                    .clickable {
                        // Toggle password visibility
                        passwordVisible = !passwordVisible
                    }
            )
        }
    } else {
        suffix // Use the passed suffix if it's not a password field
    }

    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .height(height ?: 60.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp)),
        singleLine = true,
        placeholder = {
            CustomMontserratText(
                text = placeholder,
                color = TextHintColor,
                fontSize = 14.sp,
                textAlign = TextAlign.Start,
            )
        },
        leadingIcon = prefixIcon?.let { painter ->
            {
                Icon(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier.size(32.dp),
                    tint = Color.Unspecified,
                )
            }
        },
        trailingIcon = passwordSuffix, // Use the password suffix or provided suffix
        colors = TextFieldDefaults.textFieldColors(
            focusedLabelColor = Color.Black,
            cursorColor =  PrimaryColor,
            containerColor = TextFieldBackgroundColors,  // Set the background color here
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        textStyle = LocalTextStyle.current.copy(
            textAlign = TextAlign.Start
        ),
        visualTransformation = visualTransformation // Apply the visual transformation for password obscuring
    )
}





/**
 * A rectangular, filled text field with a prefix icon and static placeholder.
 * - No floating label: placeholder stays in place
 * - Background uses TextFieldColor
 * - Text color is black; placeholder is gray
 * - No underline/border indicators
 *
 * @param value current text
 * @param onValueChange callback when text changes
 * @param placeholder the gray hint text
 * @param prefixIcon optional painter for a leading icon
 * @param keyboardType keyboard input type
 * @param modifier optional Modifier
 */

@OptIn(ExperimentalMaterial3Api::class) //annotation
@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    prefixIcon: Painter? = null,
    height: Dp? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .height(height ?: 56.dp)
            .background(
                color = TextFieldColor,
                shape = RoundedCornerShape(12.dp)
            ),
        singleLine = true,
        placeholder = {
            Text(
                text = placeholder,
                color = Color.Gray
            )
        },
        leadingIcon = prefixIcon?.let { painter ->
            {
                Icon(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            focusedLabelColor = Color.Black,
            cursorColor = PrimaryColor,
            containerColor = TextFieldColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}

/**TEXT FIELD WITH MONTSERRAT TEXT AND PREFIX ICON*/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTextField2(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    prefixIcon: Painter? = null,
    height: Dp? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .height(height ?:60.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))  // Set the corner radius for clipping
            .background(
                color = TextFieldBackgroundColors,  // Background color
                shape = RoundedCornerShape(15.dp)  // Same shape for background
            )
            .border(
                width = 1.dp,
                color = if (value.isNotEmpty()) TextFieldBorderColors else Color.Transparent,  // Set border color based on focus
                shape = RoundedCornerShape(15.dp)  // Same shape for border
            ),
        singleLine = false, // Ensure it allows multi-line text
        maxLines = 5, // Set the max lines as needed
        placeholder = {
            CustomMontserratText(  // Use the CustomMontserratText for placeholder
                text = placeholder,
                color = TextHintColor,
                fontWeight = FontWeight(500),
                fontSize = 14.sp,
                textAlign = TextAlign.Start, // Explicitly set placeholder alignment
            )
        },
        leadingIcon = prefixIcon?.let { painter ->
            {
                Icon(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier.size(32.dp),
                    tint = Color.Unspecified, // Ensure the icon is not tinted gray
                )
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            focusedLabelColor = Color.Black,
            cursorColor = PrimaryColor,
            containerColor = TextFieldBackgroundColors,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        textStyle = LocalTextStyle.current.copy(
            textAlign = TextAlign.Start // Ensures the input text is left-aligned
        )
    )
}



/**TEXT FIELD WITH MONTSERRAT TEXT AND PREFIX ANY*/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTextField3(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    prefix: @Composable (() -> Unit)? = null,  // Accepts any composable as prefix
    height: Dp? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .height(height ?: 56.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))  // Set the corner radius for clipping
            .background(
                color = Color(0xFFB583530D),  // Background color
                shape = RoundedCornerShape(24.dp)  // Same shape for background
            )
            .border(
                width = 1.dp,
                color = if (value.isNotEmpty()) PrimaryColor else Color.Transparent,  // Set border color based on focus
                shape = RoundedCornerShape(24.dp)  // Same shape for border
            ),
        singleLine = true,
        placeholder = {
            CustomMontserratText(  // Use the CustomMontserratText for placeholder
                text = placeholder,
                color = Color.Gray,
                fontSize = 14.sp
            )
        },
        leadingIcon = {
            prefix?.let {
                it()
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            focusedLabelColor = Color.Black,
            cursorColor = PrimaryColor,
            containerColor = TextFieldColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}



@Preview(showBackground = true)
@Composable
fun UseAppTextFieldWithSuffix() {
    var text by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        AppTextFieldWithSuffix(
            value = text,
            onValueChange = { text = it },
            placeholder = "Email",
            suffix = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_email),
                    contentDescription = "Email",
                )
            },
            keyboardType = KeyboardType.Email,
            modifier = Modifier.fillMaxWidth()
        )
    }
}