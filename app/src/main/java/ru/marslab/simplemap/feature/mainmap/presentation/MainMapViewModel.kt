package ru.marslab.simplemap.feature.mainmap.presentation

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import ru.marslab.simplemap.core.BaseViewModel
import ru.marslab.simplemap.feature.mainmap.presentation.model.MainMapAction
import ru.marslab.simplemap.feature.mainmap.presentation.model.MainMapState
import ru.marslab.simplemap.feature.mainmap.presentation.model.toPoint
import javax.inject.Inject

@HiltViewModel
class MainMapViewModel @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : BaseViewModel<MainMapState, MainMapAction, Nothing>(MainMapState()) {

    init {
        launch {
            dataStore.data
                .map { it.toPoint() }
                .collectLatest {
                    state.value.mapWidgetModel.setLocation(it)
                }
        }
    }

    override fun reduceState(currentState: MainMapState, action: MainMapAction): MainMapState {
        TODO("Not yet implemented")
    }
}
