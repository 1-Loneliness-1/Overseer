package com.home.features.feature_start.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.home.features.feature_start.domain.usecase.GetServerMetricsUseCase
import com.home.features.feature_start.presentation.state.StartFragmentUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    private val serverMetricsUseCase: GetServerMetricsUseCase,
) : ViewModel() {

    private val _uiStateLiveData: MutableLiveData<StartFragmentUiState> =
        MutableLiveData(StartFragmentUiState.Loading)

    val uiStateLiveData: LiveData<StartFragmentUiState> = _uiStateLiveData

    fun getServerMetrics() {
        viewModelScope.launch {
            _uiStateLiveData.postValue(StartFragmentUiState.Loading)

            val serverStatus = withContext(Dispatchers.IO) {
                serverMetricsUseCase.getServerMetrics()
            }

            _uiStateLiveData.postValue(serverStatus)
        }
    }

}