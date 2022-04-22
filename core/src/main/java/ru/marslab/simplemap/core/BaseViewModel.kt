package ru.marslab.simplemap.core

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

interface Event

interface Action

private const val ERROR_LOG_TAG = "ViewModel Error"

@OptIn(ExperimentalCoroutinesApi::class)
abstract class BaseViewModel<ST, AC : Action, EV : Event>(initState: ST) : ViewModel() {
    private val _state = MutableStateFlow(initState)
    val state: StateFlow<ST>
        get() = _state.asStateFlow()

    private val action = MutableSharedFlow<AC>(extraBufferCapacity = 1)

    private val _event = Channel<EV?>()
    val event: Flow<EV?>
        get() = _event.receiveAsFlow()

    init {
        this.action
            .scan(initState, ::reduceState)
            .onEach { _state.emit(it) }
            .stateIn(viewModelScope, SharingStarted.Eagerly, initState)
    }

    fun launch(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch {
            block.invoke(this)
        }
    }

    open fun handleError(error: Throwable, message: String? = null) {
        Log.d(ERROR_LOG_TAG, message ?: error.message.orEmpty(), error)
    }

    fun sendAction(action: AC) {
        this.action.tryEmit(action)
    }

    protected fun sendEvent(event: EV) {
        launch {
            _event.send(event)
        }
    }

    fun clearEvent() {
        launch {
            _event.send(null)
        }
    }

    protected fun reduceState(reduceBlock: () -> ST) {
        _state.tryEmit(reduceBlock())
    }

    protected abstract fun reduceState(currentState: ST, action: AC): ST
}
