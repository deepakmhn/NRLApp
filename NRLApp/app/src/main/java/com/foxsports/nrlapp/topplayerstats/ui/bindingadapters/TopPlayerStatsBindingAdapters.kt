package com.foxsports.nrlapp.topplayerstats.ui.bindingadapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.foxsports.nrlapp.HEADSHOT_IMAGE_URL
import com.foxsports.nrlapp.topplayerstats.data.model.TopPlayerDetail
import com.foxsports.nrlapp.topplayerstats.ui.statdetail.StatComparisonRecyclerViewAdapter

@BindingAdapter("teamAPlayers", "teamBPlayers")
fun setRecyclerViewPlayersData(
    recyclerView: RecyclerView,
    teamAPlayers: List<TopPlayerDetail>?,
    teamBPlayers: List<TopPlayerDetail>?
) {
    if (recyclerView.adapter is StatComparisonRecyclerViewAdapter && teamAPlayers != null && teamBPlayers != null) {
        (recyclerView.adapter as StatComparisonRecyclerViewAdapter).replaceData(teamAPlayers, teamBPlayers)
    }
}

@BindingAdapter("app:playerId")
fun setImageForPlayerId(view: ImageView, playerId: Int?) {
    playerId?.let {
        val url = "$HEADSHOT_IMAGE_URL$playerId.jpg"
        Glide.with(view.context).load(url).into(view)
    }
}

@BindingAdapter("app:statType")
fun setImageForPlayerId(view: TextView, playerId: String?) {
    playerId?.let {
        view.text = it.replace("_", " ").capitalize()
    }
}