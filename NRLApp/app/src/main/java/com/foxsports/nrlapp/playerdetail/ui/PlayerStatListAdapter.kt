package com.foxsports.nrlapp.playerdetail.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.foxsports.nrlapp.databinding.PlayerStatListItemBinding

class PlayerStatListAdapter(private var playerStatsList: ArrayList<StatUiModel>) :
    RecyclerView.Adapter<PlayerStatListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = PlayerStatListItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = playerStatsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(playerStatsList[position])

    fun replaceData(newPlayerStatsList: List<StatUiModel>) {
        playerStatsList.clear()
        playerStatsList.addAll(newPlayerStatsList)
        notifyDataSetChanged()
    }

    class ViewHolder(private var binding: PlayerStatListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(statUiModel: StatUiModel) {
            binding.statDetail = statUiModel
            binding.executePendingBindings()
        }
    }

}
