package com.home.features.feature_start.domain.model

sealed interface AppError {

    data object NoInternet : AppError

    data object ServerUnavailable : AppError

    data object ServerTooSlow : AppError

    data class Unknown(
        val throwable: Throwable
    ) : AppError

}