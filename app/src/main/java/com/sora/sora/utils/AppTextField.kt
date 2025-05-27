//package com.sora.sora.ui.components
//
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextFieldDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.input.KeyboardType
//import com.sora.sora.ui.theme.PrimaryColor
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun AppTextField(
//    value: String,
//    onValueChange: (String) -> Unit,
//    label: String,
//    modifier: Modifier = Modifier,
//    placeholder: String? = null,
//    keyboardType: KeyboardType = KeyboardType.Text,
//    singleLine: Boolean = true,
//    trailingIcon: @Composable (() -> Unit)? = null
//) {
//    OutlinedTextField(
//        value = value,
//        onValueChange = onValueChange,
//        modifier = modifier,
//        label = { Text(label) },
//        placeholder = placeholder?.let { { Text(it) } },
//        singleLine = singleLine,
//        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
//        trailingIcon = trailingIcon,
//        colors = TextFieldDefaults.outlinedTextFieldColors(
//            focusedBorderColor   = PrimaryColor,
//            unfocusedBorderColor = Color.LightGray
//        )
//    )
//}


package com.sora.sora.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sora.sora.R
import com.sora.sora.ui.theme.PrimaryColor
import com.sora.sora.ui.theme.TextFieldColor

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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    prefixIcon: Painter? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .height(56.dp)
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
@Preview(showBackground = true)
@Composable
fun PreviewAppTextField() {
    var text: String by remember { mutableStateOf("") }

    AppTextField(
        value = text,
        onValueChange = { text = it },
        placeholder = "Leslie Alexander",
        prefixIcon = painterResource(id = R.drawable.ic_favoritess),  // your preview icon
        modifier   = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}