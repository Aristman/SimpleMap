package ru.marslab.simplemap.feature.markers.presentation

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import ru.marslab.simplemap.core.BaseViewModel
import ru.marslab.simplemap.feature.markers.domain.interactor.GetMapMarkersInteractor
import ru.marslab.simplemap.feature.markers.domain.interactor.UpdateMapMarkerInteractor
import ru.marslab.simplemap.feature.markers.presentation.model.MarkersAction
import ru.marslab.simplemap.feature.markers.presentation.model.MarkersEvent
import ru.marslab.simplemap.feature.markers.presentation.model.MarkersState
import javax.inject.Inject

@HiltViewModel
class MarkersViewModel @Inject constructor(
    private val getMapMarkers: GetMapMarkersInteractor,
    private val updateMapMarker: UpdateMapMarkerInteractor
) : BaseViewModel<MarkersState, MarkersAction, MarkersEvent>(MarkersState()) {
    init {
        launch {
            getMapMarkers()
                .catch { handleError(it) }
                .collect {
                    reduceState {
                        state.value.copy(markers = it)
                    }
                }
        }
    }

    fun updateEditedMarker(name: String, annotation: String) {
        launch {
            updateMapMarker(state.value.editMarker, name, annotation)
                .catch { handleError(it) }
                .collectLatest {
                }
        }
    }

    override fun reduceStateByAction(
        currentState: MarkersState,
        action: MarkersAction
    ): MarkersState =
        when (action) {
            is MarkersAction.MarkerOnClick -> currentState.copy(
                isShowEditMarker = true,
                editMarker = action.marker
            )

            MarkersAction.CloseEditMarker -> currentState.copy(isShowEditMarker = false)
        }
}
