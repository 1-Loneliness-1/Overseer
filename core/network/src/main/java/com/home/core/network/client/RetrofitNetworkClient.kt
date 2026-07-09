package com.home.core.network.client

import com.home.core.network.api.NetworkResult
import kotlinx.serialization.SerializationException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class RetrofitNetworkClient @Inject constructor() : NetworkClient {

    override suspend fun <T> execute(call: suspend () -> T): NetworkResult<T> {

        return try {

            NetworkResult.Success(call())

        } catch (_: UnknownHostException) {

            NetworkResult.NoConnectionError

        } catch (_: ConnectException) {

            NetworkResult.NoConnectionError

        } catch (_: SocketTimeoutException) {

            NetworkResult.TimeoutError

        } catch (e: HttpException) {

            NetworkResult.HttpError(e.code(), e.message)

        } catch (_: SerializationException) {

            NetworkResult.SerializationError

        } catch (e: Exception) {

            NetworkResult.UnknownError(e)

        }

    }

}