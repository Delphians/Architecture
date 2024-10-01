package com.che.architecture.features.homepage.mvi.homeScreen

import com.che.architecture.domain.model.Ticker

internal sealed interface HomepageIntention {
    data class OpenScreenDetails(val ticker: Ticker) : HomepageIntention
}
