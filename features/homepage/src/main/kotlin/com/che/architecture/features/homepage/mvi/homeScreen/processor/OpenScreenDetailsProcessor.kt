package com.che.architecture.features.homepage.mvi.homeScreen.processor

import com.che.architecture.base.mvi.interfaces.EventsDispatcher
import com.che.architecture.base.mvi.interfaces.IntentionProcessor
import com.che.architecture.base.mvi.interfaces.MviResult
import com.che.architecture.base.mvi.interfaces.ignoreResult
import com.che.architecture.features.homepage.mvi.homeScreen.HomepageIntention
import com.che.architecture.features.homepage.mvi.homeScreen.HomepageState
import com.che.architecture.features.homepage.mvi.homeScreen.HomepageUiEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

internal class OpenScreenDetailsProcessor @Inject constructor(
    private val eventDispatcher: EventsDispatcher<HomepageUiEvent>
) :
    IntentionProcessor<HomepageState, HomepageIntention> {

    override fun process(intentions: Flow<HomepageIntention>): Flow<MviResult<HomepageState>> =
        intentions.filterIsInstance<HomepageIntention.OpenScreenDetails>()
            .onEach {
                eventDispatcher.dispatch(HomepageUiEvent.NavigateToDetails(it.ticker))
            }.ignoreResult()
}
