package com.fikrisandi.framework.base

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class MvviViewModel<STATE : BaseUiState<*>, EVENT> : MvvmViewModel() {

    private val _uiState = MutableStateFlow<BaseUiState<*>>(BaseUiState.Empty)
    val uiState = _uiState.asStateFlow()

    abstract fun onTrigger(eventType: EVENT)

    protected fun setState(state: STATE) = safeLaunch {
        _uiState.emit(state)
    }

    override fun startLoading() {
        super.startLoading()
        _uiState.update { BaseUiState.Loading }
    }

    override fun handleError(exception: Throwable) {
        super.handleError(exception)
        _uiState.update { BaseUiState.Error(exception) }
    }

    override fun setEmpty() {
        super.setEmpty()
        _uiState.update { BaseUiState.Empty }
    }

}