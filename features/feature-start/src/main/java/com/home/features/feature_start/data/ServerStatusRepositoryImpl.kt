package com.home.features.feature_start.data

import com.home.core.database.data.dao.ServersDao
import com.home.core.database.data.mapper.toDomain

import com.home.core.model.server.ServerState

import com.home.core.network.api.NetworkResult
import com.home.core.network.client.NetworkClient
import com.home.core.network.factory.ServerApiFactory

import com.home.features.feature_start.data.mapper.mapDtoToDomainModel
import com.home.features.feature_start.domain.model.AppError
import com.home.features.feature_start.domain.model.Result
import com.home.features.feature_start.domain.model.ServerStatus
import com.home.features.feature_start.domain.repository.ServerStatusRepository

import javax.inject.Inject

class ServerStatusRepositoryImpl @Inject constructor(
    private val serversDao: ServersDao,
    private val networkClient: NetworkClient,
    private val serverApiFactory: ServerApiFactory,
) : ServerStatusRepository {

    override suspend fun getServerStatus(): Result<ServerStatus> {
        val selectedServer = serversDao.getSelectedServer()?.toDomain(ServerState.CHECKING)
        val serverApi = serverApiFactory.getApi(
            selectedServer?.serverAddress ?: return Result.Failure(AppError.ServerNotSelected)
        )

        val result = networkClient.execute {
            serverApi.getServerStatus()
        }

        return when (result) {

            is NetworkResult.Success -> {

                Result.Success(result.data.mapDtoToDomainModel())

            }

            is NetworkResult.HttpError -> {

                Result.Failure(AppError.ServerUnavailable)

            }

            is NetworkResult.NoConnectionError -> {

                Result.Failure(AppError.NoInternet)

            }

            is NetworkResult.TimeoutError -> {

                Result.Failure(AppError.ServerTooSlow)

            }

            is NetworkResult.SerializationError -> {

                Result.Failure(AppError.Unknown(result.throwable))

            }

            is NetworkResult.UnknownError -> {

                Result.Failure(AppError.Unknown(result.throwable))

            }

        }
    }

}