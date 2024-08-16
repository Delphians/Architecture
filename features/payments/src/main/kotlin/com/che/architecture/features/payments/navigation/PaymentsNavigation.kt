package com.che.architecture.features.payments.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.che.architecture.base.mvi.interfaces.EventsListener
import com.che.architecture.base.mvi.interfaces.MviViewModel
import com.che.architecture.domain.fakes.FakeStockData
import com.che.architecture.domain.model.ErrorEvent
import com.che.architecture.domain.model.Ticker
import com.che.architecture.features.payments.mvi.PaymentsIntention
import com.che.architecture.features.payments.mvi.PaymentsState
import com.che.architecture.features.payments.mvi.PaymentsUiEvent
import com.che.architecture.features.payments.screens.PaymentsScreen
import com.che.architecture.ui.compose.R
import com.che.architecture.ui.compose.molecules.Dialog
import com.che.architecture.ui.compose.molecules.LoadingIndicator
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.che.architecture.features.payments.di.PaymentsModule.getViewModel
import com.che.architecture.features.shared.navigation.NavigationGraphBuilder

internal class PaymentsNavigation(
    private val errorListener: EventsListener<ErrorEvent>
) : NavigationGraphBuilder {

    override val route: String = PAYMENTS_GRAPH_ROUTE

    private lateinit var viewModel: MviViewModel<PaymentsState, PaymentsIntention, PaymentsUiEvent>

    override fun onStart(owner: LifecycleOwner) {
        viewModel = getViewModel().apply {
            start(owner.lifecycleScope)
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
        }.launchIn(viewModel.getScope())
    }

    override fun onStop(owner: LifecycleOwner) {
        viewModel.stop()
    }

    override fun setupGraph(
        navGraphBuilder: NavGraphBuilder,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(route) {

            val navHostController = rememberNavController()

            NavHost(
                navController = navHostController,
                startDestination = PaymentsGraph.PaymentsScreen.destination
            ) {
                composable(
                    route = PaymentsGraph.PaymentsScreen.destination
                ) {
                    viewModel.state.collectAsState().value.let { state ->
                        when {
                            state.isLoading -> LoadingIndicator(modifier = Modifier.fillMaxSize())
                            state.error != null -> FailureDialog(state.error)
                            else -> PaymentsScreen(paymentsState = state)
                        }
                    }
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
