package ru.marslab.simplemap.feature.mainmap.domain

import com.yandex.mapkit.geometry.Point
import kotlinx.coroutines.flow.Flow
import ru.marslab.simplemap.core.BaseRepository
import ru.marslab.simplemap.feature.mainmap.domain.model.MapMarker

interface MainMapRepository : BaseRepository {
    fun getMarker(location: Point): Flow<MapMarker>
    fun addMarker(marker: MapMarker): Flow<Unit>
    fun updateMarker(marker: MapMarker): Flow<Boolean>
    fun removeMarker(marker: MapMarker): Flow<Unit>
}
