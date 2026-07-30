package com.home.features.feature_servers_list.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.home.features.feature_servers_list.databinding.FragmentServersListBinding
import com.home.features.feature_servers_list.presentation.adapter.ServerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ServersListFragment : Fragment() {

    private val viewModel: ServersListViewModel by viewModels()

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

        val serversListAdapter: ServerAdapter = ServerAdapter()
    }

}