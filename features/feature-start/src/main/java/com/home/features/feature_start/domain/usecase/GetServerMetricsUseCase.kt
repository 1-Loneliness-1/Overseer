package com.home.features.feature_start.domain.usecase

import kotlinx.coroutines.flow.Flow

fun interface GetServerMetricsUseCase {

    fun getServerMetrics(): Flow<String>

}