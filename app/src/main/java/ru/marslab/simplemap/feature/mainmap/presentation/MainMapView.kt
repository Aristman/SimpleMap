package ru.marslab.simplemap.feature.mainmap.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.marslab.simplemap.feature.mainmap.presentation.widget.YandexMapWidget

@Composable
fun MainMapView(viewModel: MainMapViewModel = viewModel()) {
    val state = viewModel.state.collectAsState()
    YandexMapWidget(widgetModel = state.value.mapWidgetModel)
}
