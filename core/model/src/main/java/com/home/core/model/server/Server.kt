package com.home.core.model.server

data class Server(
    val serverId: Long,
    val serverIcon: ServerIcon,
    val serverName: String,
    val serverStatus: ServerState,
    val serverAddress: String,
)