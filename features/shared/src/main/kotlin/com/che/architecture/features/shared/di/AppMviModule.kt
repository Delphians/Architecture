package com.che.architecture.features.shared.di

import com.che.architecture.base.mvi.DefaultEventsHandler
import com.che.architecture.base.mvi.DefaultIntentionDispatcher
import com.che.architecture.base.mvi.interfaces.EventsDispatcher
import com.che.architecture.base.mvi.interfaces.EventsListener
import com.che.architecture.base.mvi.interfaces.IntentionDispatcher
import com.che.architecture.base.mvi.interfaces.MviViewModel
import com.che.architecture.features.shared.app.AppIntentions
import com.che.architecture.features.shared.app.AppMviState
import com.che.architecture.features.shared.app.AppMviViewModel
import com.che.architecture.features.shared.app.AppUiEvent
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module(includes = [AppMviProcessorsModule::class])
@InstallIn(ActivityRetainedComponent::class)
abstract class AppMviModule {

    @Binds
    internal abstract fun bindsMviViewModel(
        it: AppMviViewModel
    ): MviViewModel<AppMviState, AppIntentions, AppUiEvent>

    @Binds
    internal abstract fun bindsEventListener(
        handler: DefaultEventsHandler<AppUiEvent>
    ): EventsListener<AppUiEvent>

    @Binds
    internal abstract fun bindsEventDispatcher(
        handler: DefaultEventsHandler<AppUiEvent>
    ): EventsDispatcher<AppUiEvent>

    @Binds
    internal abstract fun bindsIntentionDispatcher(
        dispatcher: DefaultIntentionDispatcher<AppIntentions>
    ): IntentionDispatcher<AppIntentions>
}
