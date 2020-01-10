package com.foxsports.nrlapp.playerdetail.data.repository

import com.foxsports.nrlapp.data.Resource
import com.foxsports.nrlapp.playerdetail.data.model.PlayerDetail
import com.foxsports.nrlapp.util.NetManager
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlayerDetailRepository @Inject constructor(var netManager: NetManager, var dataSource: PlayerDetailDataSource) {

    fun getPlayerDetailData(seriesId: Int, seasonId: Int, teamId: Int, playerId: Int): Single<Resource<PlayerDetail>> {
        return if (netManager.isConnectedToInternet == true) {
            dataSource.getPlayerDetailData(seriesId, seasonId, teamId, playerId)
        } else {
            Single.just(Resource.error(null))
        }
    }

}