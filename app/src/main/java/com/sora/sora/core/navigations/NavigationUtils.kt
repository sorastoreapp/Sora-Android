package com.sora.sora.core.navigations


import kotlinx.serialization.Serializable

sealed class Dest{

    @Serializable
    data object Welcome : Dest()

    @Serializable
    data object SignIn : Dest()

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

}