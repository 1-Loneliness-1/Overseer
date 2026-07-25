package com.home.core.database.data.converters

import androidx.room.TypeConverter
import com.home.core.model.server.ServerIcon

class ServerIconConverter {

    @TypeConverter
    fun fromServerIcon(icon: ServerIcon): String {

        return icon.name
    }

    @TypeConverter
    fun toServerIcon(nameOfIcon: String): ServerIcon {

        return ServerIcon.entries.firstOrNull { it.name == nameOfIcon }
            ?: ServerIcon.DEFAULT
    }

}