package ru.marslab.simplemap.feature.mainmap.presentation

import ru.marslab.simplemap.core.BaseViewModel
import ru.marslab.simplemap.feature.mainmap.presentation.model.MainMapAction
import ru.marslab.simplemap.feature.mainmap.presentation.model.MainMapState

class MainMapViewModel : BaseViewModel<MainMapState, MainMapAction, Nothing>(MainMapState()) {

    override fun reduceState(currentState: MainMapState, action: MainMapAction): MainMapState {
        TODO("Not yet implemented")
    }
}
