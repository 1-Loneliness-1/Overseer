package com.home.core.network.api

import com.home.core.network.dto.ServerHealthDto
import com.home.core.network.dto.ServerStatusDto
import retrofit2.http.GET

interface VpsApi {

    @GET("api/status")
    suspend fun getServerStatus(): ServerStatusDto

    @GET("api/health")
    suspend fun getServerHealthStatus(): ServerHealthDto

}