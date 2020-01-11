package com.foxsports.nrlapp.topplayerstats.ui.topplayerstats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.foxsports.nrlapp.R
import com.foxsports.nrlapp.data.Resource
import com.foxsports.nrlapp.data.Status
import com.foxsports.nrlapp.databinding.TopPlayerStatsFragmentBinding
import com.foxsports.nrlapp.topplayerstats.data.MATCH_ID
import com.foxsports.nrlapp.topplayerstats.data.STAT_TYPE_LIST
import com.foxsports.nrlapp.topplayerstats.data.TOP_PLAYER_LIMIT
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class TopPlayerStatsFragment : DaggerFragment() {

    lateinit var binding: TopPlayerStatsFragmentBinding
    private lateinit var topPlayersViewModel: TopPlayerStatsViewModel

    @Inject
    lateinit var viewModelFactory: TopPlayerStatsViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = TopPlayerStatsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        topPlayersViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(TopPlayerStatsViewModel::class.java)

        binding.viewModel = topPlayersViewModel
        binding.executePendingBindings()

        initTopPlayerList()
    }

    private fun initTopPlayerList() {
        topPlayersViewModel.uiModel.observe(this, Observer { it?.let { showTopPlayersUiState(it) } })
        topPlayersViewModel.loadTopPlayerStats(MATCH_ID, STAT_TYPE_LIST, TOP_PLAYER_LIMIT)
    }

    private fun showTopPlayersUiState(topPlayerDataResource: Resource<TopPlayerUiModel>) {
        topPlayerDataResource.data?.statDetailList?.let { statDetailUiModelList ->
            fragmentManager?.let {
                binding.topStatViewPager.adapter = TopPlayersViewPagerAdapter(it, statDetailUiModelList)
            }
        }
        binding.executePendingBindings()
        if (topPlayerDataResource.status == Status.ERROR) {
            showErrorToast()
        }
    }

    private fun showErrorToast() {
        Toast.makeText(activity, getString(R.string.error_loading_data), Toast.LENGTH_SHORT).show()
    }
}
