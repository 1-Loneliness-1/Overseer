package com.home.core.network.client

import com.home.core.network.api.NetworkResult
import okio.IOException
import retrofit2.HttpException
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