package com.che.architecture.features.payments.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.che.architecture.features.shared.navigation.NavigationGraphBuilder
import dagger.Reusable
import javax.inject.Inject

@Reusable
internal class PaymentsNavigationImpl @Inject constructor(
    private val viewModel: MviViewModel<PaymentsState, PaymentsIntention, PaymentsUiEvent>,
    private val errorListener: EventsListener<ErrorEvent>
) : NavigationGraphBuilder {

    override val startDestination: String = PaymentsGraph.PaymentsScreen.route
    override fun onResume(owner: LifecycleOwner) {
        viewModel.dispatchIntention(
            PaymentsIntention.GetTickerPriceIntention(
                Ticker(FakeStockData.fakeTicker.value),
                FakeStockData.fakeStartDate..FakeStockData.fakeEndDate
            )
        )
    }

    override fun onStart(owner: LifecycleOwner) {
        viewModel.start(owner.lifecycleScope)
    }

    override fun onStop(owner: LifecycleOwner) {
        viewModel.stop()
    }

    override fun setupGraph(
        navGraphBuilder: NavGraphBuilder,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(startDestination) {

            val paymentsNavController = rememberNavController()

            NavHost(
                navController = paymentsNavController,
                startDestination = startDestination
            ) {
                composable(
                    route = startDestination
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

            LaunchedEffect(key1 = Unit) {
                errorListener.event.onEach { errorEvent ->
                    errorEvent.cause?.let {
                        viewModel.dispatchIntention(
                            PaymentsIntention.FailureIntention(it)
                        )
                    }
                }.launchIn(this)
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
