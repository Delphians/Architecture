package com.che.architecture.features.shared.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.che.architecture.base.android.service.lifecycle.LifecycleAwareNavigation

interface NavigationGraphBuilder : LifecycleAwareNavigation {

    val startDestination: String

    fun setupGraph(
        modifier: Modifier,
        navGraphBuilder: NavGraphBuilder,
        navController: NavController
    )
}
