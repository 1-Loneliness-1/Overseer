package com.home.features.feature_start.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.home.features.feature_start.domain.usecase.GetServerMetricsUseCase
import com.home.features.feature_start.presentation.state.StartFragmentUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    private val serverMetricsUseCase: GetServerMetricsUseCase,
) : ViewModel() {

    private val _uiStateFlow: MutableStateFlow<StartFragmentUiState> =
        MutableStateFlow(StartFragmentUiState.Loading)

    val uiStateFlow: StateFlow<StartFragmentUiState> = _uiStateFlow

    fun getServerMetrics() {
        viewModelScope.launch {
            _uiStateFlow.value = StartFragmentUiState.Loading

            val serverStatus = withContext(Dispatchers.IO) {
                serverMetricsUseCase.getServerMetrics()
            }

            _uiStateFlow.value = serverStatus
        }
    }

}