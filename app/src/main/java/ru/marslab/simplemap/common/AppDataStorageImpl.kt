package ru.marslab.simplemap.common

import com.yandex.mapkit.geometry.Point
import kotlinx.coroutines.flow.Flow
import ru.marslab.simplemap.feature.mainmap.domain.model.MapMarker
import javax.inject.Inject

class AppDataStorageImpl @Inject constructor() : AppDataStorage {
    override fun getMapMarkers(local: Boolean): Flow<List<MapMarker>> {
        TODO("Not yet implemented")
    }

    override fun getMapMarker(location: Point, local: Boolean): Flow<MapMarker> {
        TODO("Not yet implemented")
    }

    override fun addNewMapMarker(mapMarker: MapMarker, local: Boolean): Flow<Unit> {
        TODO("Not yet implemented")
    }

    override fun updateMapMarker(mapMarker: MapMarker, local: Boolean): Flow<Boolean> {
        TODO("Not yet implemented")
    }

    override fun removeMapMarker(mapMarker: MapMarker, local: Boolean): Flow<Unit> {
        TODO("Not yet implemented")
    }
}
