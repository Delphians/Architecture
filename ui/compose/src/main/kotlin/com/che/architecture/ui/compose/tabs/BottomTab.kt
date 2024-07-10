package com.che.architecture.ui.compose.tabs

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.che.architecture.ui.compose.R

@Immutable
enum class BottomTab(
    @StringRes
    val title: Int,
    @DrawableRes
    val icon: Int
) {
    HOME(
        title = R.string.home,
        icon = R.drawable.ic_home
    ),
    PAYMENTS(
        title = R.string.payments,
        icon = R.drawable.ic_payments
    ),
    CHART(
        title = R.string.chart,
        icon = R.drawable.ic_chart
    )
}
