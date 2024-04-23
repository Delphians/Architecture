package com.che.architecture.ui.compose.tabs

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.che.architecture.ui.compose.R

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
