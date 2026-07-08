package com.home.core.network.client

import retrofit2.HttpException
import com.home.core.model.api.NetworkResult
import okio.IOException
import javax.inject.Inject

class RetrofitNetworkClient @Inject constructor() : NetworkClient {

    override suspend fun <T> execute(call: suspend () -> T): NetworkResult<T> {

        return try {

            NetworkResult.Success(call())

        } catch (_: IOException) {

            NetworkResult.NetworkError

        } catch (e: HttpException) {

            NetworkResult.HttpError(e.code())

        } catch (_: Exception) {

            NetworkResult.UnknownError

        }

    }

}