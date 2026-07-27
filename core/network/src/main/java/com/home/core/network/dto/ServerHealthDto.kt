package com.home.core.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class ServerHealthDto(
    val status: ServerHealthStatusDto,
    val timestamp: Long,
)
