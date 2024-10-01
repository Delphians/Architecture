package com.che.architecture.features.homepage.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.che.architecture.atomic.design.foundation.dimension.LocalPadding
import com.che.architecture.domain.model.Ticker

@Composable
internal fun HomeScreenDetails(
    modifier: Modifier = Modifier,
    ticker: Ticker
) {
    Box(
        modifier
            .fillMaxSize()
            .padding(
                PaddingValues(
                    start = LocalPadding.current.l
                )
            )
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Green
                    )
                ) {
                    append("Alphabet Inc. - ${ticker.value}\n\n")
                }
                withStyle(
                    style = SpanStyle(
                        fontSize = 20.sp
                    )
                ) {
                    append("1600 Amphitheatre Parkway\n")
                    append("Mountain View, CA 94043\n")
                    append("United States\n")
                }
            },
            modifier = Modifier.align(Alignment.Center),
        )
    }
}
