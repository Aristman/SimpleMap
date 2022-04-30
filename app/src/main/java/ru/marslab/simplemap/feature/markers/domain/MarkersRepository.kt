package ru.marslab.simplemap.feature.markers.domain

import kotlinx.coroutines.flow.Flow
import ru.marslab.simplemap.common.model.MapMarker
import ru.marslab.simplemap.core.BaseRepository

interface MarkersRepository : BaseRepository {
    fun getMarkers(): Flow<List<MapMarker>>
    fun updateMarker(
        marker: MapMarker,
        name: String? = null,
        annotation: String? = null
    ): Flow<Boolean>

    fun removeMarker(marker: MapMarker): Flow<Boolean>
}
