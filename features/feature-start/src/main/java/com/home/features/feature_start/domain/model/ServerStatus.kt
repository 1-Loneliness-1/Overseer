package com.home.features.feature_start.domain.model

data class ServerStatus(
    val cpuUsagePercentage: Int,
    val ramUsagePercentage: Int,
    val diskUsagePercentage: Int,
    val uptimeHours: Long,
    val isVpnRunning: Boolean,
)