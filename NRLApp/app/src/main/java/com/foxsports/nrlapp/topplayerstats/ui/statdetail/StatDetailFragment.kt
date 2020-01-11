package com.foxsports.nrlapp.topplayerstats.ui.statdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.foxsports.nrlapp.databinding.StatDetailFragmentBinding
import com.foxsports.nrlapp.topplayerstats.ui.topplayerstats.TopPlayerStatsFragmentDirections

class StatDetailFragment : Fragment() {

    lateinit var binding: StatDetailFragmentBinding
    private val statComparisonAdapter =
        StatComparisonRecyclerViewAdapter(arrayListOf(), arrayListOf()) { playerDetail ->
            findNavController().navigate(TopPlayerStatsFragmentDirections.playerDetailAction(playerDetail), null)
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = StatDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getStatDetail(this)?.let { statDetailModel ->
            binding.statDetail = statDetailModel
            binding.topStatComparisonRv.adapter = statComparisonAdapter
            binding.executePendingBindings()
        }
    }

    companion object {
        private const val STAT_DETAIL = "STAT_DETAIL"
        fun createFragment(statDetail: StatDetailUiModel) =
            StatDetailFragment().apply { arguments = Bundle().apply { putParcelable(STAT_DETAIL, statDetail) } }

        fun getStatDetail(fragment: StatDetailFragment) =
            fragment.arguments?.getParcelable(STAT_DETAIL) as? StatDetailUiModel

    }
}