package com.home.core.network.factory

import com.home.core.network.api.VpsApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServerApiFactory @Inject constructor(
    private val okHttpClient: OkHttpClient,
    private val json: Json,
) {

    private val cache = mutableMapOf<String, VpsApi>()

    fun getApi(baseUrl: String): VpsApi {

        return cache.getOrPut(baseUrl) {

            Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(
                    json.asConverterFactory("application/json".toMediaType())
                )
                .build()
                .create(VpsApi::class.java)

        }
    }

}