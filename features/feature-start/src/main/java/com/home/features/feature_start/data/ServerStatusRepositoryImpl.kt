package com.home.features.feature_start.data

import com.home.core.network.api.VpsApi
import com.home.core.network.client.NetworkClient
import com.home.features.feature_start.domain.model.ServerStatus
import com.home.features.feature_start.domain.repository.ServerStatusRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ServerStatusRepositoryImpl @Inject constructor(
    private val vpsApi: VpsApi,
    private val networkClient: NetworkClient,
) : ServerStatusRepository {

    override fun getServerStatus(): Flow<ServerStatus> {
        return flow {

        }
    }

}