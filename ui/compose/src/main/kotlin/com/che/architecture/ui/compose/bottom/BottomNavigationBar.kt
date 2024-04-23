package com.che.architecture.ui.compose.bottom

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.che.architecture.ui.compose.tabs.BottomTab
import java.util.Locale

@Composable
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
                        painter = painterResource(id = tab.icon),
                        contentDescription = stringResource(id = tab.title)
                    )
                },
                label = {
                    Text(text = stringResource(id = tab.title).uppercase(Locale.getDefault()))
                },
                selected = currentTab == tab,
                onClick = { onClick(tab) },
                modifier = Modifier.navigationBarsPadding()
            )
        }
    }
}
