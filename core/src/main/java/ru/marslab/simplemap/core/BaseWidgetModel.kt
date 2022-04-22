package ru.marslab.simplemap.core

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseWidgetModel<D>(initValue: D) {
    private val _state = MutableStateFlow(initValue)
    val state: StateFlow<D> = _state.asStateFlow()

    fun setState(state: () -> D) {
        _state.value = state.invoke()
    }
}
