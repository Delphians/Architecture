package com.che.architecture.features.shared.app

import com.che.architecture.atomic.design.tabs.BottomTab

sealed class AppUiEvent {
    data class TabChanged(
        val activeTab: BottomTab
    ) : AppUiEvent()
}
