package com.che.architecture.features.homepage.mvi.homeScreen

import com.che.architecture.domain.model.Ticker

internal sealed interface HomepageUiEvent {
    data class NavigateToDetails(val ticker: Ticker) : HomepageUiEvent
}
