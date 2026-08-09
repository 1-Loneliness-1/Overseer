package com.home.features.feature_servers_list.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.home.features.feature_servers_list.databinding.FragmentServersListBinding
import com.home.features.feature_servers_list.presentation.adapter.ServerAdapter
import com.home.features.feature_servers_list.presentation.state.ServersListUiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ServersListFragment : Fragment() {

    private val viewModel: ServersListViewModel by viewModels()
    private lateinit var adapter: ServerAdapter

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

        val serversListLayoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        adapter = ServerAdapter()

        binding.iServersListLayout.rvAvailableServersList.layoutManager = serversListLayoutManager
        binding.iServersListLayout.rvAvailableServersList.adapter = adapter
    }

    private fun observeViewModel() {

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.serversListUiState.collect { uiState ->
                    render(uiState)
                }

            }

            repeatOnLifecycle(Lifecycle.State.STARTED) {

            }
        }

    }

    private fun render(uiState: ServersListUiState) {

        when (uiState) {
            is ServersListUiState.Loading -> showLoadingState()

            is ServersListUiState.Success -> showServersList()

            is ServersListUiState.EmptyServersList -> showEmptyListState()
        }
    }

    private fun showLoadingState() {
        binding.ivBackToPrevScreen.isVisible = false
        binding.iLoadingLayout.root.isVisible = true
        binding.iServersListLayout.root.isVisible = false
        binding.iErrorLayout.root.isVisible = false
    }

    private fun showServersList() {
        binding.ivBackToPrevScreen.isVisible = true
        binding.iLoadingLayout.root.isVisible = false
        binding.iErrorLayout.root.isVisible = false
        binding.iServersListLayout.root.isVisible = true
    }

}