import android.provider.CalendarContract.Colors
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
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
import com.sora.sora.ui.theme.AppSubTextColor
import com.sora.sora.ui.theme.DividerGray
import com.sora.sora.ui.theme.PrimaryColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewPasswordScreen(
) {
    var passwordController by remember { mutableStateOf("") }
    var confirmPassController by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .background(color = Color.White)
        ) {
            CustomAppBar(
                title = "New Password",
                onBackClick = {
                    navController.popBackStack()
                }
            )

            Column ( modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = hFactor(20),)){
                Spacer(modifier = Modifier.height(vFactor(20)))

                AppTextFieldWithSuffix(
                    value = passwordController,
                    onValueChange = { passwordController = it },
                    placeholder = "Your new password",
                    isPassword = true,  // Set to true for password field
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(vFactor(20)))

                // Password field

                AppTextFieldWithSuffix(
                    value = confirmPassController,
                    onValueChange = { confirmPassController = it },
                    placeholder = "Confirm Password",
                    isPassword = true,  // Set to true for password field
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(vFactor(20)))

                // Login Button
                PrimaryButton(
                    text = "Change Password",
                    backgroundColor = PrimaryColor,
                    onClick = {
                        NavigationManager.navigateAndClearStack(Dest.SignIn::class.toRoute())
                    },
                )

                Spacer(modifier = Modifier.height(vFactor(15))) }

        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewNewPasswordScreen() {
    SignInScreen(
        onLoginClick = {},
        onRegisterClick = {},
        onSocialLoginClick = {},
        onCountryCodeChange = {}
    )
}