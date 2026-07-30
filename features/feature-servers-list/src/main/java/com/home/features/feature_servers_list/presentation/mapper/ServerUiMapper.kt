package com.home.features.feature_servers_list.presentation.mapper

import com.home.core.model.server.Server
import com.home.core.model.server.ServerIcon
import com.home.core.model.server.ServerState
import com.home.features.feature_servers_list.R
import com.home.features.feature_servers_list.presentation.model.ServerItemUi
import com.home.features.feature_servers_list.presentation.model.ServerStatusUi
import javax.inject.Inject
import com.home.core.ui.R as CoreUiR

class ServerUiMapper @Inject constructor() {

    fun map(server: Server): ServerItemUi {
        return ServerItemUi(
            id = server.serverId,
            name = server.serverName,
            iconRes = getIconRes(server.serverIcon),
            address = server.serverAddress,
            serverStatus = getServerStatusUi(server.serverStatus),
        )
    }

    private fun getIconRes(serverIcon: ServerIcon): Int {
        return when (serverIcon) {

            ServerIcon.HOME -> R.drawable.home

            ServerIcon.CLOUD -> R.drawable.cloud_server

            ServerIcon.DATABASE -> R.drawable.database_server

            ServerIcon.WORK -> R.drawable.work_server

            ServerIcon.VPS -> R.drawable.vps_server

            ServerIcon.NAS -> R.drawable.nas_server

            ServerIcon.DEFAULT -> R.drawable.default_server
        }
    }

    private fun getServerStatusUi(serverStatusUi: ServerState): ServerStatusUi {
        return when (serverStatusUi) {
            ServerState.CHECKING -> {
                ServerStatusUi(
                    textRes = R.string.server_checking,
                    colorRes = CoreUiR.color.light_orange
                )
            }
        }
    }

}