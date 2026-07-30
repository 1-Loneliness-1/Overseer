package com.home.features.feature_servers_list.domain.usecase

import com.home.core.model.server.Server
import kotlinx.coroutines.flow.Flow

interface GetServersUseCase {

    fun getServers(): Flow<List<Server>>

}