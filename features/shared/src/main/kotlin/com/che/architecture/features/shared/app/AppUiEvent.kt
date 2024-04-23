package com.che.architecture.features.shared.app

import com.che.architecture.ui.compose.tabs.BottomTab

sealed class AppUiEvent {
    data class TabChanged(
        val activeTab: BottomTab
    ) : AppUiEvent()
}
