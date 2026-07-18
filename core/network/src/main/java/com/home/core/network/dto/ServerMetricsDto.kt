package com.home.core.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class ServerMetricsDto(
    val cpuUsagePercentage: Int,
    val ramUsagePercentage: Int,
    val diskUsagePercentage: Int,
    val uptimeHours: Long,
)