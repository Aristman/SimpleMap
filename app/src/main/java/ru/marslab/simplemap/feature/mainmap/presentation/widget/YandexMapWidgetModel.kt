package ru.marslab.simplemap.feature.mainmap.presentation.widget

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
}
