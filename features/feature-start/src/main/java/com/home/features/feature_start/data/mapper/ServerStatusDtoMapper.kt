package com.home.features.feature_start.data.mapper

import com.home.core.network.dto.ServerStatusDto
import com.home.features.feature_start.domain.model.ServerStatus

internal fun ServerStatusDto.mapDtoToDomainModel(): ServerStatus {

    return ServerStatus(
        cpuUsagePercentage = this.cpuUsagePercentage,
        ramUsagePercentage = this.ramUsagePercentage,
        diskUsagePercentage = this.diskUsagePercentage,
        uptimeHours = this.uptimeHours,
        isVpnRunning = this.isVpnRunning,
    )
}