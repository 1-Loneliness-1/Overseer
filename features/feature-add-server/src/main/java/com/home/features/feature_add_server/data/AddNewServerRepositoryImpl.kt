package com.home.features.feature_add_server.data

import com.home.core.database.data.dao.ServersDao
import com.home.core.database.data.mapper.toEntity
import com.home.core.model.server.Server
import com.home.features.feature_add_server.domain.repository.AddNewServerRepository
import javax.inject.Inject

class AddNewServerRepositoryImpl @Inject constructor(
    private val serversDao: ServersDao,
) : AddNewServerRepository {

    override suspend fun addNewServer(newServer: Server) {
        serversDao.insertNewServer(newServer.toEntity())
    }

}