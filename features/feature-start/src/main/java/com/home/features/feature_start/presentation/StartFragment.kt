package com.home.features.feature_start.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.home.core.ui.AppNavigator
import com.home.features.feature_start.R
import com.home.features.feature_start.databinding.FragmentStartBinding
import com.home.features.feature_start.domain.model.ServerStatus
import com.home.features.feature_start.presentation.state.StartFragmentUiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class StartFragment : Fragment() {

    @Inject
    lateinit var navigator: AppNavigator
    private val viewModel: StartViewModel by viewModels()

    private var _binding: FragmentStartBinding? = null

    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ibMenu.setOnClickListener { view ->
            PopupMenu(requireContext(), view).apply {

                menuInflater.inflate(R.menu.main_menu, menu)

                setOnMenuItemClickListener { item ->
                    when (item.itemId) {

                        R.id.action_servers_list -> {
                            navigator.openServersList()
                            true
                        }

                        R.id.action_refresh_server_metrics -> {
                            viewModel.getServerMetrics()
                            true
                        }

                        R.id.action_settings -> {
                            TODO("Settings screen not done yet")
                        }

                        R.id.action_about_app -> {
                            TODO("About app screen not done yet")
                        }

                        else -> false
                    }
                }

            }
        }

        observeViewModel()
        viewModel.getServerMetrics()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(
                Lifecycle.State.STARTED
            ) {
                viewModel.uiStateFlow.collect { uiState ->
                    render(uiState)
                }
            }
        }
    }

    private fun render(uiState: StartFragmentUiState) {
        when (uiState) {

            is StartFragmentUiState.Loading -> showLoadingState()

            is StartFragmentUiState.Content -> showSuccessState(uiState.serverStatus)

            is StartFragmentUiState.EmptyServersList -> showEmptyListState()

            else -> showErrorState(uiState)
        }
    }

    private fun showEmptyListState() {
        binding.iLoadingLayout.root.isVisible = false
        binding.iSuccessLayout.root.isVisible = false
        binding.iErrorLayout.root.isVisible = false
        binding.iEmptyListLayout.root.isVisible = true
    }

    private fun showLoadingState() {
        binding.iLoadingLayout.root.isVisible = true
        binding.iSuccessLayout.root.isVisible = false
        binding.iErrorLayout.root.isVisible = false
        binding.iEmptyListLayout.root.isVisible = false
    }

    private fun showSuccessState(serverStatus: ServerStatus) {
        binding.iSuccessLayout.apply {
            iCpuMetric.tvMetricName.text = getString(R.string.cpu_usage)
            iCpuMetric.tvMetricValue.text =
                String.format(
                    Locale.getDefault(),
                    "%d %%",
                    serverStatus.cpuUsagePercentage,
                )

            iRamMetric.tvMetricName.text = getString(R.string.ram_usage)
            iRamMetric.tvMetricValue.text =
                String.format(
                    Locale.getDefault(),
                    "%d %%",
                    serverStatus.ramUsagePercentage,
                )

            iDiskMetric.tvMetricName.text = getString(R.string.disk_usage)
            iDiskMetric.tvMetricValue.text =
                String.format(
                    Locale.getDefault(),
                    "%d %%",
                    serverStatus.diskUsagePercentage,
                )

            iUptimeMetric.tvMetricName.text = getString(R.string.uptime_hours)
            iUptimeMetric.tvMetricValue.text =
                String.format(
                    Locale.getDefault(),
                    "%d",
                    serverStatus.uptimeHours,
                )


            iVpnMetric.tvMetricName.text = getString(R.string.vpn_status)
            val vpnStatusText =
                if (serverStatus.isVpnRunning) R.string.vpn_is_running else R.string.vpn_is_down
            iVpnMetric.tvMetricValue.text =
                String.format(
                    Locale.getDefault(),
                    "%s",
                    getString(vpnStatusText)
                )
        }

        binding.iLoadingLayout.root.isVisible = false
        binding.iSuccessLayout.root.isVisible = true
        binding.iErrorLayout.root.isVisible = false
        binding.iEmptyListLayout.root.isVisible = false
    }

    private fun showErrorState(uiErrorState: StartFragmentUiState) {
        binding.iLoadingLayout.root.isVisible = false
        binding.iSuccessLayout.root.isVisible = false
        binding.iEmptyListLayout.root.isVisible = false

        when (uiErrorState) {

            is StartFragmentUiState.SlowServer -> {
                binding.iErrorLayout.ivServerStatus.setImageResource(R.drawable.slow_server)
                binding.iErrorLayout.tvServerAvailability.text = getString(R.string.server_slow)
            }

            is StartFragmentUiState.ServerUnavailable -> {
                binding.iErrorLayout.ivServerStatus.setImageResource(R.drawable.server_is_down)
                binding.iErrorLayout.tvServerAvailability.text =
                    getString(R.string.server_is_fucked)
            }

            is StartFragmentUiState.NoInternet -> {
                binding.iErrorLayout.ivServerStatus.setImageResource(R.drawable.no_internet)
                binding.iErrorLayout.tvServerAvailability.text = getString(R.string.no_internet)
            }

            else -> {
                binding.iErrorLayout.ivServerStatus.setImageResource(R.drawable.unknown_error)
                binding.iErrorLayout.tvServerAvailability.text = getString(R.string.unknown_error)
            }

        }

        binding.iErrorLayout.root.isVisible = true
    }

}