package com.che.architecture.features.homepage.di

import com.che.architecture.base.mvi.DefaultEventsHandler
import com.che.architecture.base.mvi.DefaultIntentionDispatcher
import com.che.architecture.base.mvi.interfaces.EventsDispatcher
import com.che.architecture.base.mvi.interfaces.EventsListener
import com.che.architecture.base.mvi.interfaces.IntentionDispatcher
import com.che.architecture.base.mvi.interfaces.MviViewModel
import com.che.architecture.features.homepage.mvi.homeScreen.HomepageIntention
import com.che.architecture.features.homepage.mvi.homeScreen.HomepageState
import com.che.architecture.features.homepage.mvi.homeScreen.HomepageUiEvent
import com.che.architecture.features.homepage.mvi.homeScreen.HomepageViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module(includes = [HomepageProcessorsModule::class])
@InstallIn(ActivityComponent::class)
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
