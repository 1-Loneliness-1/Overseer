package com.home.features.feature_servers_list.presentation.mapper

import com.home.core.model.server.Server
import com.home.core.model.server.ServerState
import com.home.features.feature_servers_list.presentation.model.ServerItemUi

fun Server.toUi(): ServerItemUi {
    return when (this.serverStatus) {
        ServerState.CHECKING -> {
            ServerItemUi(
                id = this.serverId,
                name = this.serverName,
                iconRes = this.serverIcon
            )
        }
    }
}