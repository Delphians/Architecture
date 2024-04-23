package com.che.architecture.ui.compose.molecules

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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.sp
import com.che.architecture.ui.compose.R
import com.che.architecture.ui.compose.foundation.ArchitectureTheme
import com.che.architecture.ui.compose.foundation.dimension.LocalPadding

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
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_error),
            contentDescription = stringResource(id = R.string.error),
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
                    append(stringResource(id = R.string.somethingWentWrong))
                }
            },
            modifier = modifier.padding(
                LocalPadding.current.l
            )
        )
    }
}

@PreviewLightDark
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
