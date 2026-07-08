package com.home.features.feature_start.domain.repository

import com.home.features.feature_start.domain.model.ServerStatus
import kotlinx.coroutines.flow.Flow

interface ServerStatusRepository {

    fun getServerStatus(): Flow<ServerStatus>

}