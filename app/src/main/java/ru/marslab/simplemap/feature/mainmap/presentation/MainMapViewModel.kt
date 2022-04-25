package ru.marslab.simplemap.feature.mainmap.presentation

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import ru.marslab.simplemap.common.model.MapMarker
import ru.marslab.simplemap.core.BaseViewModel
import ru.marslab.simplemap.feature.mainmap.domain.interactor.AddNewMapMarkerInteractor
import ru.marslab.simplemap.feature.mainmap.presentation.model.MainMapAction
import ru.marslab.simplemap.feature.mainmap.presentation.model.MainMapEvent
import ru.marslab.simplemap.feature.mainmap.presentation.model.MainMapState
import ru.marslab.simplemap.feature.mainmap.presentation.model.toPoint
import ru.marslab.simplemap.feature.markers.domain.interactor.GetMapMarkersInteractor
import ru.marslab.simplemap.feature.markers.presentation.MarkersScreen
import javax.inject.Inject

@HiltViewModel
class MainMapViewModel @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val addNewMapMarker: AddNewMapMarkerInteractor,
    private val getMapMarkers: GetMapMarkersInteractor
) : BaseViewModel<MainMapState, MainMapAction, MainMapEvent>(MainMapState()) {

    init {
        launch {
            dataStore.data
                .map { it.toPoint() }
                .collectLatest {
                    state.value.mapWidgetModel.setLocation(it)
                }
        }
    }

    override fun reduceStateByAction(
        currentState: MainMapState,
        action: MainMapAction
    ): MainMapState =
        when (action) {
            is MainMapAction.MapLongClick -> currentState.copy(
                isShowAddMarkerConfirmDialog = true,
                newMarkerPoint = action.clickPoint
            )
            MainMapAction.CloseAddMarkerConfirmDialog -> currentState.copy(
                isShowAddMarkerConfirmDialog = false
            )
            MainMapAction.OpenMarkersList -> {
                navigator.push(MarkersScreen())
                currentState
            }
        }

    fun updateMap() {
        launch {
            getMapMarkers()
                .catch { handleError(it) }
                .collectLatest { markers ->
                    val yandexMap = state.value.mapWidgetModel.state.value.map
                    val position = state.value.mapWidgetModel.state.value.position
                    reduceState {
                        state.value.copy(
                            mapMarkers = markers.mapNotNull {
                                yandexMap?.mapObjects?.addPlacemark(
                                    it.location
                                )
                            }
                        )
                    }
                    state.value.mapWidgetModel.setLocation(
                        location = position.target,
                        zoom = position.zoom
                    )
                }
        }
    }

    fun addNewMarker() {
        val newMarkerPoint = state.value.newMarkerPoint
        val mapMarkers = state.value.mapMarkers.toMutableList()
        val yandexMap = state.value.mapWidgetModel.state.value.map
        launch {
            addNewMapMarker(MapMarker("", "", newMarkerPoint))
                .catch { handleError(it) }
                .collect {
                    yandexMap?.let { map ->
                        reduceState {
                            state.value.copy(
                                mapMarkers = mapMarkers.apply {
                                    add(map.mapObjects.addPlacemark(newMarkerPoint))
                                }.toList()
                            )
                        }
                    }
                }
        }
    }
}
