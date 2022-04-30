package ru.marslab.simplemap.feature.mainmap.presentation.widget

import com.yandex.mapkit.Animation
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.Map
import ru.marslab.simplemap.core.BaseWidgetModel

private const val ANIMATE_DURATION = 0.5f
private const val START_ZOOM = 10f
private const val START_AZIMUTH = 0f
private const val START_TILT = 0f
private val START_LOCATION = Point(0.0, 0.0)

data class YandexMapState(
    val map: Map? = null,
    val position: CameraPosition = CameraPosition(
        START_LOCATION,
        START_ZOOM,
        START_AZIMUTH,
        START_TILT
    )
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
        state.value.map?.move(
            state.value.position,
            Animation(Animation.Type.SMOOTH, ANIMATE_DURATION),
            null
        )
    }
}
