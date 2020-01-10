package com.foxsports.nrlapp.playerdetail.data.api

import com.foxsports.nrlapp.playerdetail.data.model.PlayerDetail
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Retrofit service interface used to fetch match detail service data from the REST service
 */
interface PlayerDetailService {

    @GET("league/series/{seriesId}/seasons/{seasonId}/teams/{teamId}/players/{playerId}/detailedstats.json")
    fun getPlayerDetail(
        @Path("seriesId") seriesId: Int,
        @Path("seasonId") seasonId: Int,
        @Path("teamId") teamId: Int,
        @Path("playerId") playerId: Int
    ): Single<Response<PlayerDetail>>
}