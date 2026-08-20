package com.home.features.feature_add_server.domain.repository

import com.home.core.model.server.Server

interface AddNewServerRepository {

    suspend fun addNewServer(newServer: Server)

}