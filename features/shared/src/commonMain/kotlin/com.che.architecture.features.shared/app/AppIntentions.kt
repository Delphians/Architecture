package com.che.architecture.features.shared.app

import com.che.architecture.atomic.design.tabs.BottomTab

sealed class AppIntentions {
    data object InitialIntention : AppIntentions()

    data class TabChangedIntention(
        val tab: BottomTab
    ) : AppIntentions()
}
