package ru.marslab.simplemap.feature.markers.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import ru.marslab.simplemap.common.AppDataStorage
import ru.marslab.simplemap.common.model.MapMarker
import ru.marslab.simplemap.feature.markers.domain.MarkersRepository
import javax.inject.Inject

class MarkersDataRepository @Inject constructor(
    private val appDataStorage: AppDataStorage,
    override val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : MarkersRepository {
    override fun getMarkers(): Flow<List<MapMarker>> =
        appDataStorage.getMapMarkers()
            .flowOn(dispatcher)

    override fun updateMarker(
        marker: MapMarker,
        name: String?,
        annotation: String?
    ): Flow<Boolean> =
        appDataStorage.updateMapMarker(mapMarker = marker, name = name, annotation = annotation)
            .flowOn(dispatcher)

    override fun removeMarker(marker: MapMarker): Flow<Boolean> =
        appDataStorage.removeMapMarker(marker)
            .flowOn(dispatcher)
}
