package com.home.core.database.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.home.core.database.data.entity.ServerEntity

@Dao
interface ServersDao {

    @Upsert(ServerEntity::class)
    suspend fun insertNewServer(server: ServerEntity)

    @Query("SELECT * FROM servers;")
    suspend fun getServersList(): List<ServerEntity>

    @Query("DELETE FROM servers WHERE id = :id;")
    suspend fun deleteServerById(id: Long)

    @Query("DELETE FROM servers;")
    suspend fun deleteAllServers()

}