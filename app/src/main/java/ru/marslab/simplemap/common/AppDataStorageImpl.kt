package ru.marslab.simplemap.common

import com.yandex.mapkit.geometry.Point
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.marslab.simplemap.common.model.MapMarker
import javax.inject.Inject

class AppDataStorageImpl @Inject constructor() : AppDataStorage {
    private val mapMarkers: MutableList<MapMarker> = mutableListOf()

    override fun getMapMarkers(local: Boolean): Flow<List<MapMarker>> = flow {
        emit(mapMarkers)
    }

    override fun getMapMarker(location: Point, local: Boolean): Flow<MapMarker?> = flow {
        emit(mapMarkers.find { it.location == location })
    }

    override fun addNewMapMarker(mapMarker: MapMarker, local: Boolean): Flow<Boolean> = flow {
        emit(mapMarkers.add(mapMarker))
    }

    override fun updateMapMarker(
        mapMarker: MapMarker,
        name: String?,
        annotation: String?,
        local: Boolean
    ): Flow<Boolean> = flow {
        kotlin.runCatching {
            mapMarkers[mapMarkers.indexOf(mapMarker)] = mapMarker.copy(
                name = name ?: mapMarker.name,
                annotation = name ?: mapMarker.annotation
            )
        }
            .onSuccess { emit(true) }
            .onFailure { emit(false) }
    }

    override fun removeMapMarker(mapMarker: MapMarker, local: Boolean): Flow<Boolean> = flow {
        emit(mapMarkers.remove(mapMarker))
    }
}
