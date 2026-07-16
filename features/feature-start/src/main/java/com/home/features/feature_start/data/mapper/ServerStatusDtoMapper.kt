package com.home.features.feature_start.data.mapper

import com.home.core.network.dto.ServerStatusDto
import com.home.features.feature_start.domain.model.ServerStatus

internal fun ServerStatusDto.mapDtoToDomainModel(): ServerStatus {

    return ServerStatus(
        cpuUsagePercentage = this.serverMetrics.cpuUsagePercentage,
        ramUsagePercentage = this.serverMetrics.ramUsagePercentage,
        diskUsagePercentage = this.serverMetrics.diskUsagePercentage,
        uptimeHours = this.serverMetrics.uptimeHours,
        isVpnRunning = this.vpnStatus.isRunning,
    )
}