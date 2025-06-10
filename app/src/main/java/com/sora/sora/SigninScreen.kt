import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sora.sora.R
import com.sora.sora.core.navigations.NavigationManager.navController
import com.sora.sora.ui.theme.PrimaryColor
import com.sora.sora.ui.theme.PrimaryColorFaded


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    onLoginClick: (String) -> Unit,
    onRegisterClick: () -> Unit,
    onSocialLoginClick: (String) -> Unit,
    onCountryCodeChange: (String) -> Unit
) {
    // Example country codes - you can replace with full list or a library
    val countryCodes = listOf("+49", "+1", "+44")
    var expanded by remember { mutableStateOf(false) }
    var selectedCountryCode by remember { mutableStateOf(countryCodes[0]) }
    var phoneNumber by remember { mutableStateOf("") }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        // Background PNG with embedded text, illustrations etc.
        Image(
            painter = painterResource(id = R.drawable.signin_background), // Your imported png image
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )

        // Foreground content

        // Back button
        Image(
            painter = painterResource(id = R.drawable.img_back_circular),
            contentDescription = "Back",
            modifier = Modifier
                .padding(start = 16.dp, top = 55.dp)
                .size(45.dp)
//                .background(PrimaryColorFaded) // your primary color
                .align(Alignment.TopStart)
                .pointerInput(Unit) {
                    detectTapGestures {
                        navController.popBackStack()
                    }
                }
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(horizontal = 24.dp, vertical = 270.dp)
        ) {
            // Spacer for top content, you can adjust padding as per design

            // Phone input row with country code selector
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
                            .clickable { expanded = true }
                            .padding(horizontal = 8.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(selectedCountryCode, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Select Country Code"
                        )
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        countryCodes.forEach { code ->
                            DropdownMenuItem(
                                text = { Text(code) },
                                onClick = {
                                    selectedCountryCode = code
                                    expanded = false
                                    onCountryCodeChange(code)
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.width(4.dp))
                // Vertical divider line between country code and phone number
                Box(
                    modifier = Modifier
                        .width(1.dp)
                        .height(32.dp)   // slightly smaller than row height for nice padding
                        .background(Color.Gray)
                )
                Spacer(modifier = Modifier.width(4.dp))
                // Phone number input field
                TextField(
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it },
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

            Spacer(modifier = Modifier.height(24.dp))

            // Login button
            Button(
                onClick = { onLoginClick(selectedCountryCode + phoneNumber) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7B3F00))
            ) {
                Text("Login", color = Color.White, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // OR separator
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Divider(
                    color = Color.Gray,
                    thickness = 1.dp,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    " OR ",
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Divider(
                    color = Color.Gray,
                    thickness = 1.dp,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Social login buttons
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                SocialLoginButton(R.drawable.ic_google) { onSocialLoginClick("Google") }
                Spacer(modifier = Modifier.width(10.dp))
                SocialLoginButton(R.drawable.ic_facebook) { onSocialLoginClick("Facebook") }
                Spacer(modifier = Modifier.width(10.dp))
                SocialLoginButton(R.drawable.ic_apple) { onSocialLoginClick("Apple") }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Register prompt
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text("Don't have account? ")
                Text(
                    text = "Register Now",
                    color = Color(0xFF7B3F00),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable { onRegisterClick() }
                )
            }
        }
    }
}

@Composable
fun SocialLoginButton(@DrawableRes iconRes: Int, onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier.height(50.dp).width(70.dp),
        shape = MaterialTheme.shapes.medium,
        contentPadding = PaddingValues(0.dp),
        border = BorderStroke(1.dp, Color(0xFFD9D9D9)),
        colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White)
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewSignInScreen() {
    SignInScreen(
        onLoginClick = {},
        onRegisterClick = {},
        onSocialLoginClick = {},
        onCountryCodeChange = {}
    )
}
