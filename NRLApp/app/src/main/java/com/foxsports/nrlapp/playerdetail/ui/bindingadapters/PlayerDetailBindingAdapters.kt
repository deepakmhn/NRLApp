package com.foxsports.nrlapp.playerdetail.ui.bindingadapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.foxsports.nrlapp.playerdetail.ui.PlayerStatListAdapter
import com.foxsports.nrlapp.playerdetail.ui.StatUiModel


@BindingAdapter("app:statsList")
fun setRecyclerViewPlayerStatsData(
    recyclerView: RecyclerView,
    statsList: List<StatUiModel>?
) {
    if (recyclerView.adapter is PlayerStatListAdapter && statsList != null) {
        (recyclerView.adapter as PlayerStatListAdapter).replaceData(statsList)
    }
}