package com.home.features.feature_servers_list.presentation.model

import androidx.annotation.DrawableRes

data class ServerItemUi(
    val id: Long,
    val name: String,
    @DrawableRes
    val iconRes: Int,
    val address: String,
    val serverStatus: ServerStatusUi,
)