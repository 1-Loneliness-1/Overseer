package com.home.features.feature_servers_list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.home.features.feature_servers_list.domain.usecase.GetServersUseCase
import com.home.features.feature_servers_list.presentation.mapper.ServerUiMapper
import com.home.features.feature_servers_list.presentation.model.ServerItemUi
import com.home.features.feature_servers_list.presentation.state.ServersListUiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ServersListViewModel @Inject constructor(
    private val serverUiMapper: ServerUiMapper,
    private val getServersUseCase: GetServersUseCase,
) : ViewModel() {

    private val _serversListUiState: MutableStateFlow<ServersListUiState> =
        MutableStateFlow(ServersListUiState.Loading)
    private val _servers: MutableSharedFlow<List<ServerItemUi>> = MutableSharedFlow()

    val serversListUiState: StateFlow<ServersListUiState> = _serversListUiState
    val servers: SharedFlow<List<ServerItemUi>> = _servers

    fun getServersList() {

        viewModelScope.launch {
            _serversListUiState.value = ServersListUiState.Loading

            getServersUseCase.getServers().collect { savedServers ->
                if (savedServers.isEmpty()) {
                    _serversListUiState.value = ServersListUiState.EmptyServersList
                } else {
                    val serversListItems = savedServers.map(serverUiMapper::map)

                    _serversListUiState.value = ServersListUiState.Success(serversListItems)
                }
            }
        }

    }
}