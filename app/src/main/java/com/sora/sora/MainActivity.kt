package com.sora.sora

import SignInScreen
import WelcomeScreen
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.sora.sora.core.navigations.MainNavigation
import com.sora.sora.features.HomeScreen
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

