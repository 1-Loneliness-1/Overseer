package com.home.features.feature_servers_list.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.home.features.feature_servers_list.presentation.model.ServerItemUi

class ServerDiffCallback : DiffUtil.ItemCallback<ServerItemUi>() {

    override fun areItemsTheSame(oldItem: ServerItemUi, newItem: ServerItemUi): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ServerItemUi, newItem: ServerItemUi): Boolean {
        return oldItem == newItem
    }

}