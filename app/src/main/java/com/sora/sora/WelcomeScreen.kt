
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sora.sora.R
import com.sora.sora.core.customButtons.PrimaryButton
import com.sora.sora.core.customText.CustomMontserratText
import com.sora.sora.ui.theme.PrimaryColor
import com.sora.sora.ui.theme.PrimaryColor100
import com.sora.sora.ui.theme.SecondaryColor


@Composable
fun WelcomeScreen(
    onLoginClick: () -> Unit,
    onSignupClick: () -> Unit,
    onSkipClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.welcome_screen), // your PNG
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )

        // Foreground content (Text + Buttons)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 22.dp, vertical = 60.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Login Button
            PrimaryButton(
                text = "Sign in",
                onClick = onLoginClick,
                backgroundColor = PrimaryColor,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Sign Up Button
            PrimaryButton(
                text = "Sign Up",
                textColor = PrimaryColor,
                onClick = onSignupClick,
                backgroundColor = PrimaryColor100,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))
            // Skip Text Button
            Box(
                modifier = Modifier
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { onSkipClick() }
            ) {
                CustomMontserratText(
                    "Continue as a Guest",
                    color = PrimaryColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W700
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWelcomeScreen() {
    WelcomeScreen(
        onLoginClick = {},
        onSignupClick = {},
        onSkipClick = {}
    )
}

/***/
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.tooling.preview.PreviewParameter
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.sora.sora.R
//import com.sora.sora.ui.theme.PrimaryColor
//import com.sora.sora.ui.theme.SecondaryColor
//import kotlinx.serialization.Serializable
//
//
//
//@Composable
//fun PreviewWelcomeScreen() {
//    WelcomeScreen(
//        onLoginClick = {},
//        onSignupClick = {},
//        onSkipClick = {}
//    )
//}
//
//@Composable
//fun WelcomeScreen(
//    onLoginClick: () -> Unit,
//    onSignupClick: () -> Unit,
//    onSkipClick: () -> Unit
//) {
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//    ) {
//        // Background Image
//        Image(
//            painter = painterResource(id = R.drawable.welcome_screen), // your PNG
//            contentDescription = null,
//            modifier = Modifier.fillMaxSize()
//        )
//
//        // Foreground content (Text + Buttons)
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(horizontal = 22.dp, vertical = 60.dp),
//            verticalArrangement = Arrangement.Bottom,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            // Login Button
//            Button(
//                onClick = onLoginClick,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(57.dp),
//                colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
//                shape = MaterialTheme.shapes.medium
//            ) {
//                Text(text = "Login", color = Color.White, fontSize = 16.sp)
//            }
//            Spacer(modifier = Modifier.height(16.dp))
//
//            // Sign Up Button
//            Button(
//                onClick = onSignupClick,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(57.dp),
//                colors = ButtonDefaults.buttonColors(containerColor = SecondaryColor),
//                shape = MaterialTheme.shapes.medium
//            ) {
//                Text(text = "Sign Up", color = Color.White, fontSize = 16.sp)
//            }
//
//            // Skip
//            TextButton(onClick = onSkipClick) {
//                Text("Skip", color = Color.Black, fontSize = 14.sp)
//            }
//        }
//    }
//}
