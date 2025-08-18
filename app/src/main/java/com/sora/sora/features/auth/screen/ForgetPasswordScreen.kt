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
import com.sora.sora.core.navigations.NavigationManager.navController
import com.sora.sora.core.navigations.toRoute
import com.sora.sora.core.vFactor
import com.sora.sora.ui.components.AppTextFieldWithSuffix
import com.sora.sora.ui.theme.AppSubTextColor
import com.sora.sora.ui.theme.DividerGray
import com.sora.sora.ui.theme.PrimaryColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgetPasswordScreen(
) {
    // Example country codes - you can replace with full list or a library
    val countryCodes = listOf("+49", "+1", "+44")
    var passwordController by remember { mutableStateOf("") }
    var emailController by remember { mutableStateOf("") }
    var nameController by remember { mutableStateOf("") }

    // State to handle password visibility toggle
    var passwordVisible by remember { mutableStateOf(false) }

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
                title = "Forget Password",
                onBackClick = {
                    navController.popBackStack()
                }
            )

          Column(  modifier = Modifier
              .fillMaxWidth()
              .background(color = Color.White)
              .padding(horizontal = hFactor(20),)

          ){
              Spacer(modifier = Modifier.height(vFactor(20)))

              AppTextFieldWithSuffix(
                  value = emailController,
                  onValueChange = { emailController = it },
                  placeholder = "Email",
                  suffix = {
                      Icon(painter = painterResource(id = R.drawable.ic_email), contentDescription = "Some Icon")
                  },
                  modifier = Modifier.fillMaxWidth()
              )

              Spacer(modifier = Modifier.height(vFactor(20)))


              val annotatedString = buildAnnotatedString {
                  append("We will send an OTP to your email to reset your password ")
                  pushStyle(SpanStyle(textDecoration = TextDecoration.Underline))
              }

              CustomMontserratText(
                  text = "We will send an OTP to your email to reset your password",
                  fontSize = 14.sp, color = AppSubTextColor
              )


              Spacer(modifier = Modifier.height(vFactor(20)))

              // Login Button
              PrimaryButton(
                  text = "Send OTP",
                  backgroundColor = PrimaryColor,
                  onClick = {
                      navController.navigate(Dest.OtpScreen::class.toRoute())
                  },
              )

              Spacer(modifier = Modifier.height(vFactor(15)))
          }

        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewForgetPasswordScreen() {
    SignInScreen(
        onLoginClick = {},
        onRegisterClick = {},
        onSocialLoginClick = {},
        onCountryCodeChange = {}
    )
}