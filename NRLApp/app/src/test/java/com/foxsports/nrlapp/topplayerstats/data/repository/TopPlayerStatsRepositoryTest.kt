package com.foxsports.nrlapp.topplayerstats.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.foxsports.nrlapp.data.Resource
import com.foxsports.nrlapp.topplayerstats.data.model.TopPlayerStatDetail
import com.foxsports.nrlapp.topplayerstats.data.model.TeamDetail
import com.foxsports.nrlapp.topplayerstats.data.model.TopPlayerDetail
import com.foxsports.nrlapp.util.NetManager
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class TopPlayerStatsRepositoryTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val netManager = Mockito.mock(NetManager::class.java)
    private val dataSource = Mockito.mock(TopPlayerStatsRemoteDataSource::class.java)
    private var matchDetailRepository = TopPlayerStatsRepository(netManager, dataSource)
    private lateinit var matchDetailList: List<TopPlayerStatDetail>

    @Test
    fun getTopPlayerStatDetail_SuccessfulWhenNetworkConnected() {
        Mockito.`when`(netManager.isConnectedToInternet).thenReturn(true)
        Mockito.`when`(dataSource.getMatchDetailData(any(), any(), any())).thenReturn(getTopPlayerStatDetailListWithData())
        val testObserver = TestObserver<Resource<List<TopPlayerStatDetail>>>()
        matchDetailRepository.getMatchDetailData("NRL20190101", getTypeList(), 5).subscribe(testObserver)

        verify(dataSource).getMatchDetailData(any(), any(), any())
        testObserver.assertResult(Resource.success(matchDetailList))
    }

    @Test
    fun getTopPlayerStatDetail_ErrorWhenObserved() {
        Mockito.`when`(netManager.isConnectedToInternet).thenReturn(false)
        Mockito.`when`(dataSource.getMatchDetailData(any(), any(), any())).thenReturn(getTopPlayerStatDetailListWithData())
        val testObserver = TestObserver<Resource<List<TopPlayerStatDetail>>>()
        matchDetailRepository.getMatchDetailData("NRL20190101", getTypeList(), 5).subscribe(testObserver)

        verify(dataSource, Mockito.never()).getMatchDetailData(any(), any(), any())
        testObserver.assertResult(Resource.error(null))
    }

    private fun getTopPlayerStatDetailListWithData(): Single<Resource<List<TopPlayerStatDetail>>> {
        val teamA = TeamDetail(1, "Team A", "TA", "Team1", getTopPlayerList())
        val teamB = TeamDetail(1, "Team B", "TB", "Team2", getTopPlayerList())
        val matchDetail1 = TopPlayerStatDetail("NRL20190101", teamA, teamB, "fantasy_points")
        val matchDetail2 = TopPlayerStatDetail("NRL20190101", teamA, teamB, "tackles")
        matchDetailList = listOf(matchDetail1, matchDetail2)
        return Single.just(Resource.success(matchDetailList))
    }

    private fun getTopPlayerList(): List<TopPlayerDetail> {
        val player1 = TopPlayerDetail(1, "Wing", "Player 1", "P1", 100, 1)
        val player2 = TopPlayerDetail(2, "Wing", "Player 1", "P1", 100, 2)
        val player3 = TopPlayerDetail(3, "Wing", "Player 1", "P1", 100, 3)
        return listOf(player1, player2, player3)
    }

    private fun getTypeList() = listOf("fantasy_points", "tackles")
}
