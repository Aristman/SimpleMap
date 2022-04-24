package ru.marslab.simplemap.feature.markers.domain.interactor

import kotlinx.coroutines.flow.Flow
import ru.marslab.simplemap.common.model.MapMarker
import ru.marslab.simplemap.feature.markers.domain.MarkersRepository
import javax.inject.Inject

class GetMapMarkersInteractor @Inject constructor(
    private val repository: MarkersRepository
) {
    operator fun invoke(): Flow<List<MapMarker>> =
        repository.getMarkers()
}
