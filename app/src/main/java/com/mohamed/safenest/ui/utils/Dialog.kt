package com.mohamed.safenest.ui.utils

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mohamed.safenest.R
import com.mohamed.safenest.ui.theme.colors

@Composable
fun ErrorDialog(
    errorState: MutableIntState,
    modifier: Modifier = Modifier,
    onRetryClick: () -> Unit?,
) {

    if (errorState.intValue != R.string.empty) {

        AlertDialog(
            onDismissRequest = {
                errorState.intValue = R.string.empty
            },

            confirmButton = {
                TextButton(onClick = {
                    onRetryClick
                    R.string.empty
                }) {
                    Text(
                        stringResource(R.string.retry),
                        color = colors.LightBlue
                    )
                }

            },
            dismissButton = {
                TextButton(onClick = {
                    errorState.intValue = R.string.empty
                }) {
                    Text(
                        stringResource(R.string.dismiss),
                        color = colors.LightBlue
                    )
                }
            }, text = {
                Text(text = stringResource(id = R.string.error))
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ErrorDialogPrev() {
    val errorState = remember { mutableIntStateOf(R.string.error) }

    ErrorDialog(errorState = errorState) {}
}