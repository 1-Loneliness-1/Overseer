package com.home.features.feature_servers_list.data

import com.home.core.database.data.dao.ServersDao
import com.home.core.database.data.mapper.toDomain
import com.home.core.model.server.Server
import com.home.core.model.server.ServerState
import com.home.core.network.api.NetworkResult
import com.home.core.network.client.NetworkClient
import com.home.core.network.factory.ServerApiFactory
import com.home.features.feature_servers_list.domain.repository.ServersRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class ServersRepositoryImpl @Inject constructor(
    private val serversDao: ServersDao,
    private val networkClient: NetworkClient,
    private val serverApiFactory: ServerApiFactory,
) : ServersRepository {

    private val _servers = MutableStateFlow<List<Server>>(emptyList())

    override fun getServers(): Flow<List<Server>> {
        return flow {
            val currentServers = serversDao.getServersList()
                .map { serverEntity ->
                    serverEntity.toDomain(ServerState.CHECKING)
                }

            _servers.value = currentServers
            emit(currentServers)

            coroutineScope {

                currentServers.forEach { server ->

                    launch {
                        val apiForCurrentServer =
                            serverApiFactory.getApi(server.serverAddress)
                        val serverHealthState = networkClient.execute {
                            apiForCurrentServer.getServerHealthStatus()
                        }

                        val serverCurrentStatus =
                            if (serverHealthState is NetworkResult.Success) {
                                ServerState.ONLINE
                            } else {
                                ServerState.OFFLINE
                            }

                        _servers.update { currentServers ->
                            currentServers.map {

                                if (it.serverId == server.serverId) {
                                    it.copy(serverStatus = serverCurrentStatus)
                                } else {
                                    it
                                }

                            }
                        }
                    }

                }

            }
        }
    }

}