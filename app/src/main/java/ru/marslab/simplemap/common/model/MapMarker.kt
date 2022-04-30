package ru.marslab.simplemap.common.model

import com.yandex.mapkit.geometry.Point

data class MapMarker(
    val name: String = "",
    val annotation: String = "",
    val location: Point = Point(0.0, 0.0)
)
