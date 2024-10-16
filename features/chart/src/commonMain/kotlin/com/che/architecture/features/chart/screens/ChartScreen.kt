package com.che.architecture.features.chart.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.che.architecture.atomic.design.foundation.dimension.LocalPadding
import com.che.architecture.atomic.design.molecules.DrawChart
import com.che.architecture.features.chart.mvi.ChartState

@Composable
internal fun ChartScreen(
    modifier: Modifier = Modifier,
    chartState: ChartState
) {
    Card(
        modifier = Modifier
            .height(360.dp)
            .padding(LocalPadding.current.l),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            DrawChart(chartPoints = chartState.points)
        }
    }
}
