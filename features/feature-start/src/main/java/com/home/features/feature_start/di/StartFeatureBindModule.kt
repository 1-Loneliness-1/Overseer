package com.home.features.feature_start.di

import com.home.features.feature_start.data.ServerStatusRepositoryImpl
import com.home.features.feature_start.domain.GetServerMetricsUseCaseImpl
import com.home.features.feature_start.domain.repository.ServerStatusRepository
import com.home.features.feature_start.domain.usecase.GetServerMetricsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class StartFeatureBindModule {

    @Binds
    abstract fun bindServerStatusRepository(
        impl: ServerStatusRepositoryImpl,
    ): ServerStatusRepository

    @Binds
    abstract fun bindGetServerMetricsUseCase(
        impl: GetServerMetricsUseCaseImpl
    ): GetServerMetricsUseCase

}