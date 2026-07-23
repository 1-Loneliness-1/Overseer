package com.home.features.feature_servers_list.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ListAdapter
import com.home.features.feature_servers_list.databinding.FragmentServersListBinding
import com.home.features.feature_servers_list.domain.model.Server

class ServersListFragment : Fragment() {

    private var _binding: FragmentServersListBinding? = null

    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentServersListBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val serversListAdapter: ListAdapter<Server> = ListAdapter<Server>()
    }

}