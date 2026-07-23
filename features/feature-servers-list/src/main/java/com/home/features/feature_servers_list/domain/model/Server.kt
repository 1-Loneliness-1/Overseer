package com.home.features.feature_servers_list.domain.model

data class Server(
    val serverId: Int,
    val serverIcon: ServerIcon,
    val serverName: String,
    val serverStatus: String,
    val serverAddress: String,
)