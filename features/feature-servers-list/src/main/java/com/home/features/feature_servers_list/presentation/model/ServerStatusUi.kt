package com.home.features.feature_servers_list.presentation.model

import androidx.annotation.ColorRes
import androidx.annotation.StringRes

data class ServerStatusUi(
    @StringRes val textRes: Int,
    @ColorRes val colorRes: Int,
)