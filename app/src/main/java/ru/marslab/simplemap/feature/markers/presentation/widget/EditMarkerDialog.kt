package ru.marslab.simplemap.feature.markers.presentation.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import ru.marslab.simplemap.R
import ru.marslab.simplemap.common.model.MapMarker

@Composable
fun EditMarkerDialog(
    mapMarker: MapMarker,
    resultDialog: ((result: Boolean, newMarker: MapMarker) -> Unit)
) {
    var name by remember {
        mutableStateOf(mapMarker.name)
    }
    var annotation by remember {
        mutableStateOf(mapMarker.annotation)
    }
    Dialog(onDismissRequest = { resultDialog(false, MapMarker()) }) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = stringResource(id = R.string.edit_marker_text))
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    label = { Text(text = stringResource(id = R.string.name)) }
                )
                TextField(
                    value = annotation,
                    onValueChange = { annotation = it },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    label = { Text(text = stringResource(id = R.string.annotation)) }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Divider()
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Button(onClick = { resultDialog(false, MapMarker()) }) {
                        Text(text = stringResource(id = R.string.cancel))
                    }
                    Button(onClick = {
                        resultDialog(
                            true,
                            MapMarker(
                                name = name,
                                annotation = annotation,
                                location = mapMarker.location
                            )
                        )
                    }) {
                        Text(text = stringResource(id = R.string.ok))
                    }
                }
            }
        }
    }
}
