package com.home.core.network.api

import com.home.core.network.dto.ServerStatusDto
import retrofit2.http.GET

interface VpsApi {

    @GET("api/status")
    suspend fun getServerStatus(): ServerStatusDto

}