package com.che.architecture.features.shared.app.processor

import com.che.architecture.base.mvi.interfaces.EventsDispatcher
import com.che.architecture.base.mvi.interfaces.IntentionProcessor
import com.che.architecture.base.mvi.interfaces.MviResult
import com.che.architecture.base.mvi.interfaces.ignoreResult
import com.che.architecture.features.shared.app.AppIntentions
import com.che.architecture.features.shared.app.AppMviState
import com.che.architecture.features.shared.app.AppUiEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.onEach

internal class TabChangedIntentionProcessor(
    private val eventsDispatcher: EventsDispatcher<AppUiEvent>
) : IntentionProcessor<AppMviState, AppIntentions> {

    override fun process(intentions: Flow<AppIntentions>): Flow<MviResult<AppMviState>> =
        intentions.filterIsInstance<AppIntentions.TabChangedIntention>()
            .onEach {
                eventsDispatcher.dispatch(
                    AppUiEvent.TabChanged(it.tab)
                )
            }.ignoreResult()
}
