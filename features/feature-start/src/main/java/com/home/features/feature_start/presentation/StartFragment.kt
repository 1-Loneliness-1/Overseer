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
import java.util.Locale

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

            }
        }
    }

}