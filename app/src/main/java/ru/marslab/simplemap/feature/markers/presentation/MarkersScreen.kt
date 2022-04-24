package ru.marslab.simplemap.feature.markers.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.map.PlacemarkMapObject

class MarkersScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        val viewModel = getViewModel<MarkersViewModel>()
        MarkersView(viewModel)
    }
}

@Composable
private fun MarkersView(viewModel: MarkersViewModel) {
    val state = viewModel.state.collectAsState()
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            items(items = state.value.markers) {
                MarkerItem(it)
            }
        }
    }
}

@Composable
private fun MarkerItem(marker: PlacemarkMapObject) {
}

@Preview(showBackground = true)
@Composable
fun MarkerItemPreview() {
    val marker = MapKitFactory.getInstance()
}
