package com.home.features.feature_servers_list.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.home.features.feature_servers_list.domain.model.Server

class ServerDiffCallback : DiffUtil.ItemCallback<Server>() {

    override fun areItemsTheSame(oldItem: Server, newItem: Server): Boolean {
        return oldItem.serverId == newItem.serverId
    }

    override fun areContentsTheSame(oldItem: Server, newItem: Server): Boolean {
        return oldItem == newItem
    }

}