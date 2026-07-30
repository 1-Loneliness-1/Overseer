package com.home.features.feature_servers_list.presentation.adapter

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.home.features.feature_servers_list.databinding.ServersListItemBinding
import com.home.features.feature_servers_list.presentation.model.ServerItemUi

class ServerViewHolder(
    private val binding: ServersListItemBinding,
) : RecyclerView.ViewHolder(binding.root) {

    private val context = binding.root.context

    fun bind(item: ServerItemUi) {
        binding.ivServerIcon.setImageResource(item.iconRes)
        binding.tvServerName.text = item.name
        binding.tvServerAddress.text = item.address
        binding.tvServerStatus.text = context.getString(item.serverStatus.textRes)
        binding.tvServerStatus.setTextColor(
            ContextCompat.getColor(
                binding.root.context,
                item.serverStatus.colorRes,
            )
        )
    }

}