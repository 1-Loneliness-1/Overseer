package com.home.features.feature_start.domain.repository

import com.home.features.feature_start.domain.model.Result
import com.home.features.feature_start.domain.model.ServerStatus

interface ServerStatusRepository {

    suspend fun getServerStatus(): Result<ServerStatus>

}