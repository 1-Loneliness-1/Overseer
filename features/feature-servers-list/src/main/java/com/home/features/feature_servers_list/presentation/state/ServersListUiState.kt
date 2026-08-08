package com.home.features.feature_servers_list.presentation.state

import com.home.features.feature_servers_list.presentation.model.ServerItemUi

sealed interface ServersListUiState {

    data object Loading : ServersListUiState

    data object EmptyServersList : ServersListUiState

    data class Success(
        val servers: List<ServerItemUi>,
    ) : ServersListUiState

}