package com.che.architecture.atomic.design.foundation.dimension

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.che.architecture.atomic.design.foundation.common.Dimension

val LocalPadding = staticCompositionLocalOf { Padding() }

@Immutable
data class Padding(
    val none: Dp = 0.dp,
    val xs: Dp = 4.dp,
    val s: Dp = 6.dp,
    val sm: Dp = 8.dp,
    val m: Dp = 10.dp,
    val ml: Dp = 12.dp,
    val l: Dp = 16.dp,
    val xl: Dp = 24.dp,
    val xxl: Dp = 36.dp,
    val xxxl: Dp = 40.dp,
) : Dimension
