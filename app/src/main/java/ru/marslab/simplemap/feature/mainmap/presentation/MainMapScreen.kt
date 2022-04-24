package ru.marslab.simplemap.feature.mainmap.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import ru.marslab.simplemap.R
import ru.marslab.simplemap.feature.mainmap.presentation.model.MainMapAction
import ru.marslab.simplemap.feature.mainmap.presentation.widget.ConfirmDialog
import ru.marslab.simplemap.feature.mainmap.presentation.widget.YandexMapWidget

class MainMapScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        val viewModel = getViewModel<MainMapViewModel>()
        viewModel.setNavigator(LocalNavigator.currentOrThrow)
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
    Box(modifier = Modifier.fillMaxSize()) {
        YandexMapWidget(
            widgetModel = state.value.mapWidgetModel,
            onLongClick = {
                viewModel.sendAction(MainMapAction.MapLongClick(it))
            }
        )
        FloatingActionButton(
            modifier = Modifier.align(Alignment.BottomEnd),
            onClick = { viewModel.sendAction(MainMapAction.OpenMarkersList) }
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_edit_location_24),
                contentDescription = null
            )
        }
    }
}
