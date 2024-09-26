package com.che.architecture.atomic.design.molecules

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import com.che.architecture.atomic.design.foundation.ArchitectureTheme
import com.che.architecture.atomic.design.foundation.dimension.LocalPadding
import com.che.architecture.atomic.design.resources.Res
import com.che.architecture.atomic.design.resources.error
import com.che.architecture.atomic.design.resources.ic_error
import com.che.architecture.atomic.design.resources.somethingWentWrong
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = vectorResource(resource = Res.drawable.ic_error),
            contentDescription = stringResource(resource = Res.string.error),
            tint = Color.Red
        )
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Red
                    )
                ) {
                    append(stringResource(resource = Res.string.somethingWentWrong))
                }
            },
            modifier = modifier.padding(
                LocalPadding.current.l
            )
        )
    }
}

@Composable
private fun ErrorScreenPreview() {
    ArchitectureTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            ErrorScreen()
        }
    }
}
