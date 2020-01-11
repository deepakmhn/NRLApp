package com.foxsports.nrlapp.topplayerstats.ui.topplayerstats

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.foxsports.nrlapp.AppSchedulers
import com.foxsports.nrlapp.data.Resource
import com.foxsports.nrlapp.topplayerstats.util.MATCH_ID
import com.foxsports.nrlapp.topplayerstats.util.STAT_TYPE_LIST
import com.foxsports.nrlapp.topplayerstats.util.TOP_PLAYER_LIMIT
import com.foxsports.nrlapp.topplayerstats.data.model.TeamDetail
import com.foxsports.nrlapp.topplayerstats.data.model.TopPlayerDetail
import com.foxsports.nrlapp.topplayerstats.data.model.TopPlayerStatDetail
import com.foxsports.nrlapp.topplayerstats.data.repository.TopPlayerStatsRepository
import com.nhaarman.mockito_kotlin.*
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.assertj.core.api.Assertions
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class TopPlayerStatsViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val repository = Mockito.mock(TopPlayerStatsRepository::class.java)
    private val testScheduler = TestScheduler()
    private val appSchedulers = AppSchedulers(testScheduler, testScheduler, testScheduler)
    private var playerStatsViewModel = TopPlayerStatsViewModel(repository, appSchedulers)

    @Test
    fun loadStatDetailList_SuccessfulWhenObserved() {
        Mockito.`when`(repository.getMatchDetailData(any(), any(), any())).thenReturn(getTopPlayerStatDetailListWithData())
        val observer = mock<Observer<Resource<TopPlayerUiModel>>>()
        playerStatsViewModel.uiModel.observeForever(observer)
        playerStatsViewModel.loadTopPlayerStats(MATCH_ID, STAT_TYPE_LIST, TOP_PLAYER_LIMIT)

        testScheduler.triggerActions()
        Mockito.verify(repository).getMatchDetailData(MATCH_ID, STAT_TYPE_LIST, TOP_PLAYER_LIMIT)

        val argumentCaptor = argumentCaptor<Resource<TopPlayerUiModel>>()
        argumentCaptor.run {
            verify(observer, times(2)).onChanged(capture())
            val (loadingState, successState) = allValues
            assertEquals(loadingState, Resource.loading(null))
            val topPlayerStatsList = successState.data?.statDetailList
            Assertions.assertThat(topPlayerStatsList?.size).isEqualTo(2)
            Assertions.assertThat(topPlayerStatsList?.get(0)?.statType).isEqualTo("fantasy_points")
            Assertions.assertThat(topPlayerStatsList?.get(0)?.teamATopPlayers?.size).isEqualTo(3)
            Assertions.assertThat(topPlayerStatsList?.get(0)?.teamATopPlayers?.get(0)?.fullName).isEqualTo("Player 1")
            Assertions.assertThat(topPlayerStatsList?.get(0)?.teamBTopPlayers?.size).isEqualTo(3)
            Assertions.assertThat(topPlayerStatsList?.get(1)?.statType).isEqualTo("tackles")
            Assertions.assertThat(topPlayerStatsList?.get(1)?.teamATopPlayers?.size).isEqualTo(3)
            Assertions.assertThat(topPlayerStatsList?.get(1)?.teamBTopPlayers?.size).isEqualTo(3)
        }
    }

    @Test
    fun loadStatDetailList_ErrorWhenObserved() {
        Mockito.`when`(repository.getMatchDetailData(any(), any(), any())).thenReturn(Single.just(Resource.error(null)))
        val observer = mock<Observer<Resource<TopPlayerUiModel>>>()
        playerStatsViewModel.uiModel.observeForever(observer)
        playerStatsViewModel.loadTopPlayerStats(MATCH_ID, STAT_TYPE_LIST, TOP_PLAYER_LIMIT)

        testScheduler.triggerActions()
        Mockito.verify(repository).getMatchDetailData(MATCH_ID, STAT_TYPE_LIST, TOP_PLAYER_LIMIT)

        val argumentCaptor = argumentCaptor<Resource<TopPlayerUiModel>>()
        argumentCaptor.run {
            verify(observer, times(2)).onChanged(capture())
            val (loadingState, errorState) = allValues
            assertEquals(loadingState, Resource.loading(null))
            assertEquals(errorState, Resource.error(null))
        }
    }

    private fun getTopPlayerStatDetailListWithData(): Single<Resource<List<TopPlayerStatDetail>>> {
        val teamA = TeamDetail(1, "Team A", "TA", "Team1", getTopPlayerList())
        val teamB = TeamDetail(1, "Team B", "TB", "Team2", getTopPlayerList())
        val topPlayerStatDetail1 = TopPlayerStatDetail("NRL20190101", teamA, teamB, "fantasy_points")
        val topPlayerStatDetail2 = TopPlayerStatDetail("NRL20190101", teamA, teamB, "tackles")
        val toPlayerStatsList = listOf(topPlayerStatDetail1, topPlayerStatDetail2)
        return Single.just(Resource.success(toPlayerStatsList))
    }

    private fun getTopPlayerList(): List<TopPlayerDetail> {
        val player1 = TopPlayerDetail(1, "Wing", "Player 1", "P1", 100, 1)
        val player2 = TopPlayerDetail(2, "Wing", "Player 2", "P2", 100, 2)
        val player3 = TopPlayerDetail(3, "Wing", "Player 3", "P3", 100, 3)
        return listOf(player1, player2, player3)
    }
}