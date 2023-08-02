package com.fikrisandi.framework.base

sealed interface BaseUiState<out T>{
    object Empty: BaseUiState<Nothing>
    object Loading: BaseUiState<Nothing>
    data class Data<T>(val value: T): BaseUiState<T>
    data class Error(val throwable: Throwable): BaseUiState<Nothing>
}

