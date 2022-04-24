package ru.marslab.simplemap.feature.mainmap.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import ru.marslab.simplemap.R
import ru.marslab.simplemap.feature.mainmap.presentation.model.MainMapAction
import ru.marslab.simplemap.feature.mainmap.presentation.widget.ConfirmDialog
import ru.marslab.simplemap.feature.mainmap.presentation.widget.YandexMapWidget

class MainMapScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        val viewModel = getViewModel<MainMapViewModel>()
        MainMapView(viewModel)
    }
}

@Composable
private fun MainMapView(viewModel: MainMapViewModel) {
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
