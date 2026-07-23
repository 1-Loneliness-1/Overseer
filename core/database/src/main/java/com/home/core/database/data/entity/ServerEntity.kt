package com.home.core.database.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "servers")
data class ServerEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val name: String,
    @ColumnInfo(name = "base_url") val baseUrl: String,
    val icon: String,
)