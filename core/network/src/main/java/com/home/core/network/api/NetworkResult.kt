package com.home.core.network.api

sealed interface NetworkResult<out T> {

    data class Success<T>(
        val data: T,
    ) : NetworkResult<T>

    data object NoConnectionError : NetworkResult<Nothing>

    data object TimeoutError : NetworkResult<Nothing>

    data class HttpError(
        val code: Int,
        val message: String?,
    ) : NetworkResult<Nothing>

    data object SerializationError : NetworkResult<Nothing>

    data class UnknownError(
        val throwable: Throwable,
    ) : NetworkResult<Nothing>

}