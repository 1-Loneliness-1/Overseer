package com.home.features.feature_servers_list.domain.repository

import com.home.core.model.server.Server
import kotlinx.coroutines.flow.Flow

interface ServersRepository {

    fun getServers(): Flow<List<Server>>

}