package com.home.features.feature_servers_list.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter

import com.home.features.feature_servers_list.databinding.ServersListItemBinding
import com.home.features.feature_servers_list.presentation.model.ServerItemUi

class ServerAdapter : ListAdapter<ServerItemUi, ServerViewHolder>(ServerDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ServerViewHolder {
        val binding = ServersListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ServerViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ServerViewHolder,
        position: Int,
    ) {
        holder.bind(getItem(position))
    }

}