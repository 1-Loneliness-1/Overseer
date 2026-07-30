package com.home.features.feature_servers_list.presentation.state

import com.home.core.model.server.Server

sealed interface ServersListUiState {

    data object Loading : ServersListUiState

    data class Success(
        val servers: List<Server>,
    ) : ServersListUiState

}