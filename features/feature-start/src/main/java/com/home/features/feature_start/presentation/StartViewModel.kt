package com.home.features.feature_start.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.home.features.feature_start.domain.usecase.GetServerMetricsUseCase
import com.home.features.feature_start.presentation.state.StartFragmentUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class StartViewModel @Inject constructor(
    private val serverMetricsUseCase: GetServerMetricsUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<StartFragmentUiState> =
        MutableStateFlow(StartFragmentUiState.Loading)

    val uiState: StateFlow<StartFragmentUiState> = _uiState

    fun getServerMetrics() {
        viewModelScope.launch {
            _uiState.value = StartFragmentUiState.Loading
            _uiState.value = serverMetricsUseCase.getServerMetrics()
        }
    }

}