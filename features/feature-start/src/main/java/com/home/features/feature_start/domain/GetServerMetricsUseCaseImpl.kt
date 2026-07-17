package com.home.features.feature_start.domain

import com.home.features.feature_start.domain.mapper.convertToUiStateObj
import com.home.features.feature_start.domain.repository.ServerStatusRepository
import com.home.features.feature_start.domain.usecase.GetServerMetricsUseCase
import com.home.features.feature_start.presentation.state.StartFragmentUiState
import javax.inject.Inject

class GetServerMetricsUseCaseImpl @Inject constructor(
    private val serverStatusRepository: ServerStatusRepository,
) : GetServerMetricsUseCase {

    override suspend fun getServerMetrics(): StartFragmentUiState {
        return serverStatusRepository.getServerStatus().convertToUiStateObj()
    }

}