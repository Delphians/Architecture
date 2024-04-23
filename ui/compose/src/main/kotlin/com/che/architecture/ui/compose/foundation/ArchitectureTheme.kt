package com.che.architecture.ui.compose.foundation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.che.architecture.ui.compose.foundation.dimension.LocalPadding
import com.che.architecture.ui.compose.foundation.dimension.Padding

internal val typography = Typography()
internal val shapes = Shapes()

@Composable
fun ArchitectureTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (!useDarkTheme) {
        lightColorScheme()
    } else {
        darkColorScheme()
    }
    CompositionLocalProvider(
        LocalPadding provides Padding(),
    ) {
        MaterialTheme(
            colorScheme = colors,
            typography = typography,
            shapes = shapes,
            content = content
        )
    }
}
