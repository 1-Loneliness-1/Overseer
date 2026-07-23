package com.home.features.feature_servers_list.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.home.features.feature_servers_list.databinding.ServersListItemBinding
import com.home.features.feature_servers_list.domain.model.Server

class ServerViewHolder(
    private val binding: ServersListItemBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: Server) {
        binding.ivServerIcon.setImageResource(model.serverIconResourceId)
    }

}