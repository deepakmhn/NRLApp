package com.foxsports.nrlapp.topplayerstats.data.repository

import com.foxsports.nrlapp.data.Resource
import com.foxsports.nrlapp.topplayerstats.data.model.TopPlayerStatDetail
import io.reactivex.Single

interface TopPlayerStatsDataSource {
    fun getMatchDetailData(matchId: String, types: List<String>, limit: Int): Single<Resource<List<TopPlayerStatDetail>>>
}