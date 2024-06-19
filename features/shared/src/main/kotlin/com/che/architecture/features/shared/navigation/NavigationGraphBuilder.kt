package com.che.architecture.features.shared.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import com.che.architecture.base.android.service.lifecycle.LifecycleAwareNavigation

interface NavigationGraphBuilder : LifecycleAwareNavigation {

    val route: String

    fun setupGraph(
        navGraphBuilder: NavGraphBuilder,
        modifier: Modifier
    )
}
