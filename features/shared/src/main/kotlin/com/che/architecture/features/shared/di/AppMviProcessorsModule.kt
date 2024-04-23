package com.che.architecture.features.shared.di

import com.che.architecture.base.mvi.interfaces.IntentionProcessor
import com.che.architecture.features.shared.app.AppIntentions
import com.che.architecture.features.shared.app.AppMviState
import com.che.architecture.features.shared.app.processor.TabChangedIntentionProcessor
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet

@Module
abstract class AppMviProcessorsModule {

    @Binds
    @IntoSet
    internal abstract fun bindsTabChangedIntentionProcessor(
        it: TabChangedIntentionProcessor
    ): IntentionProcessor<AppMviState, AppIntentions>
}
