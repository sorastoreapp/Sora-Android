package com.sora.sora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sora.sora.core.navigations.MainNavigation
import com.sora.sora.ui.theme.SoraTheme





/**CODE WITHOUT NAVIGATIONS"*/
//
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SoraTheme {

//                HomeScreen()
                MainNavigation()

//                AccountDetailsScreen(
//                    onBackClick = { /* Handle back */ },
//                    onContinueClick = { data -> /* Handle data */ }
//                )

//                Surface(modifier = Modifier.fillMaxSize()) {
//                    SignInScreen(
//                        modifier = Modifier.fillMaxSize(),
//                        onLoginClick = { phoneNumber ->
//                            Log.d("SignInScreen", "Login clicked with phone: $phoneNumber")
//                            // TODO: Add your login logic here
//                        },
//                        onRegisterClick = {
//                            Log.d("SignInScreen", "Register clicked")
//                            // TODO: Navigate to registration screen
//                        },
//                        onSocialLoginClick = { provider ->
//                            Log.d("SignInScreen", "Social login clicked: $provider")
//                            // TODO: Trigger social login flow (Google, Facebook, Apple)
//                        },
//                        onCountryCodeChange = { countryCode ->
//                            Log.d("SignInScreen", "Country code changed to $countryCode")
//                            // TODO: Handle country code changes if needed
//                        }
//                    )
//                }
            }
        }
    }
}


