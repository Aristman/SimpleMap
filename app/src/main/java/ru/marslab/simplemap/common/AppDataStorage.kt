package ru.marslab.simplemap.common

import com.yandex.mapkit.geometry.Point
import kotlinx.coroutines.flow.Flow
import ru.marslab.simplemap.common.model.MapMarker

interface AppDataStorage {
    fun getMapMarkers(local: Boolean = true): Flow<List<MapMarker>>
    fun getMapMarker(location: Point, local: Boolean = true): Flow<MapMarker?>
    fun addNewMapMarker(mapMarker: MapMarker, local: Boolean = true): Flow<Boolean>
    fun updateMapMarker(
        mapMarker: MapMarker,
        name: String?,
        annotation: String?,
        local: Boolean = true
    ): Flow<Boolean>

    fun removeMapMarker(mapMarker: MapMarker, local: Boolean = true): Flow<Boolean>
}
