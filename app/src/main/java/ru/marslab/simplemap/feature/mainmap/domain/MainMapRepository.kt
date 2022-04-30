package ru.marslab.simplemap.feature.mainmap.domain

import kotlinx.coroutines.flow.Flow
import ru.marslab.simplemap.common.model.MapMarker
import ru.marslab.simplemap.core.BaseRepository

interface MainMapRepository : BaseRepository {
    fun addMarker(marker: MapMarker): Flow<Boolean>
}
