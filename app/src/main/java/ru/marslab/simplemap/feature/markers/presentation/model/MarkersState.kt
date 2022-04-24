package ru.marslab.simplemap.feature.markers.presentation.model

import com.yandex.mapkit.map.PlacemarkMapObject

data class MarkersState(
    val markers: List<PlacemarkMapObject> = emptyList()
)
