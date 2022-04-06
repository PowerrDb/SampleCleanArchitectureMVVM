package com.bestpractises.razisample.util.extension

sealed class ResultData<out T> {
    data class Success<out T>(val data: T) : ResultData<T>()
    data class Loading(val isLoading: Boolean) : ResultData<Nothing>()
    data class Failed(val message: String) : ResultData<Nothing>()
    data class Exception(val exception: kotlin.Exception) : ResultData<Nothing>()
}

inline fun <reified T> ResultData<T>.doIfSuccess(callback: (data: T) -> Unit) {
    if (this is ResultData.Success) {
        callback(data)
    }
}

inline fun <reified T> ResultData<T>.doIfLoading(callback: (isLoading: Boolean) -> Unit) {
    if (this is ResultData.Loading) {
        callback(isLoading)
    }
}

inline fun <reified T> ResultData<T>.doIfFailed(callback: (message: String) -> Unit) {
    if (this is ResultData.Failed) {
        callback(message)
    }
}

inline fun <reified T> ResultData<T>.doIfException(callback: (exception: Exception) -> Unit) {
    if (this is ResultData.Exception) {
        callback(exception)
    }
}



