package com.home.core.model.api

sealed interface NetworkResult<out T> {

    data class Success<T>(
        val data: T,
    ) : NetworkResult<T>

    data class HttpError(
        val code: Int,
    ) : NetworkResult<Nothing>

    data object NetworkError : NetworkResult<Nothing>

    data object UnknownError : NetworkResult<Nothing>

}