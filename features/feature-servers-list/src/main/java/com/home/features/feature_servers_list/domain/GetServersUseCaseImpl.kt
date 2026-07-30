package com.home.features.feature_servers_list.domain

import com.home.core.model.server.Server
import com.home.features.feature_servers_list.domain.repository.ServersRepository
import com.home.features.feature_servers_list.domain.usecase.GetServersUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetServersUseCaseImpl @Inject constructor(
    private val serversRepository: ServersRepository,
) : GetServersUseCase {

    override fun getServers(): Flow<List<Server>> {
        return serversRepository.getServers()
    }
}