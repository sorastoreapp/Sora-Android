import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
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
import kotlinx.coroutines.delay


//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun OtpScreen() {
//    var otpValue by remember { mutableStateOf("") }
//
//    Box(
//        modifier = Modifier.fillMaxSize()
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .align(Alignment.TopCenter)
//                .background(color = Color.White)
//        ) {
//
//            CustomAppBar(
//                title = "OTP",
//                onBackClick = {
//                    navController.popBackStack()
//                }
//            )
//
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = hFactor(20)),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//
//                Spacer(modifier = Modifier.height(vFactor(30)))
//                Image(
//                    painter = painterResource(id = R.drawable.ic_email),  // your mail icon
//                    contentDescription = null,
//                    modifier = Modifier
//                        .size(64.dp)
//                        .background(Color(0xFFF8F0E8), shape = CircleShape)
//                        .padding(18.dp)
//                )
//
//                Spacer(modifier = Modifier.height(vFactor(20)))
//
//                CustomMontserratText(
//                    text = "We sent an OTP to your Email",
//                    fontSize = 14.sp,
//                    color = AppSubTextColor
//                )
//
//                Spacer(modifier = Modifier.height(vFactor(30)))
//
//                // — OTP Fields —
//                // — OTP Fields —
//                OtpTextField(
//                    otpText = otpValue,
//                    onOtpTextChange = { otpValue = it },
//                    otpLength = 4
//                )
//
//
//                Spacer(modifier = Modifier.height(vFactor(40)))
//
//                PrimaryButton(
//                    text = "Verify Otp",
//                    backgroundColor = PrimaryColor,
//                    onClick = {
//                        // action
//                    }
//                )
//
//                Spacer(modifier = Modifier.height(vFactor(20)))
//                //here i want resend otp text and counter
//            }
//        }
//    }
//}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtpScreen() {
    var otpValue by remember { mutableStateOf("") }
    var timer by remember { mutableStateOf(60) }
    var isTimerRunning by remember { mutableStateOf(true) }

    // Run countdown
    LaunchedEffect(isTimerRunning, timer) {
        if (isTimerRunning && timer > 0) {
            delay(1000L)
            timer--
        }
        if (timer == 0) {
            isTimerRunning = false
        }
    }

    Box(modifier = Modifier.fillMaxSize().background(color = Color.White)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .background(color = Color.White)
        ) {

            CustomAppBar(
                title = "OTP",
                onBackClick = { navController.popBackStack() }
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = hFactor(20)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(vFactor(30)))

                Image(
                    painter = painterResource(id = R.drawable.ic_email),
                    contentDescription = null,
                    modifier = Modifier
                        .size(64.dp)
                        .background(Color(0xFFF8F0E8), shape = CircleShape)
                        .padding(18.dp)
                )

                Spacer(modifier = Modifier.height(vFactor(20)))

                CustomMontserratText(
                    text = "We sent an OTP to your Email",
                    fontSize = 14.sp,
                    color = AppSubTextColor
                )

                Spacer(modifier = Modifier.height(vFactor(30)))

                OtpTextField(
                    otpText = otpValue,
                    onOtpTextChange = { otpValue = it },
                    otpLength = 4
                )

                Spacer(modifier = Modifier.height(vFactor(40)))

                // Verify button becomes active only when OTP length is 4
                PrimaryButton(
                    text = "Verify Otp",
                    backgroundColor = if (otpValue.length == 4) PrimaryColor else Color.Gray,
                    onClick = {
                     if(otpValue.length == 4){
                         navController.navigate(Dest.NewPasswordScreen::class.toRoute())
                     }
                    },
                )

                Spacer(modifier = Modifier.height(vFactor(20)))

                // Resend OTP section
                if (isTimerRunning) {
                    CustomMontserratText(
                        text = "Resend OTP in $timer sec",
                        fontSize = 14.sp,
                        color = AppSubTextColor
                    )
                } else {
                    Text(
                        text = "Resend OTP",
                        color = PrimaryColor,
                        fontSize = 14.sp,
                        modifier = Modifier.clickable {
                            // action to resend
                            timer = 60
                            isTimerRunning = true


                        }
                    )
                }
            }
        }
    }
}



@Composable
fun OtpTextField(
    otpText: String,
    onOtpTextChange: (String) -> Unit,
    otpLength: Int = 4
) {
    val focusRequesters = remember { List(otpLength) { FocusRequester() } }

    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        repeat(otpLength) { index ->
            val char = otpText.getOrNull(index)?.toString() ?: ""

            BasicTextField(
                value = char,
                onValueChange = { value ->
                    if (value.length <= 1) {
                        val newOtp = StringBuilder(otpText)

                        if (value.isNotEmpty()) {
                            // add or replace this index
                            if (otpText.length > index) {
                                newOtp.setCharAt(index, value[0])
                            } else {
                                newOtp.append(value)
                            }
                            onOtpTextChange(newOtp.toString())

                            // move focus to next
                            if (index < otpLength - 1) {
                                focusRequesters[index + 1].requestFocus()
                            }
                        } else {
                            // delete / go back
                            if (otpText.isNotEmpty()) {
                                newOtp.deleteCharAt(otpText.length - 1)
                                onOtpTextChange(newOtp.toString())

                                if (index > 0) {
                                    focusRequesters[index - 1].requestFocus()
                                }
                            }
                        }
                    }
                },
                singleLine = true,
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),

                        modifier = Modifier
                    .size(60.dp)
                    .focusRequester(focusRequesters[index])
                    .background(color = Color(0xFFF8F0E8), shape = RoundedCornerShape(12.dp))
                    .wrapContentHeight(Alignment.CenterVertically),
                decorationBox = { innerTextField ->
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        innerTextField()
                    }
                }
            )
        }
    }
}

