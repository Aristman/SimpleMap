package ru.marslab.simplemap.feature.mainmap.data

import com.yandex.mapkit.geometry.Point
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import ru.marslab.simplemap.common.AppDataStorage
import ru.marslab.simplemap.feature.mainmap.domain.MainMapRepository
import ru.marslab.simplemap.feature.mainmap.domain.model.MapMarker
import javax.inject.Inject

class MainMapDataRepository @Inject constructor(
    private val appDataStorage: AppDataStorage,
    override val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : MainMapRepository {
    override fun getMarker(location: Point): Flow<MapMarker> =
        appDataStorage.getMapMarker(location)
            .flowOn(dispatcher)

    override fun addMarker(marker: MapMarker): Flow<Unit> =
        appDataStorage.addNewMapMarker(marker)
            .flowOn(dispatcher)

    override fun updateMarker(marker: MapMarker): Flow<Boolean> =
        appDataStorage.updateMapMarker(marker)
            .flowOn(dispatcher)

    override fun removeMarker(marker: MapMarker): Flow<Unit> =
        appDataStorage.removeMapMarker(marker)
            .flowOn(dispatcher)
}
