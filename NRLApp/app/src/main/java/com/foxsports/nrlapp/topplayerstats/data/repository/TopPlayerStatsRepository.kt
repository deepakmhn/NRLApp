package com.foxsports.nrlapp.topplayerstats.data.repository

import com.foxsports.nrlapp.data.Resource
import com.foxsports.nrlapp.topplayerstats.data.model.TopPlayerStatDetail
import com.foxsports.nrlapp.util.NetManager
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TopPlayerStatsRepository @Inject constructor(var netManager: NetManager, var dataSource: TopPlayerStatsDataSource) {

    fun getMatchDetailData(matchId: String, types: List<String>, limit: Int): Single<Resource<List<TopPlayerStatDetail>>> {
        return if (netManager.isConnectedToInternet == true) {
            dataSource.getMatchDetailData(matchId, types, limit)
        } else {
            Single.just(Resource.error(null))
        }
    }

}