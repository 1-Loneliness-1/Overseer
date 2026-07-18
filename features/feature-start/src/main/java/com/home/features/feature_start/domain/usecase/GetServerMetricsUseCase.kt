package com.home.features.feature_start.domain.usecase

import com.home.features.feature_start.presentation.state.StartFragmentUiState

interface GetServerMetricsUseCase {

    suspend fun getServerMetrics(): StartFragmentUiState

}