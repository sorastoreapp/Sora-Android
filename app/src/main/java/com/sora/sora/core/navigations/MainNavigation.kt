package com.sora.sora.core.navigations

import SignInScreen
import WelcomeScreen
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sora.sora.AccountDetailsScreen
import com.sora.sora.OnboardingScreen
import com.sora.sora.core.temp.TempCustomData
import com.sora.sora.features.dashboard.CartScreen
import com.sora.sora.features.dashboard.CategoryScreen
import com.sora.sora.features.dashboard.DashboardScreen
import com.sora.sora.features.dashboard.FavoritesScreen
import com.sora.sora.features.dashboard.HomeScreen
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.reflect.KClass

@Parcelize
@Serializable
data class Dummy(
    val name: String,
    val age: Int
) : Parcelable


val DummyType = object : NavType<Dummy>(false) {
    override fun get(bundle: Bundle, key: String): Dummy? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, Dummy::class.java)
        } else {
            bundle.getParcelable(key)
        }
    }

    override fun parseValue(value: String): Dummy {
        return Json.decodeFromString(value)
    }

    override fun put(bundle: Bundle, key: String, value: Dummy) {
        bundle.putParcelable(key, value)
    }

    override fun serializeAsValue(value: Dummy): String {
        return Json.encodeToString(value)
    }
}


class CustomNavType<T : Parcelable>(
    private val clazz: KClass<T>,
    private val serializer: KSerializer<T>
) : NavType<T>(false) {
    override fun get(bundle: Bundle, key: String): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, clazz.java)
        } else {
            bundle.getParcelable(key)
        }
    }

    override fun parseValue(value: String): T {
        return Json.decodeFromString(serializer, value)
    }

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putParcelable(key, value)
    }

    override fun serializeAsValue(value: T): String {
        return Json.encodeToString(serializer, value)
    }
}

fun <T : Any> KClass<T>.toRoute(): String {
    return this.simpleName.orEmpty()
}

@Composable
fun MainNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Dest.DashBoardScreen::class.toRoute(),
        modifier = modifier
    ) {

        composable(Dest.OnboardingScreen::class.toRoute()) {
            // Pass navController so OnboardingScreen can navigate
            OnboardingScreen(navController = navController)
        }

        composable(Dest.Welcome::class.toRoute()) {
            WelcomeScreen(
                onLoginClick = {
                    navController.navigate(Dest.SignIn::class.toRoute())
                },
                onSignupClick = {
                    navController.navigate(Dest.AccountDetailsScreen::class.toRoute())
                },
                onSkipClick = {
                    navController.navigate(Dest.HomeScreen::class.toRoute())
                }
            )
        }

        composable(Dest.SignIn::class.toRoute()) {
            SignInScreen(
                onLoginClick = {
                    navController.navigate(Dest.HomeScreen::class.toRoute())
                },
                onRegisterClick = {
                    navController.navigate(Dest.AccountDetailsScreen::class.toRoute())
                },
                onSocialLoginClick = {
                    navController.navigate(Dest.HomeScreen::class.toRoute())
                },
                onCountryCodeChange = {}
            )
        }

        composable(Dest.AccountDetailsScreen::class.toRoute()) {
            AccountDetailsScreen(
                onBackClick = {},
                onContinueClick = {
                    navController.navigate(Dest.HomeScreen::class.toRoute())
                }
            )
        }


        composable(Dest.HomeScreen::class.toRoute()) {
            HomeScreen( )
        }

        composable(Dest.DashBoardScreen::class.toRoute()) {
            DashboardScreen( )
        }
        composable(Dest.CategoryScreen::class.toRoute()) {
            val categories = TempCustomData().categories
            CategoryScreen(  categories = categories )
        }

        composable(Dest.FavoritesScreen::class.toRoute()) { FavoritesScreen()}
        composable(Dest.CartScreen::class.toRoute()) { CartScreen()}

    }
}
