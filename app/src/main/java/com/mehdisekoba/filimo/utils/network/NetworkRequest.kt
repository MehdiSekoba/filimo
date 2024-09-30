package com.mehdisekoba.filimo.utils.network

sealed class NetworkRequest<T>(val data: T? = null, val error: String? = null) {
    class Loading<T> : NetworkRequest<T>()
    class Success<T>(data: T) : NetworkRequest<T>(data)
    class Error<T>(message: String) : NetworkRequest<T>(error = message)
}