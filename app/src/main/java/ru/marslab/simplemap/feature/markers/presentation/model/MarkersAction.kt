package ru.marslab.simplemap.feature.markers.presentation.model

import ru.marslab.simplemap.common.model.MapMarker
import ru.marslab.simplemap.core.Action

sealed class MarkersAction : Action {
    data class MarkerOnClick(val marker: MapMarker) : MarkersAction()
    object CloseEditMarker : MarkersAction()
}
