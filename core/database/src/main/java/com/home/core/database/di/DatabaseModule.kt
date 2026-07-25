package com.home.core.database.di

import android.content.Context
import androidx.room.Room
import com.home.core.database.data.OverseerDatabase
import com.home.core.database.data.dao.ServersDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): OverseerDatabase {

        return Room.databaseBuilder(
            context,
            OverseerDatabase::class.java,
            "overseer_database"
        ).build()
    }

    @Provides
    fun provideServersDao(
        database: OverseerDatabase,
    ): ServersDao {

        return database.serversDao()
    }

}