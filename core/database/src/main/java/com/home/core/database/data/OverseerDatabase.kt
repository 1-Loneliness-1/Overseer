package com.home.core.database.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.home.core.database.data.dao.ServersDao
import com.home.core.database.data.entity.ServerEntity

@Database(
    version = 1,
    entities = [ServerEntity::class],
)
@TypeConverters()
abstract class OverseerDatabase : RoomDatabase() {

    abstract fun serversDao(): ServersDao

}