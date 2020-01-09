package com.foxsports.nrlapp.topplayerstats.data.api

import com.foxsports.nrlapp.topplayerstats.data.model.TopPlayerStatDetail
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.Response

/**
 * Retrofit service interface used to fetch match detail service data from the REST service
 */
interface TopPlayerStatsService {
    @GET("/matches/{matchId}/topplayerstats.json;{types}")
    fun getMatchDetail(
        @Path("matchId") matchId: String,
        @Path("types") types: String,
        @Query("limit") limit: Int
    ): Single<Response<List<TopPlayerStatDetail>>>
}