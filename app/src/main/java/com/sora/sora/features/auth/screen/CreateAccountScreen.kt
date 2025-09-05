import android.provider.CalendarContract.Colors
import android.util.Log
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
import com.sora.sora.ui.theme.AppTextGray
import com.sora.sora.ui.theme.DividerGray
import com.sora.sora.ui.theme.PrimaryColor


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun CreateAccountScreen(
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
            .background(Color.White)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .background(color = Color.White)
        ) {
            CustomAppBar(
                title = "Create account",
                onBackClick = {
                    navController.popBackStack()
                }
            )

         Column ( modifier = Modifier
             .fillMaxWidth()
             .padding(horizontal = hFactor(20),)){
             Spacer(modifier = Modifier.height(vFactor(45)))
             AppTextFieldWithSuffix(
                 value = nameController,
                 onValueChange = { nameController = it },
                 placeholder = "Full Name",
                 suffix = {
                     Icon(painter = painterResource(id = R.drawable.ic_profile2), contentDescription = "Some Icon",tint = PrimaryColor)
                 },
                 modifier = Modifier.fillMaxWidth()
             )

             Spacer(modifier = Modifier.height(vFactor(10)))

             AppTextFieldWithSuffix(
                 value = emailController,
                 onValueChange = { emailController = it },
                 placeholder = "Email",
                 suffix = {
                     Icon(painter = painterResource(id = R.drawable.ic_email), contentDescription = "Some Icon",tint = PrimaryColor)
                 },
                 modifier = Modifier.fillMaxWidth()
             )

             Spacer(modifier = Modifier.height(vFactor(10)))

             // Password field

             AppTextFieldWithSuffix(
                 value = passwordController,
                 onValueChange = { passwordController = it },
                 placeholder = "Password",
                 isPassword = true,  // Set to true for password field
                 modifier = Modifier.fillMaxWidth()
             )

             Spacer(modifier = Modifier.height(vFactor(5)))

//            CustomMontserratText(
//                text = "By clicking continue, you accept the Terms & Conditions and Privacy Policy",
//                fontSize = 14.sp,
//                color = AppSubTextColor,
//                fontWeight = FontWeight.Normal,
//                modifier = Modifier.padding(start = hFactor(10))
//            )

             val annotatedString = buildAnnotatedString {
                 append("By clicking continue, you accept the ")

                 // Add annotation for "Terms & Conditions"
                 pushStringAnnotation(tag = "terms", annotation = "Terms & Conditions")
                 pushStyle(SpanStyle(textDecoration = TextDecoration.Underline, color = AppTextGray, fontWeight = FontWeight.Bold))
                 append("Terms & Conditions")
                 pop()
                 pop()

                 append(" and ")

                 // Add annotation for "Privacy Policy"
                 pushStringAnnotation(tag = "privacy", annotation = "Privacy Policy")
                 pushStyle(SpanStyle(textDecoration = TextDecoration.Underline, color = AppTextGray, fontWeight = FontWeight.Bold))
                 append("Privacy Policy")
                 pop()
                 pop()

                 append(".")
             }

             ClickableText(
                 text = annotatedString,
                 onClick = { offset ->
                     // Check if the clicked position is within the Terms & Conditions range
                     annotatedString.getStringAnnotations(tag = "terms", start = offset, end = offset)
                         .firstOrNull()?.let {
                             // Handle click on Terms & Conditions
                             Log.d("MyTag", "------------------Terms & Conditions clicked")
                             navController.navigate(Dest.TermConditionScreen::class.toRoute())
                         }

                     // Check if the clicked position is within the Privacy Policy range
                     annotatedString.getStringAnnotations(tag = "privacy", start = offset, end = offset)
                         .firstOrNull()?.let {
                             // Handle click on Privacy Policy
                             Log.d("MyTag", "------------------Privacy Policy clicked")
                             navController.navigate(Dest.PrivacyPolicyScreen::class.toRoute())
                         }
                 },
                 style = LocalTextStyle.current.copy(fontSize = 12.sp, color = AppTextGray)
             )


             Spacer(modifier = Modifier.height(vFactor(20)))

             // Login Button
             PrimaryButton(
                 text = "Continue",
                 backgroundColor = PrimaryColor,
                 onClick = {
                     navController.navigate(Dest.ForgetPasswordScreen::class.toRoute())
                 },
             )

             Spacer(modifier = Modifier.height(vFactor(15)))

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
                     color = AppTextGray,
                     fontSize = 12.sp,
                     modifier = Modifier.padding(horizontal = 8.dp)
                 )
                 Divider(
                     color = DividerGray,
                     thickness = 1.dp,
                     modifier = Modifier.weight(1f)
                 )
             }

             Spacer(modifier = Modifier.height(30.dp))

             // Social login buttons
             Row(
                 horizontalArrangement = Arrangement.Center,
                 modifier = Modifier.fillMaxWidth(),
                 verticalAlignment = Alignment.CenterVertically,
             ) {
                 SocialLoginButton(R.drawable.ic_google) {
//                     onSocialLoginClick("Google")
                 }
//                Spacer(modifier = Modifier.width(10.dp))
//                SocialLoginButton(R.drawable.ic_facebook) { onSocialLoginClick("Facebook") }
                 Spacer(modifier = Modifier.width(10.dp))
//                 SocialLoginButton(R.drawable.ic_apple) {
////                     onSocialLoginClick("Apple")
//                 }
             }
         }

        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewCreateAccountScreen() {
    SignInScreen(
        onLoginClick = {},
        onRegisterClick = {},
        onSocialLoginClick = {},
        onCountryCodeChange = {}
    )
}