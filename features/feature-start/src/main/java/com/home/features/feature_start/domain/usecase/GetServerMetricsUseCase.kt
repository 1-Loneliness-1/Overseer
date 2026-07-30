package com.home.features.feature_start.domain.usecase

import com.home.features.feature_start.domain.model.Result
import com.home.features.feature_start.domain.model.ServerStatus

interface GetServerMetricsUseCase {

    suspend fun getServerMetrics(): Result<ServerStatus>

}