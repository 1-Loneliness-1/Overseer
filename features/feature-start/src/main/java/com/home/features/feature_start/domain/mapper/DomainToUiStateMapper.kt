package com.home.features.feature_start.domain.mapper

import com.home.features.feature_start.domain.model.AppError
import com.home.features.feature_start.domain.model.Result
import com.home.features.feature_start.domain.model.ServerStatus
import com.home.features.feature_start.presentation.state.StartFragmentUiState

fun <T> Result<T>.convertToUiStateObj(): StartFragmentUiState {
    return when (this) {

        is Result.Success -> {

            StartFragmentUiState.Content(this.data as ServerStatus)

        }

        is Result.Failure -> {

            when (this.error) {

                is AppError.ServerTooSlow -> StartFragmentUiState.SlowServer

                is AppError.ServerUnavailable -> StartFragmentUiState.ServerUnavailable

                is AppError.NoInternet -> StartFragmentUiState.NoInternet

                is AppError.Unknown -> StartFragmentUiState.UnknownError
            }

        }

    }
}