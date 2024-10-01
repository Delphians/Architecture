package com.che.architecture.features.homepage.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.che.architecture.domain.fakes.FakeStockData
import com.che.architecture.domain.model.Ticker

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    onHomeScreenDetailsClick: (Ticker) -> Unit
) {
    Box(
        modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center

    ) {
        Canvas(
            modifier = Modifier
                .size(200.dp)
                .clickable {
                    onHomeScreenDetailsClick(FakeStockData.fakeTicker)
                },
            onDraw = {
                drawCircle(
                    color = Color.Green,
                    style = Stroke(width = 10F)
                )
            }
        )
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold)) {
                    append(FakeStockData.fakeTicker.value)
                }
            },
            modifier = Modifier.align(Alignment.Center),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}
