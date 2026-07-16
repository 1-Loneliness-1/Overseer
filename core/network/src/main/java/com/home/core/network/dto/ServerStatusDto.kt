package com.home.core.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class ServerStatusDto(
    val serverMetrics: ServerMetricsDto,
    val vpnStatus: VpnStatus,
)