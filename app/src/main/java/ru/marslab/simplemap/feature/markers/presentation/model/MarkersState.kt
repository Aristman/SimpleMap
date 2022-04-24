package ru.marslab.simplemap.feature.markers.presentation.model

import ru.marslab.simplemap.common.model.MapMarker

data class MarkersState(
    val markers: List<MapMarker> = emptyList()
)
