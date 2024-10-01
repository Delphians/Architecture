package com.che.architecture.atomic.design.bottom

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.che.architecture.atomic.design.tabs.BottomTab
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
@NonRestartableComposable
fun BottomNavigationBar(
    tabs: List<BottomTab>,
    currentTab: BottomTab,
    onClick: (BottomTab) -> Unit
) {
    NavigationBar(
        modifier = Modifier.height(66.dp)
    ) {
        tabs.forEachIndexed { index, tab ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(resource = tab.icon),
                        contentDescription = stringResource(resource = tab.title)
                    )
                },
                label = {
                    Text(text = stringResource(resource = tab.title))
                },
                selected = currentTab == tab,
                onClick = { onClick(tab) },
                modifier = Modifier.navigationBarsPadding()
            )
        }
    }
}
