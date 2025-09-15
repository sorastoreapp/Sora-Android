package com.sora.sora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatDelegate  // ✅ Import necesario
import com.sora.sora.core.navigations.MainNavigation
import com.sora.sora.ui.theme.SoraTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // ✅ Desactiva el modo oscuro
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            SoraTheme {
                Surface(modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)) {
                    MainNavigation()
                }
            }
        }
    }
}
