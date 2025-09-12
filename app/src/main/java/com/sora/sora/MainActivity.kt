package com.sora.sora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sora.sora.core.navigations.MainNavigation
import com.sora.sora.ui.theme.SoraTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            SoraTheme (){
                // Set background to white for the entire screen
                Surface(modifier = Modifier.fillMaxSize().background(Color.White)) {
                    // Use MainNavigation for navigation or HomeScreen based on your app's flow
                    MainNavigation()
                }
            }
        }
    }
}
