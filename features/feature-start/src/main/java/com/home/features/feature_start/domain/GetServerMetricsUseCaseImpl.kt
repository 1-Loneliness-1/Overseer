package com.home.features.feature_start.domain

import com.home.features.feature_start.domain.model.Result
import com.home.features.feature_start.domain.model.ServerStatus
import com.home.features.feature_start.domain.repository.ServerStatusRepository
import com.home.features.feature_start.domain.usecase.GetServerMetricsUseCase

import javax.inject.Inject

class GetServerMetricsUseCaseImpl @Inject constructor(
    private val serverStatusRepository: ServerStatusRepository,
) : GetServerMetricsUseCase {

    override suspend fun getServerMetrics(): Result<ServerStatus> {
        return serverStatusRepository.getServerStatus()
    }

}