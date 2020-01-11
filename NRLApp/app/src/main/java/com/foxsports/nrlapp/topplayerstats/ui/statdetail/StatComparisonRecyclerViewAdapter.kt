package com.foxsports.nrlapp.topplayerstats.ui.statdetail

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.foxsports.nrlapp.databinding.StatComparisonListItemBinding
import com.foxsports.nrlapp.topplayerstats.data.model.TopPlayerDetail
import kotlinx.android.synthetic.main.stat_comparison_list_item.view.*
import java.util.*

/**
 * Adapter used in the RecyclerView which loads the album data
 */
class StatComparisonRecyclerViewAdapter(
    private var teamAPlayers: ArrayList<TopPlayerDetail>,
    private var teamBPlayers: ArrayList<TopPlayerDetail>,
    private var listener: ((TopPlayerDetail) -> Unit)?
) : RecyclerView.Adapter<StatComparisonRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = StatComparisonListItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int =
        if (teamAPlayers.size < teamBPlayers.size) teamAPlayers.size else teamBPlayers.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(teamAPlayers[position], teamBPlayers[position], listener)

    fun replaceData(newTeamAPlayers: List<TopPlayerDetail>, newTeamBPlayers: List<TopPlayerDetail>) {
        teamAPlayers.clear()
        teamAPlayers.addAll(newTeamAPlayers)
        teamBPlayers.clear()
        teamBPlayers.addAll(newTeamBPlayers)
        notifyDataSetChanged()
    }

    class ViewHolder(private var binding: StatComparisonListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            teamAPlayer: TopPlayerDetail,
            teamBPlayer: TopPlayerDetail,
            listener: ((TopPlayerDetail) -> Unit)?
        ) {
            binding.playerADetail = teamAPlayer
            binding.playerBDetail = teamBPlayer
            binding.root.team_a_player_image.setOnClickListener {
                binding.playerADetail?.let {
                    listener?.invoke(it)
                }
            }
            binding.root.team_b_player_image.setOnClickListener {
                binding.playerBDetail?.let {
                    listener?.invoke(it)
                }
            }
            binding.executePendingBindings()
        }
    }

}