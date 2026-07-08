package com.home.core.network.client

import com.home.core.model.api.NetworkResult
import com.home.core.model.api.Request

interface NetworkClient {

    suspend fun <T> execute(
        call: suspend () -> T
    ): NetworkResult<T>

}