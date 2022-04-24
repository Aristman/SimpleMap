package ru.marslab.simplemap.feature.markers.presentation

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.marslab.simplemap.core.BaseViewModel
import ru.marslab.simplemap.feature.markers.presentation.model.MarkersAction
import ru.marslab.simplemap.feature.markers.presentation.model.MarkersEvent
import ru.marslab.simplemap.feature.markers.presentation.model.MarkersState
import javax.inject.Inject

@HiltViewModel
class MarkersViewModel @Inject constructor() : BaseViewModel<MarkersState, MarkersAction, MarkersEvent>(MarkersState()) {
    override fun reduceState(currentState: MarkersState, action: MarkersAction): MarkersState {
        TODO("Not yet implemented")
    }
}
