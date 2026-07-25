package com.home.core.database.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.home.core.database.data.converters.ServerIconConverter
import com.home.core.database.data.dao.ServersDao
import com.home.core.database.data.entity.ServerEntity

@Database(
    entities = [ServerEntity::class],
    version = 1,
    exportSchema = true,
)
@TypeConverters(ServerIconConverter::class)
abstract class OverseerDatabase : RoomDatabase() {

    abstract fun serversDao(): ServersDao

}