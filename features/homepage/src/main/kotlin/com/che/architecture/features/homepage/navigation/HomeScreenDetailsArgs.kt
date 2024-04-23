package com.che.architecture.features.homepage.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

internal data class HomeScreenDetailsArgs(
    val ticker: String = "ticker"
) {
    val args = listOf(
        navArgument(ticker) {
            type = NavType.StringType
        }
    )
}
