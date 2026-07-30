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
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

import javax.inject.Inject

class ServersRepositoryImpl @Inject constructor(
    private val serversDao: ServersDao,
    private val networkClient: NetworkClient,
    private val serverApiFactory: ServerApiFactory,
) : ServersRepository {

    override fun getServers(): Flow<List<Server>> =
        channelFlow {
            val servers = serversDao.getServersList()
                .map {
                    it.toDomain(ServerState.CHECKING)
                }
            val state = MutableStateFlow(servers)

            send(state.value)

            coroutineScope {

                servers.forEach { server ->

                    launch {
                        val apiForCurrentServer = serverApiFactory.getApi(server.serverAddress)
                        val serverHealthState = networkClient.execute {
                            apiForCurrentServer.getServerHealthStatus()
                        }
                        val currentServerStatus =
                            if (serverHealthState is NetworkResult.Success) {
                                ServerState.ONLINE
                            } else {
                                ServerState.OFFLINE
                            }

                        state.update { currentServers ->
                            currentServers.map {
                                if (it.serverAddress == server.serverAddress) {
                                    it.copy(serverStatus = currentServerStatus)
                                } else {
                                    it
                                }
                            }
                        }

                        send(state.value)
                    }

                }

            }
        }

}