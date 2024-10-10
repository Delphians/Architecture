package com.che.architecture.features.payments.mvi.processor

import com.che.architecture.base.mvi.interfaces.IntentionProcessor
import com.che.architecture.domain.utils.className
import com.che.architecture.features.payments.di.paymentsModule
import com.che.architecture.features.payments.mvi.FailureResults
import com.che.architecture.features.payments.mvi.PaymentsIntention
import com.che.architecture.features.payments.mvi.PaymentsState
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.qualifier.named
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertTrue

internal class FailureIntentionProcessorTest {

    private val koinApp = startKoin {
        modules(paymentsModule)
    }.koin

    private val testSubject = koinApp.get<IntentionProcessor<PaymentsState, PaymentsIntention>>(
        named(FailureIntentionProcessor::class.className())
    )

    @AfterTest
    fun after() {
        stopKoin()
    }

    @Test
    fun `When process FailureIntention should get FailureResults`() = runTest {
        val actualResult = testSubject.process(
            flowOf(PaymentsIntention.FailureIntention(Throwable()))
        ).first()
        assertTrue(actualResult::class == FailureResults::class)
    }
}
