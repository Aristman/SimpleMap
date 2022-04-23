package ru.marslab.simplemap.feature.mainmap.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.marslab.simplemap.R
import ru.marslab.simplemap.feature.mainmap.presentation.model.MainMapAction
import ru.marslab.simplemap.feature.mainmap.presentation.widget.ConfirmDialog
import ru.marslab.simplemap.feature.mainmap.presentation.widget.YandexMapWidget

@Composable
fun MainMapView(viewModel: MainMapViewModel = viewModel()) {
    val state = viewModel.state.collectAsState()
    if (state.value.isShowAddMarkerConfirmDialog) {
        ConfirmDialog(message = R.string.add_marker_dialog) {
            viewModel.sendAction(MainMapAction.CloseAddMarkerConfirmDialog)
            if (it) {
                viewModel.addNewMarker()
            }
        }
    }
    YandexMapWidget(
        widgetModel = state.value.mapWidgetModel,
        onLongClick = {
            viewModel.sendAction(MainMapAction.MapLongClick(it))
        }
    )
}
