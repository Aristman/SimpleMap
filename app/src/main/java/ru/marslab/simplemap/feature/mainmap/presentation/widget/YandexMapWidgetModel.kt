package ru.marslab.simplemap.feature.mainmap.presentation.widget

import com.yandex.mapkit.Animation
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.Map
import ru.marslab.simplemap.core.BaseWidgetModel

data class YandexMapState(
    val map: Map? = null,
    val position: CameraPosition = CameraPosition(Point(0.0, 0.0), 10f, 0f, 0f)
)

class YandexMapWidgetModel : BaseWidgetModel<YandexMapState>(YandexMapState()) {

    internal fun setMap(map: Map) {
        setState { state.value.copy(map = map) }
    }

    internal fun setCameraPosition(cameraPosition: CameraPosition) {
        setState { state.value.copy(position = cameraPosition) }
    }

    internal fun setLocation(
        location: Point? = null,
        zoom: Float? = null,
        azimuth: Float? = null,
        tilt: Float? = null
    ) {
        val oldPosition = state.value.position
        setState {
            state.value.copy(
                position = CameraPosition(
                    location ?: oldPosition.target,
                    zoom ?: oldPosition.zoom,
                    azimuth ?: oldPosition.azimuth,
                    tilt ?: oldPosition.tilt
                )
            )
        }
        state.value.map?.move(state.value.position, Animation(Animation.Type.SMOOTH, 0.5f), null)
    }
}
