package com.home.features.feature_start.presentation.state

import com.home.features.feature_start.domain.model.ServerStatus

sealed interface StartFragmentUiState {

    data object Loading : StartFragmentUiState

    data class Content(
        val serverStatus: ServerStatus,
    ) : StartFragmentUiState

    data object SlowServer : StartFragmentUiState

    data object ServerUnavailable : StartFragmentUiState

    data object NoInternet : StartFragmentUiState

    data object UnknownError : StartFragmentUiState

}