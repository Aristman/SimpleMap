package ru.marslab.simplemap.feature.mainmap.presentation.widget

import androidx.annotation.StringRes
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.marslab.simplemap.R

@Composable
fun ConfirmDialog(
    @StringRes message: Int,
    result: (result: Boolean) -> Unit
) {
    AlertDialog(
        onDismissRequest = { result(false) },
        confirmButton = {
            Button(onClick = { result(true) }) {
                Text(text = stringResource(id = R.string.ok))
            }
        },
        dismissButton = {
            Button(onClick = { result(false) }) {
                Text(text = stringResource(id = R.string.cancel))
            }
        },
        text = { Text(text = stringResource(id = message)) }
    )
}
