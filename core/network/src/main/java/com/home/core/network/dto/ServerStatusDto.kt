package com.home.core.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ServerStatusDto(
    @SerialName("metrics")
    val serverMetrics: ServerMetricsDto,
    @SerialName("vpn")
    val vpnStatus: VpnStatus,
)