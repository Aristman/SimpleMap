package ru.marslab.simplemap.core

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cafe.adriel.voyager.navigator.Navigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

interface Event

interface Action

private const val ERROR_LOG_TAG = "ViewModel Error"

abstract class BaseViewModel<ST, AC : Action, EV : Event>(initState: ST) : ViewModel() {
    private val _state = MutableStateFlow(initState)
    val state: StateFlow<ST>
        get() = _state.asStateFlow()

    private val action = MutableSharedFlow<AC>()

    private val _event = Channel<EV?>(capacity = Channel.CONFLATED)
    val event: Flow<EV?>
        get() = _event.receiveAsFlow()

    private var _navigator: Navigator? = null
    val navigator: Navigator
        get() = checkNotNull(_navigator)

    init {
        this.action
            .onEach { _state.tryEmit(reduceStateByAction(state.value, it)) }
            .stateIn(viewModelScope, SharingStarted.Eagerly, initState)
    }

    fun setNavigator(navigator: Navigator) {
        this._navigator = navigator
    }

    fun sendAction(action: AC) {
        launch {
            this@BaseViewModel.action.emit(action)
        }
    }

    protected fun launch(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch {
            block.invoke(this)
        }
    }

    protected open fun handleError(error: Throwable, message: String? = null) {
        Log.d(ERROR_LOG_TAG, message ?: error.message.orEmpty(), error)
    }

    protected fun sendEvent(event: EV) {
        launch {
            _event.send(event)
        }
    }

    protected fun clearEvent() {
        launch {
            _event.send(null)
        }
    }

    protected fun reduceState(reduceBlock: () -> ST) {
        _state.tryEmit(reduceBlock())
    }

    protected abstract fun reduceStateByAction(currentState: ST, action: AC): ST
}
