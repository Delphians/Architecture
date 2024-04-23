package com.che.architecture.features.homepage.di

import com.che.architecture.base.mvi.DefaultEventsHandler
import com.che.architecture.base.mvi.DefaultIntentionDispatcher
import com.che.architecture.base.mvi.interfaces.EventsDispatcher
import com.che.architecture.base.mvi.interfaces.EventsListener
import com.che.architecture.base.mvi.interfaces.IntentionDispatcher
import com.che.architecture.base.mvi.interfaces.MviViewModel
import com.che.architecture.features.homepage.mvi.HomepageIntention
import com.che.architecture.features.homepage.mvi.HomepageState
import com.che.architecture.features.homepage.mvi.HomepageUiEvent
import com.che.architecture.features.homepage.mvi.HomepageViewModel
import dagger.Binds
import dagger.Module

@Module
abstract class HomepageModule {

    @Binds
    internal abstract fun bindsMviViewModel(
        it: HomepageViewModel
    ): MviViewModel<HomepageState, HomepageIntention, HomepageUiEvent>

    @Binds
    internal abstract fun bindsEventListener(
        handler: DefaultEventsHandler<HomepageUiEvent>
    ): EventsListener<HomepageUiEvent>

    @Binds
    internal abstract fun bindsEventDispatcher(
        handler: DefaultEventsHandler<HomepageUiEvent>
    ): EventsDispatcher<HomepageUiEvent>

    @Binds
    internal abstract fun bindsIntentionDispatcher(
        dispatcher: DefaultIntentionDispatcher<HomepageIntention>
    ): IntentionDispatcher<HomepageIntention>
}
