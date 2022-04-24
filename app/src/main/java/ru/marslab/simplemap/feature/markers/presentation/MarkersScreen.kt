package ru.marslab.simplemap.feature.markers.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import ru.marslab.simplemap.common.model.MapMarker
import ru.marslab.simplemap.feature.markers.presentation.model.MarkersAction

class MarkersScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        val viewModel = getViewModel<MarkersViewModel>()
        viewModel.setNavigator(LocalNavigator.currentOrThrow)
        MarkersView(viewModel)
    }
}

@Composable
private fun MarkersView(viewModel: MarkersViewModel) {
    val state = viewModel.state.collectAsState()
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            items(items = state.value.markers) {
                MarkerItem(it) {
                    viewModel.sendAction(MarkersAction.MarkerOnClick(it))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun MarkerItem(marker: MapMarker, onClick: () -> Unit) {
    Card(shape = RoundedCornerShape(16.dp), onClick = onClick) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = marker.name)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = marker.annotation)
            Divider()
            Spacer(modifier = Modifier.height(8.dp))
            Row(Modifier.fillMaxWidth()) {
                Text(
                    text = marker.location.latitude.toString(),
                    modifier = Modifier.weight(0.5f),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = marker.location.longitude.toString(),
                    modifier = Modifier.weight(0.5f),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
