package ru.marslab.simplemap.feature.mainmap.presentation.model

import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.PlacemarkMapObject
import ru.marslab.simplemap.feature.mainmap.presentation.widget.YandexMapWidgetModel

data class MainMapState(
    val mapWidgetModel: YandexMapWidgetModel = YandexMapWidgetModel(),
    val newMarkerPoint: Point = Point(0.0, 0.0),
    val isShowAddMarkerConfirmDialog: Boolean = false,
    val mapMarkers: List<PlacemarkMapObject> = emptyList()
)
