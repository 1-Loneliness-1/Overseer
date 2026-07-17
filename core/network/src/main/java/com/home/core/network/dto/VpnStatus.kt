package com.home.core.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class VpnStatus(
    val isRunning: Boolean,
    val interfaceName: String,
)