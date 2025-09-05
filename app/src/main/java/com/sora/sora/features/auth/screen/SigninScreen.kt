import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sora.sora.R
import com.sora.sora.core.CustomAppBar
import com.sora.sora.core.customButtons.PrimaryButton
import com.sora.sora.core.customText.CustomMontserratText
import com.sora.sora.core.hFactor
import com.sora.sora.core.navigations.Dest
import com.sora.sora.core.navigations.NavigationManager
import com.sora.sora.core.navigations.NavigationManager.navController
import com.sora.sora.core.navigations.toRoute
import com.sora.sora.core.vFactor
import com.sora.sora.ui.components.AppTextFieldWithSuffix
import com.sora.sora.ui.theme.DividerGray
import com.sora.sora.ui.theme.PrimaryColor
import com.sora.sora.ui.theme.TextFieldBackgroundColors
import com.sora.sora.ui.theme.TextFieldBorderColors
import com.sora.sora.ui.theme.TextHintColor


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
    var passwordController by remember { mutableStateOf("") }
    var emailController by remember { mutableStateOf("") }

    // State to handle password visibility toggle
    var passwordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        // Background PNG with embedded text, illustrations etc.
        Image(
            painter = painterResource(id = R.drawable.signin_background), // Your imported png image
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )

        CustomAppBar(
            title = "Sign in",
            onBackClick = {
                navController.popBackStack()
            }
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(horizontal = hFactor(20), vertical = vFactor(174))
        ) {

            CustomMontserratText(
                text = "Login by your Email",
                fontSize = 16.sp,
                fontWeight = FontWeight.W500,
                color = PrimaryColor
            )

            // i want spacer vertical below
            Spacer(modifier = Modifier.height(vFactor(20)))

            AppTextFieldWithSuffix(
                value = emailController,
                onValueChange = { emailController = it },
                placeholder = "Faisalajmi95@gmail.com",

                suffix = {
                    Icon(painter = painterResource(id = R.drawable.ic_email), contentDescription = "Some Icon", tint = PrimaryColor)
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(vFactor(20)))

            // Password field

            AppTextFieldWithSuffix(
                value = passwordController,
                onValueChange = { passwordController = it },
                placeholder = "Password",
                isPassword = true,  // Set to true for password field
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(vFactor(10)))

            CustomMontserratText(
                text = "Forget Password?",
                fontSize = 14.sp,
                fontWeight = FontWeight.W600,
                color = PrimaryColor,
                modifier = Modifier.padding(start = hFactor(10)).pointerInput(Unit) {
                    detectTapGestures(
                        onTap = { navController.navigate(Dest.ForgetPasswordScreen::class.toRoute()) },
                    )
                }
            )

            Spacer(modifier = Modifier.height(vFactor(20)))

            // Login Button
            PrimaryButton(
                text = "Sign in",
                backgroundColor = PrimaryColor,
                onClick = {
                    NavigationManager.navigateAndClearStack(Dest.DashBoardScreen::class.toRoute())
                },
            )

            Spacer(modifier = Modifier.height(vFactor(20)))

            // OR separator
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = hFactor(48))
            ) {
                Divider(
                    color = DividerGray,
                    thickness = 1.dp,
                    modifier = Modifier.weight(1f)
                )
                CustomMontserratText(
                    " OR ",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Divider(
                    color = DividerGray,
                    thickness = 1.dp,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(vFactor(20)))

            // Social login buttons
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                SocialLoginButton(R.drawable.ic_google) { onSocialLoginClick("Google") }
//                Spacer(modifier = Modifier.width(10.dp))
//                SocialLoginButton(R.drawable.ic_facebook) { onSocialLoginClick("Facebook") }
                Spacer(modifier = Modifier.width(10.dp))
               /// SocialLoginButton(R.drawable.ic_apple) { onSocialLoginClick("Apple") }
            }

            Spacer(modifier = Modifier.height(vFactor(20)))

            // Register prompt
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Top,
            ) {
                CustomMontserratText("Don't have account? ", fontSize = 14.sp, color = Color.Black)
                CustomMontserratText(
                    text = "Register Now",
                    color = PrimaryColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
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
        modifier = Modifier
            .height(45.dp)
            .width(65.dp),
        shape = MaterialTheme.shapes.medium,
        contentPadding = PaddingValues(0.dp),
        border = BorderStroke(0.5.dp, Color(0xFFD9D9D9)),
        colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White)
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier.size(20.dp)
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
