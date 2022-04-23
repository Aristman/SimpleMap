package ru.marslab.simplemap.feature.mainmap.presentation.model

import com.yandex.mapkit.geometry.Point
import ru.marslab.simplemap.core.Action

sealed class MainMapAction : Action {
    data class MapLongClick(val clickPoint: Point) : MainMapAction()
    object CloseAddMarkerConfirmDialog : MainMapAction()
}
