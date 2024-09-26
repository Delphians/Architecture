package com.che.architecture.atomic.design.molecules

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.che.architecture.atomic.design.resources.Res
import com.che.architecture.atomic.design.resources.alertDialog
import com.che.architecture.atomic.design.resources.confirm
import com.che.architecture.atomic.design.resources.dismiss
import com.che.architecture.atomic.design.resources.ic_warning
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource

@Composable
fun Dialog(
    title: String,
    text: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        icon = {
            Icon(
                vectorResource(resource = Res.drawable.ic_warning),
                contentDescription = stringResource(resource = Res.string.alertDialog)
            )
        },
        title = {
            Text(text = title)
        },
        text = {
            Text(text = text)
        },
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = onConfirm
            ) {
                Text(stringResource(resource = Res.string.confirm))
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text(stringResource(resource = Res.string.dismiss))
            }
        }
    )
}
