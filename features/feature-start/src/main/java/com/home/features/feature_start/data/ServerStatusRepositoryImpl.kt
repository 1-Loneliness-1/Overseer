package com.home.features.feature_start.data

import com.home.core.network.api.NetworkResult
import com.home.core.network.api.VpsApi
import com.home.core.network.client.NetworkClient
import com.home.features.feature_start.data.mapper.mapDtoToDomainModel
import com.home.features.feature_start.domain.model.AppError
import com.home.features.feature_start.domain.model.Result
import com.home.features.feature_start.domain.model.ServerStatus
import com.home.features.feature_start.domain.repository.ServerStatusRepository
import javax.inject.Inject

class ServerStatusRepositoryImpl @Inject constructor(
    private val vpsApi: VpsApi,
    private val networkClient: NetworkClient,
) : ServerStatusRepository {

    override suspend fun getServerStatus(): Result<ServerStatus> {
        val result = networkClient.execute {
            vpsApi.getServerStatus()
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