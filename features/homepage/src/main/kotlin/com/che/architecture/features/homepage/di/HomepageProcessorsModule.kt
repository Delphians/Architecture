package com.che.architecture.features.homepage.di

import com.che.architecture.base.mvi.interfaces.IntentionProcessor
import com.che.architecture.features.homepage.mvi.HomepageIntention
import com.che.architecture.features.homepage.mvi.HomepageState
import com.che.architecture.features.homepage.mvi.processor.OpenScreenDetailsProcessor
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet

@Module
abstract class HomepageProcessorsModule {

    @IntoSet
    @Binds
    internal abstract fun bindsOpenScreenDetailsProcessor(
        it: OpenScreenDetailsProcessor
    ): IntentionProcessor<HomepageState, HomepageIntention>
}
