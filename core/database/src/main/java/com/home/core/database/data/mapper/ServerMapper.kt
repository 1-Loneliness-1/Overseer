package com.home.core.database.data.mapper

import com.home.core.database.data.entity.ServerEntity
import com.home.core.model.server.Server
import com.home.core.model.server.ServerIcon
import com.home.core.model.server.ServerState

fun ServerEntity.toDomain(state: ServerState): Server {
    return Server(
        serverId = this.id,
        serverIcon = ServerIcon.entries.firstOrNull { it.name == this.icon } ?: ServerIcon.DEFAULT,
        serverName = this.name,
        serverStatus = state,
        serverAddress = this.baseUrl,
        isSelected = this.isSelected,
    )
}

fun Server.toEntity(): ServerEntity {
    return ServerEntity(
        id = this.serverId,
        name = this.serverName,
        baseUrl = this.serverAddress,
        icon = serverIcon.name,
        isSelected = this.isSelected,
    )
}