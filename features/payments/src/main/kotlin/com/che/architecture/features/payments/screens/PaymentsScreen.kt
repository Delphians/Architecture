package com.che.architecture.features.payments.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.che.architecture.domain.fakes.FakeStockData
import com.che.architecture.features.payments.mvi.PaymentsState
import com.che.architecture.ui.compose.foundation.ArchitectureTheme
import com.che.architecture.ui.compose.foundation.dimension.LocalPadding
import java.text.DecimalFormat

@Composable
internal fun PaymentsScreen(
    paymentsState: PaymentsState,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
    ) {
        items(paymentsState.prices) {
            Card(
                modifier = Modifier
                    .padding(LocalPadding.current.xs),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    val typography = MaterialTheme.typography
                    Box(
                        modifier
                            .size(4.dp, 36.dp)
                            .background(color = Color.Green)
                    )
                    Text(
                        text = it.date,
                        style = typography.bodyMedium,
                        modifier = Modifier
                            .padding(
                                PaddingValues(
                                    start = LocalPadding.current.l
                                )
                            )
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = "$ ",
                        style = typography.bodyMedium
                    )
                    Text(
                        text = priceFormat.format(it.close),
                        style = typography.bodyMedium,
                        modifier = Modifier
                            .padding(
                                PaddingValues(
                                    end = LocalPadding.current.l
                                )
                            )
                    )
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(Color.Gray)
                )
            }
        }
    }
}

private val priceFormat = DecimalFormat("#,###.##")

@SuppressWarnings("UnusedPrivateMember")
@PreviewLightDark
@Composable
private fun PaymentsScreenPreview() {
    ArchitectureTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            PaymentsScreen(
                paymentsState = PaymentsState(
                    FakeStockData.fakePricesGenerator(
                        FakeStockData.dateRange
                    )
                )
            )
        }
    }
}
