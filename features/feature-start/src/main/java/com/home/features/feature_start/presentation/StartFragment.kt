package com.home.features.feature_start.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.home.features.feature_start.R
import com.home.features.feature_start.databinding.FragmentStartBinding
import com.home.features.feature_start.presentation.state.StartFragmentUiState
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class StartFragment : Fragment() {

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

        observeViewModel()
        viewModel.getServerMetrics()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeViewModel() {
        viewModel.uiStateLiveData.observe(viewLifecycleOwner) { fragmentUiState ->
            when (fragmentUiState) {

                is StartFragmentUiState.Loading -> {
                    binding.pbMain.isVisible = true
                    binding.llServerStatus.isVisible = false
                    binding.clServerMetrics.isVisible = false
                }

                is StartFragmentUiState.Content -> {
                    binding.pbMain.isVisible = false
                    binding.llServerStatus.isVisible = true
                    binding.clServerMetrics.isVisible = true

                    binding.tvCpuUsageValue.text = String.format(
                        Locale.getDefault(),
                        "%d %",
                        fragmentUiState.serverStatus.cpuUsagePercentage,
                    )
                    binding.tvRamUsageValue.text = String.format(
                        Locale.getDefault(),
                        "%d %",
                        fragmentUiState.serverStatus.ramUsagePercentage,
                    )
                    binding.tvDiskUsageValue.text = String.format(
                        Locale.getDefault(),
                        "%d %",
                        fragmentUiState.serverStatus.diskUsagePercentage,
                    )
                    binding.tvUptimeHoursValue.text = String.format(
                        Locale.getDefault(),
                        "%d",
                        fragmentUiState.serverStatus.uptimeHours
                    )

                    val colorForVpnStatusBg = ContextCompat.getColor(
                        requireContext(),
                        if (fragmentUiState.serverStatus.isVpnRunning) R.color.light_green else R.color.light_red
                    )
                    val textForVpnStatusView = getString(
                        if (fragmentUiState.serverStatus.isVpnRunning) R.string.vpn_is_ok else R.string.vpn_is_down
                    )

                    binding.tvVpnStatus.apply {
                        text = textForVpnStatusView
                        setBackgroundColor(colorForVpnStatusBg)
                    }
                }

                is StartFragmentUiState.SlowServer -> {
                    binding.pbMain.isVisible = false
                    binding.ivServerStatus.setImageResource(R.drawable.slow_server)
                    binding.tvServerAvailability.text = getString(R.string.server_slow)
                    binding.llServerStatus.isVisible = true
                    binding.clServerMetrics.isVisible = false
                }

                is StartFragmentUiState.ServerUnavailable -> {
                    binding.pbMain.isVisible = false
                    binding.ivServerStatus.setImageResource(R.drawable.server_is_down)
                    binding.tvServerAvailability.text = getString(R.string.server_is_fucked)
                    binding.llServerStatus.isVisible = true
                    binding.clServerMetrics.isVisible = false
                }

                is StartFragmentUiState.NoInternet -> {
                    binding.pbMain.isVisible = false
                    binding.ivServerStatus.setImageResource(R.drawable.no_internet)
                    binding.tvServerAvailability.text = getString(R.string.no_internet)
                    binding.llServerStatus.isVisible = true
                    binding.clServerMetrics.isVisible = false
                }

                is StartFragmentUiState.UnknownError -> {
                    binding.pbMain.isVisible = false
                    binding.ivServerStatus.setImageResource(R.drawable.unknown_error)
                    binding.tvServerAvailability.text = getString(R.string.unknown_error)
                    binding.llServerStatus.isVisible = true
                    binding.clServerMetrics.isVisible = false
                }
            }
        }
    }

}