package ru.marslab.simplemap.feature.mainmap.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import ru.marslab.simplemap.common.AppDataStorage
import ru.marslab.simplemap.common.model.MapMarker
import ru.marslab.simplemap.feature.mainmap.domain.MainMapRepository
import javax.inject.Inject

class MainMapDataRepository @Inject constructor(
    private val appDataStorage: AppDataStorage,
    override val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : MainMapRepository {
    override fun addMarker(marker: MapMarker): Flow<Boolean> =
        appDataStorage.addNewMapMarker(marker)
            .flowOn(dispatcher)
}
