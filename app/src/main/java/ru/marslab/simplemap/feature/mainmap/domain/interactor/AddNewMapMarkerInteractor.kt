package ru.marslab.simplemap.feature.mainmap.domain.interactor

import kotlinx.coroutines.flow.Flow
import ru.marslab.simplemap.common.model.MapMarker
import ru.marslab.simplemap.feature.mainmap.domain.MainMapRepository
import javax.inject.Inject

class AddNewMapMarkerInteractor @Inject constructor(
    private val mainMapRepository: MainMapRepository
) {
    operator fun invoke(mapMarker: MapMarker): Flow<Boolean> =
        mainMapRepository.addMarker(mapMarker)
}
