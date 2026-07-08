package com.home.features.feature_start.data.mapper

import com.home.core.network.dto.ServerStatusDto
import com.home.features.feature_start.domain.model.ServerStatus

internal class ServerStatusDtoMapper {

    fun mapDtoToDomainModel(dtoObj: ServerStatusDto): ServerStatus {

        return ServerStatus(
            cpuUsagePercentage = dtoObj.cpuUsagePercentage,
            ramUsagePercentage = dtoObj.ramUsagePercentage,
            diskUsagePercentage = dtoObj.diskUsagePercentage,
            uptimeHours = dtoObj.uptimeHours,
            isVpnRunning = dtoObj.isVpnRunning,
        )
    }

}