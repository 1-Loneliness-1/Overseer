package com.home.core.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class ServerStatusDto(
    val cpuUsagePercentage: Int,
    val ramUsagePercentage: Int,
    val diskUsagePercentage: Int,
    val uptimeHours: Long,
    val isVpnRunning: Boolean,
    val vpnInterfaceName: String,
)