package com.sora.sora.core.navigations

import com.sora.sora.features.category.screen.CategoryDetailScreen
import CreateAccountScreen
import ForgetPasswordScreen
import NewPasswordScreen
import OtpScreen
import SignInScreen
import SplashScreen
import WelcomeScreen
import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sora.sora.AccountDetailsScreen
import com.sora.sora.core.temp.SeeAllModel
import com.sora.sora.features.static_screens.OnboardingScreen
import com.sora.sora.core.temp.TempCustomData
import com.sora.sora.features.category.CategoryDetailModel
import com.sora.sora.features.category.screen.CategoryModel
import com.sora.sora.features.common.view.screen.SeeAllProductScreen
import com.sora.sora.features.dashboard.CartScreen
import com.sora.sora.features.dashboard.CategoryScreen
import com.sora.sora.features.dashboard.DashboardScreen
import com.sora.sora.features.dashboard.FavoritesScreen
import com.sora.sora.features.dashboard.HomeScreen
import com.sora.sora.features.dashboard.ItemDetailScreen
import com.sora.sora.features.favourites.widgets.ReviewDetailScreen
import com.sora.sora.features.profile.screen.AboutUsScreen
import com.sora.sora.features.profile.screen.AddNewAddressScreen
import com.sora.sora.features.profile.screen.AddReviewScreen

import com.sora.sora.features.profile.screen.EditProfileScreen
import com.sora.sora.features.profile.screen.FaqScreen
import com.sora.sora.features.profile.screen.MyAddressesScreen
import com.sora.sora.features.profile.screen.NotificationScreen
import com.sora.sora.features.profile.screen.OrderDetailScreen
import com.sora.sora.features.profile.screen.OrdersScreen
import com.sora.sora.features.profile.screen.PaymentSuccessScreen
import com.sora.sora.features.profile.screen.PrivacyPolicyScreen
import com.sora.sora.features.profile.screen.ProfileScreen
import com.sora.sora.features.profile.screen.TermsAndConditionsScreen


import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import java.net.URLDecoder
import kotlin.reflect.KClass

@Parcelize
@Serializable
data class Dummy(
    val name: String,
    val age: Int
) : Parcelable

fun <T : Any> KClass<T>.toRoute(): String {
    return this.simpleName.orEmpty()
}

@Composable
fun MainNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    LaunchedEffect(Unit) {
        NavigationManager.navController = navController
    }

    NavHost(
        navController = navController,
        startDestination = Dest.OrdersScreen::class.toRoute(),
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
                    navController.navigate(Dest.CreateAccountScreen::class.toRoute())
                },
                onSkipClick = {
                    navController.navigate(Dest.DashBoardScreen::class.toRoute())
                }
            )
        }

        composable(Dest.SignIn::class.toRoute()) {
            SignInScreen(
                onLoginClick = {
                    NavigationManager.navigateAndClearStack(Dest.DashBoardScreen::class.toRoute())
                },
                onRegisterClick = {
                    navController.navigate(Dest.CreateAccountScreen::class.toRoute())
                },
                onSocialLoginClick = {
                    NavigationManager.navigateAndClearStack(Dest.DashBoardScreen::class.toRoute())
                },
                onCountryCodeChange = {}
            )
        }

        composable(Dest.CreateAccountScreen::class.toRoute()) {
            CreateAccountScreen(

            )
        }
        composable(Dest.ForgetPasswordScreen::class.toRoute()) {
            ForgetPasswordScreen(

            )
        }
        composable(Dest.OtpScreen::class.toRoute()) {
            OtpScreen(

            )
        }
        composable(Dest.NewPasswordScreen::class.toRoute()) {
            NewPasswordScreen(

            )
        }

        composable(Dest.AccountDetailsScreen::class.toRoute()) {
            AccountDetailsScreen(
                onBackClick = {},
                onContinueClick = {
                    navController.navigate(Dest.DashBoardScreen::class.toRoute())
                }
            )
        }

        composable(Dest.HomeScreen::class.toRoute()) {
            HomeScreen( )
        }

        composable(Dest.DashBoardScreen::class.toRoute()) {
            DashboardScreen( navController = navController)
        }

        composable(Dest.CategoryScreen::class.toRoute()) {
            val categories = TempCustomData().categories
            CategoryScreen(  categories = categories )
        }
//
//        composable(Dest.SeeAllProductScreen::class.toRoute()) {
//            val categories = TempCustomData().categories
//            CategoryScreen(  categories = categories )
//        }

        composable(Dest.SeeAllProductScreen::class.toRoute() + "?title={title}&list={list}") { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: ""
            val list = backStackEntry.arguments?.getString("list")?.split(",") ?: listOf()
            SeeAllProductScreen(seeAllModel = SeeAllModel(title = title, list = list))
        }

        composable(Dest.CategoryDetailScreen::class.toRoute() + "?title={title}&themeColor={themeColor}") { backStackEntry ->
            val encodedTitle = backStackEntry.arguments?.getString("title") ?: ""
            val encodedThemeColor = backStackEntry.arguments?.getString("themeColor") ?: ""

            // Decode the title and themeColor from the URL encoding
            val title = URLDecoder.decode(encodedTitle, "UTF-8")
            val themeColor = URLDecoder.decode(encodedThemeColor, "UTF-8")

            // Pass the decoded title and themeColor to CategoryDetailScreen
            CategoryDetailScreen(categoryDetailModel = CategoryDetailModel(title = title, themeColor = themeColor))
        }



        composable(Dest.FavoritesScreen::class.toRoute()) {
            FavoritesScreen()
        }
        composable(Dest.CartScreen::class.toRoute()) { CartScreen()}
        composable(Dest.ItemDetailScreen::class.toRoute()) { ItemDetailScreen()}
//        composable(Dest.CategoryDetailScreen::class.toRoute()) { CategoryDetailScreen()}

        composable(Dest.ProfileScreen::class.toRoute()) { ProfileScreen()}
        composable(Dest.OrdersScreen::class.toRoute()) { OrdersScreen()}
        composable(Dest.MyAddressScreen::class.toRoute()) { MyAddressesScreen() }
        composable(Dest.AddNewAddressScreen::class.toRoute()) { AddNewAddressScreen() }
        composable(Dest.TermConditionScreen::class.toRoute()) { TermsAndConditionsScreen() }
        composable(Dest.PrivacyPolicyScreen::class.toRoute()) { PrivacyPolicyScreen() }
        composable(Dest.FaqScreen::class.toRoute()) { FaqScreen() }
        composable(Dest.AboutUsScreen::class.toRoute()) { AboutUsScreen() }
        composable(Dest.EditProfileScreen::class.toRoute()) { EditProfileScreen() }
        composable(Dest.AddReviewScreen::class.toRoute()) { AddReviewScreen() }
        composable(Dest.ReviewDetailScreen::class.toRoute()) { ReviewDetailScreen() }
        composable(Dest.SplashScreen::class.toRoute()) { SplashScreen() }
        composable(Dest.NotificationScreen::class.toRoute()) { NotificationScreen() }

        /**Single Primitive Parameter*/
        composable(Dest.OrderDetailScreen::class.toRoute() + "?status={status}") { backStackEntry ->
            val status = backStackEntry.arguments?.getString("status")
            if (status != null) {
                OrderDetailScreen(status = status)
            }
        }
        composable(Dest.PaymentSuccessScreen::class.toRoute()) { PaymentSuccessScreen() }



    }

}
