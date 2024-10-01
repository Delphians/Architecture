package com.che.architecture.features.shared.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder

interface NavigationGraphBuilder {

    val route: String

    fun setupGraph(
        navGraphBuilder: NavGraphBuilder,
        modifier: Modifier
    )
}
