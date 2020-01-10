package com.foxsports.nrlapp.playerdetail.data.repository

import com.foxsports.nrlapp.data.Resource
import com.foxsports.nrlapp.playerdetail.data.api.PlayerDetailService
import com.foxsports.nrlapp.playerdetail.data.model.PlayerDetail
import io.reactivex.Single
import javax.inject.Inject

class PlayerDetailRemoteDataSource @Inject constructor(private val service: PlayerDetailService) :
    PlayerDetailDataSource {

    override fun getPlayerDetailData(
        seriesId: Int,
        seasonId: Int,
        teamId: Int,
        playerId: Int
    ): Single<Resource<PlayerDetail>> {
        return service.getPlayerDetail(seriesId, seasonId, teamId, playerId).map { response ->
            if (response.isSuccessful) {
                response.body()?.let { Resource.success(it) } ?: Resource.error(null)
            } else {
                Resource.error(null)
            }
        }
    }

}