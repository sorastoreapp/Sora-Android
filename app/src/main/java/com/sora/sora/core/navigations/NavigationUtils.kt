package com.sora.sora.core.navigations


import kotlinx.serialization.Serializable

sealed class Dest{

    @Serializable
    data object Welcome : Dest()

    @Serializable
    data object SignIn : Dest()

    @Serializable
    data object CreateAccountScreen : Dest()

    @Serializable
    data object OnboardingScreen : Dest()

    @Serializable
    data object AccountDetailsScreen : Dest()

    @Serializable
    data object HomeScreen : Dest()

    @Serializable
    data object DashBoardScreen : Dest()

    @Serializable
    data object CategoryScreen : Dest()

    @Serializable
    data object FavoritesScreen : Dest()

    @Serializable
    data object CartScreen : Dest()
    @Serializable
    data object ItemDetailScreen : Dest()

    @Serializable
    data object CategoryDetailScreen : Dest()

    @Serializable
    data object ProfileScreen : Dest()

    @Serializable
    data object OrdersScreen : Dest()

    @Serializable
    data object MyAddressScreen : Dest()

    @Serializable
    data object AddNewAddressScreen : Dest()

    @Serializable
    data object TermConditionScreen : Dest()

    @Serializable
    data object PrivacyPolicyScreen : Dest()

    @Serializable
    data object FaqScreen : Dest()

    @Serializable
    data object AboutUsScreen : Dest()

    @Serializable
    data object EditProfileScreen : Dest()

    @Serializable
    data object AddReviewScreen : Dest()

    @Serializable
    data object ReviewDetailScreen : Dest()

    @Serializable
    data object SplashScreen : Dest()

    @Serializable
    data object OrderDetailScreen : Dest()

    @Serializable
    data object NotificationScreen : Dest()

}

// -- 