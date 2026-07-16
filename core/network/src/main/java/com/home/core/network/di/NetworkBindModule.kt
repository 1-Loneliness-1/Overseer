package com.home.core.network.di

import com.home.core.network.client.NetworkClient
import com.home.core.network.client.RetrofitNetworkClient
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkBindModule {

    @Binds
    abstract fun bindNetworkClient(
        impl: RetrofitNetworkClient,
    ): NetworkClient

}