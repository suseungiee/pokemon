package com.seungsu.pokemon.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

abstract class MVIViewModel<I: ViewIntent, S: ViewState, E: ViewEffect>: ViewModel() {
    abstract fun createInitialState(): S

    private val initialState: S by lazy { createInitialState() }

    private val _intent: MutableSharedFlow<I> = MutableSharedFlow()
    val intent: SharedFlow<I> = _intent.asSharedFlow()

    private val _state: MutableStateFlow<S> = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state.asStateFlow()

    private val _effect: MutableSharedFlow<E> = MutableSharedFlow()
    val effect: SharedFlow<E> = _effect.asSharedFlow()

    val _toastEffect: MutableSharedFlow<String> = MutableSharedFlow()
    val toastEffect: SharedFlow<String> = _toastEffect.asSharedFlow()

    val _loadingEffect: MutableSharedFlow<Boolean> = MutableSharedFlow()
    val loadingEffect: SharedFlow<Boolean> = _loadingEffect.asSharedFlow()

    private val refreshSignal: MutableSharedFlow<SignalState> = MutableSharedFlow()

    protected val loadDataSignal: Flow<SignalState> = flow {
        emit(SignalState.INITIALIZE)
        emitAll(refreshSignal)
    }

    init {
        viewModelScope.launch {
            intent.collect { proecessIntent(it) }
        }
    }

    protected fun onRefresh() {
        viewModelScope.launch {
            refreshSignal.emit(SignalState.REFRESH)
        }
    }

    fun dispatch(intent: I) {
        viewModelScope.launch { _intent.emit(intent) }
    }

    abstract fun proecessIntent(intent: I)

    protected fun setState(action: S.() -> S) {
        val result = state.value.action()
        _state.value = result
    }

    protected fun setEffect(effect: E) {
        viewModelScope.launch { _effect.emit(effect) }
    }

    protected fun setToastEffect(message: String) {
        viewModelScope.launch { _toastEffect.emit(message) }
    }

    protected fun setLoadingEffect(isLoading: Boolean) {
        viewModelScope.launch { _loadingEffect.emit(isLoading) }
    }

    protected inline fun <reified S: ViewState> currentStateIf(action: S.() -> Unit) {
        val currentState = state.value
        if (currentState is S) {
            currentState.action()
        }
    }

    protected inline fun <reified T> currentState(action: S.() -> T): T {
        val currentState = state.value
        return currentState.action()
    }
}
