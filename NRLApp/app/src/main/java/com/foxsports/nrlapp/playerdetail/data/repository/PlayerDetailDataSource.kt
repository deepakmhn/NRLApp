package com.foxsports.nrlapp.playerdetail.data.repository

import com.foxsports.nrlapp.data.Resource
import com.foxsports.nrlapp.playerdetail.data.model.PlayerDetail
import io.reactivex.Single


interface PlayerDetailDataSource {
    fun getPlayerDetailData(seriesId: Int, seasonId: Int, teamId: Int, playerId: Int): Single<Resource<PlayerDetail>>
}