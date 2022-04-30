package ru.marslab.simplemap.feature.markers.domain.interactor

import kotlinx.coroutines.flow.Flow
import ru.marslab.simplemap.common.model.MapMarker
import ru.marslab.simplemap.feature.markers.domain.MarkersRepository
import javax.inject.Inject

class UpdateMapMarkerInteractor @Inject constructor(
    private val repository: MarkersRepository
) {
    operator fun invoke(mapMarker: MapMarker, name: String, annotation: String): Flow<Boolean> =
        repository.updateMarker(marker = mapMarker, name = name, annotation = annotation)
}
