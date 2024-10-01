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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.che.architecture.atomic.design.foundation.dimension.LocalPadding
import com.che.architecture.features.payments.mvi.PaymentsState

@Composable
internal fun PaymentsScreen(
    modifier: Modifier = Modifier,
    paymentsState: PaymentsState
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
                        text = "${it.close}",
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
