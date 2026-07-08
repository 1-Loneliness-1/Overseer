package com.home.core.model.dto

data class ServerStatusDto(
    val cpuUsagePercentage: Int,
    val ramUsagePercentage: Int,
    val diskUsagePercentage: Int,
    val uptimeHours: Long,
    val isVpnRunning: Boolean,
    val vpnInterfaceName: String,
)