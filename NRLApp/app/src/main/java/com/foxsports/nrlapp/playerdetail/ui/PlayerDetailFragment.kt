package com.foxsports.nrlapp.playerdetail.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.foxsports.nrlapp.R
import com.foxsports.nrlapp.data.Resource
import com.foxsports.nrlapp.data.Status
import com.foxsports.nrlapp.databinding.PlayerDetailFragmentBinding
import com.foxsports.nrlapp.playerdetail.util.SEASON_ID
import com.foxsports.nrlapp.playerdetail.util.SERIES_ID
import com.foxsports.nrlapp.playerdetail.util.TEAM_ID
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class PlayerDetailFragment : DaggerFragment() {

    lateinit var binding: PlayerDetailFragmentBinding
    private lateinit var playersDetailViewModel: PlayerDetailViewModel
    private val playerStatsAdapter = PlayerStatListAdapter(arrayListOf())

    @Inject
    lateinit var viewModelFactory: PlayerDetailViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = PlayerDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        playersDetailViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(PlayerDetailViewModel::class.java)

        binding.viewModel = playersDetailViewModel
        binding.lastGameStatsRv.adapter = playerStatsAdapter
        binding.executePendingBindings()

        val safeArgs: PlayerDetailFragmentArgs by navArgs()
        setupPlayerDetail(safeArgs.playerDetail.id)
    }

    private fun setupPlayerDetail(playerId: Int) {
        playersDetailViewModel.uiModel.observe(this, Observer { it?.let { showPlayerDetailUiState(it) } })
        playersDetailViewModel.loadPlayerDetails(SERIES_ID, SEASON_ID, TEAM_ID, playerId)
    }

    private fun showPlayerDetailUiState(playerDetailResource: Resource<PlayerDetailUiModel>) {
        playerDetailResource.data?.let { playerDetailUiModel ->
            binding.playerDetail = playerDetailUiModel
            binding.executePendingBindings()
        }
        if (playerDetailResource.status == Status.ERROR) {
            showErrorToast()
        }
    }

    private fun showErrorToast() {
        Toast.makeText(activity, getString(R.string.error_loading_data), Toast.LENGTH_SHORT).show()
    }
}