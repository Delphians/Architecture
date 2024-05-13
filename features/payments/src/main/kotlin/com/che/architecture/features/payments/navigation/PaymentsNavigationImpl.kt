package com.che.architecture.features.payments.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.che.architecture.base.mvi.interfaces.MviViewModel
import com.che.architecture.domain.fakes.FakeStockData
import com.che.architecture.domain.model.Ticker
import com.che.architecture.features.payments.mvi.PaymentsIntention
import com.che.architecture.features.payments.mvi.PaymentsState
import com.che.architecture.features.payments.mvi.PaymentsUiEvent
import com.che.architecture.features.payments.screens.PaymentsScreen
import com.che.architecture.ui.compose.R
import com.che.architecture.ui.compose.molecules.Dialog
import com.che.architecture.ui.compose.molecules.LoadingIndicator
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.che.architecture.base.mvi.interfaces.EventsListener
import com.che.architecture.domain.model.ErrorEvent
import com.che.architecture.features.payments.di.PaymentsModule.getViewModel
import com.che.architecture.features.shared.navigation.NavigationGraphBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

internal class PaymentsNavigationImpl(
    private val errorListener: EventsListener<ErrorEvent>
) : NavigationGraphBuilder {

    override val startDestination: String = PaymentsGraph.PaymentsScreen.route

    private lateinit var viewModel: MviViewModel<PaymentsState, PaymentsIntention, PaymentsUiEvent>

    private lateinit var coroutineScope: CoroutineScope

    override fun onStart(owner: LifecycleOwner) {
        coroutineScope = CoroutineScope(Dispatchers.Default)
        viewModel = getViewModel().also {
            it.start(coroutineScope)
        }
    }

    override fun onResume(owner: LifecycleOwner) {
        viewModel.dispatchIntention(
            PaymentsIntention.GetTickerPriceIntention(
                Ticker(FakeStockData.fakeTicker.value),
                FakeStockData.fakeStartDate..FakeStockData.fakeEndDate
            )
        )
        errorListener.event.onEach { errorEvent ->
            errorEvent.cause?.let {
                viewModel.dispatchIntention(
                    PaymentsIntention.FailureIntention(it)
                )
            }
        }.launchIn(coroutineScope)
    }

    override fun onStop(owner: LifecycleOwner) {
        coroutineScope.cancel()
    }

    override fun setupGraph(
        modifier: Modifier,
        navGraphBuilder: NavGraphBuilder,
        navController: NavController
    ) {
        navGraphBuilder.composable(startDestination) {

            viewModel.state.collectAsState().value.let { state ->
                when {
                    state.isLoading -> LoadingIndicator(modifier = Modifier.fillMaxSize())
                    state.error != null -> FailureDialog(state.error)
                    else -> PaymentsScreen(paymentsState = state)
                }
            }
        }
    }

    @Composable
    private fun FailureDialog(failure: Throwable) {
        Dialog(
            title = stringResource(id = R.string.error), text = failure.message ?: "",
            onDismiss = {
                viewModel.dispatchIntention(PaymentsIntention.EmptyIntention)
            }, onConfirm = {
                viewModel.dispatchIntention(PaymentsIntention.EmptyIntention)
            }
        )
    }
}
