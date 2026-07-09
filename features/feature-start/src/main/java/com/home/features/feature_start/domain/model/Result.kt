package com.home.features.feature_start.domain.model

sealed interface Result<out T> {

    data class Success<T>(
        val data: T,
    ) : Result<T>

    data class Failure(
        val error: AppError,
    ) : Result<Nothing>

}