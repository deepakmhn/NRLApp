package com.foxsports.nrlapp.topplayerstats.data.repository

import com.foxsports.nrlapp.data.Resource
import com.foxsports.nrlapp.topplayerstats.data.api.TopPlayerStatsService
import com.foxsports.nrlapp.topplayerstats.data.model.TopPlayerStatDetail
import io.reactivex.Single
import javax.inject.Inject

class TopPlayerStatsRemoteDataSource @Inject constructor(private val service: TopPlayerStatsService) : TopPlayerStatsDataSource {

    override fun getMatchDetailData(matchId: String, types: List<String>, limit: Int): Single<Resource<List<TopPlayerStatDetail>>> {
        val requestTypes = types.joinToString(";")
        return service.getMatchDetail(matchId, requestTypes, limit).map { response ->
            if (response.isSuccessful) {
                response.body()?.let { Resource.success(it) } ?: Resource.error(null)
            } else {
                Resource.error(null)
            }
        }
    }

}