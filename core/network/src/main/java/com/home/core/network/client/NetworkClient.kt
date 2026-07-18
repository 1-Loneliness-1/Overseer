package com.home.core.network.client

import com.home.core.network.api.NetworkResult

interface NetworkClient {

    suspend fun <T> execute(
        call: suspend () -> T
    ): NetworkResult<T>

}