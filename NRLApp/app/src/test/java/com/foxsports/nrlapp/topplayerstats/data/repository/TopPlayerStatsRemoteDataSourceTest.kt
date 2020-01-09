package com.foxsports.nrlapp.topplayerstats.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.foxsports.nrlapp.data.Resource
import com.foxsports.nrlapp.topplayerstats.data.api.TopPlayerStatsService
import com.foxsports.nrlapp.topplayerstats.data.model.TopPlayerStatDetail
import com.foxsports.nrlapp.topplayerstats.data.model.TeamDetail
import com.foxsports.nrlapp.topplayerstats.data.model.TopPlayerDetail
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import okhttp3.ResponseBody
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import retrofit2.Response

class TopPlayerStatsRemoteDataSourceTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val service = Mockito.mock(TopPlayerStatsService::class.java)
    private var dataSource = TopPlayerStatsRemoteDataSource(service)
    private lateinit var matchDetailList: List<TopPlayerStatDetail>


    @Test
    fun getTopPlayerStatDetail_SuccessfulWhenDataAvailable() {
        Mockito.`when`(service.getMatchDetail(any(), any(), any())).thenReturn(getTopPlayerStatDetailListWithData())
        val testObserver = TestObserver<Resource<List<TopPlayerStatDetail>>>()
        dataSource.getMatchDetailData("NRL20190101", getTypeList(), 5).subscribe(testObserver)

        verify(service).getMatchDetail("NRL20190101", "fantasy_points;tackles", 5)
        testObserver.assertNoErrors().assertResult(Resource.success(matchDetailList))
    }

    @Test
    fun getTopPlayerStatDetail_ErrorWhenDataNotAvailable() {
        Mockito.`when`(service.getMatchDetail(any(), any(), any()))
            .thenReturn(Single.just(Response.error(404, Mockito.mock(ResponseBody::class.java))))
        val testObserver = TestObserver<Resource<List<TopPlayerStatDetail>>>()
        dataSource.getMatchDetailData("NRL20190101", getTypeList(), 5).subscribe(testObserver)

        verify(service).getMatchDetail("NRL20190101", "fantasy_points;tackles", 5)
        testObserver.assertNoErrors().assertResult(Resource.error(null))
    }

    private fun getTopPlayerStatDetailListWithData(): Single<Response<List<TopPlayerStatDetail>>> {
        val teamA = TeamDetail(1, "Team A", "TA", "Team1", getTopPlayerList())
        val teamB = TeamDetail(1, "Team B", "TB", "Team2", getTopPlayerList())
        val matchDetail1 = TopPlayerStatDetail("NRL20190101", teamA, teamB, "fantasy_points")
        val matchDetail2 = TopPlayerStatDetail("NRL20190101", teamA, teamB, "tackles")
        matchDetailList = listOf(matchDetail1, matchDetail2)
        return Single.just(Response.success(matchDetailList))
    }

    private fun getTopPlayerList(): List<TopPlayerDetail> {
        val player1 = TopPlayerDetail(1, "Wing", "Player 1", "P1", 100, 1)
        val player2 = TopPlayerDetail(2, "Wing", "Player 1", "P1", 100, 2)
        val player3 = TopPlayerDetail(3, "Wing", "Player 1", "P1", 100, 3)
        return listOf(player1, player2, player3)
    }

    private fun getTypeList() = listOf("fantasy_points", "tackles")

}

