package com.che.architecture.atomic.design.tabs

import androidx.compose.runtime.Immutable
import com.che.architecture.atomic.design.resources.Res
import com.che.architecture.atomic.design.resources.chart
import com.che.architecture.atomic.design.resources.home
import com.che.architecture.atomic.design.resources.ic_chart
import com.che.architecture.atomic.design.resources.ic_home
import com.che.architecture.atomic.design.resources.ic_payments
import com.che.architecture.atomic.design.resources.payments
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

@Immutable
enum class BottomTab(
    val title: StringResource,
    val icon: DrawableResource
) {
    HOME(
        title = Res.string.home,
        icon = Res.drawable.ic_home
    ),
    PAYMENTS(
        title = Res.string.payments,
        icon = Res.drawable.ic_payments
    ),
    CHART(
        title = Res.string.chart,
        icon = Res.drawable.ic_chart
    )
}
