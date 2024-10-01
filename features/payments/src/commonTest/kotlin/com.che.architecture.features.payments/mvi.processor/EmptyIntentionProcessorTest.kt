package com.che.architecture.features.payments.mvi.processor

import com.che.architecture.features.payments.mvi.EmptyResults
import com.che.architecture.features.payments.mvi.PaymentsIntention
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertTrue

internal class EmptyIntentionProcessorTest {

    private val testSubject = EmptyIntentionProcessor()

    @Test
    fun `When process EmptyIntention should get EmptyResults`() = runTest {
        val actualResult = testSubject.process(flowOf(PaymentsIntention.EmptyIntention)).first()
        assertTrue(actualResult::class == EmptyResults::class)
    }
}
