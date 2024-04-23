package com.che.architecture.features.payments.mvi.processor

import com.che.architecture.features.payments.mvi.FailureResults
import com.che.architecture.features.payments.mvi.PaymentsIntention
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class FailureIntentionProcessorTest {

    private val testSubject = FailureIntentionProcessor()

    @Test
    fun `When process FailureIntention should get FailureResults`() = runTest {
        val actualResult = testSubject.process(
            flowOf(PaymentsIntention.FailureIntention(Throwable()))
        ).first()
        Assertions.assertTrue(actualResult::class == FailureResults::class)
    }
}
