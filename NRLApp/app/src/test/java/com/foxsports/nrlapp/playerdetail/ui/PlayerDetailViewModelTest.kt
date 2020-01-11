package com.foxsports.nrlapp.playerdetail.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.foxsports.nrlapp.AppSchedulers
import com.foxsports.nrlapp.data.Resource
import com.foxsports.nrlapp.playerdetail.data.model.CareerStats
import com.foxsports.nrlapp.playerdetail.data.model.LastMatchStats
import com.foxsports.nrlapp.playerdetail.data.model.PlayerDetail
import com.foxsports.nrlapp.playerdetail.data.repository.PlayerDetailRepository
import com.foxsports.nrlapp.playerdetail.util.SEASON_ID
import com.foxsports.nrlapp.playerdetail.util.SERIES_ID
import com.foxsports.nrlapp.playerdetail.util.TEAM_ID
import com.nhaarman.mockito_kotlin.*
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.assertj.core.api.Assertions
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class PlayerDetailViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val repository = Mockito.mock(PlayerDetailRepository::class.java)
    private val testScheduler = TestScheduler()
    private val appSchedulers = AppSchedulers(testScheduler, testScheduler, testScheduler)
    private var playerDetailViewModel = PlayerDetailViewModel(repository, appSchedulers)

    @Test
    fun loadPlayerDetail_SuccessfulWhenObserved() {
        Mockito.`when`(repository.getPlayerDetailData(any(), any(), any(), any()))
            .thenReturn(getPlayerDetailWithData())
        val observer = mock<Observer<Resource<PlayerDetailUiModel>>>()
        playerDetailViewModel.uiModel.observeForever(observer)
        playerDetailViewModel.loadPlayerDetails(SERIES_ID, SEASON_ID, TEAM_ID, 1)

        testScheduler.triggerActions()
        Mockito.verify(repository).getPlayerDetailData(SERIES_ID, SEASON_ID, TEAM_ID, 1)

        val argumentCaptor = argumentCaptor<Resource<PlayerDetailUiModel>>()
        argumentCaptor.run {
            verify(observer, times(2)).onChanged(capture())
            val (loadingState, successState) = allValues
            assertEquals(loadingState, Resource.loading(null))
            val playerDetail = successState.data
            Assertions.assertThat(playerDetail?.playerName).isEqualTo("Top Player")
            Assertions.assertThat(playerDetail?.position).isEqualTo("Fullback")
            Assertions.assertThat(playerDetail?.playerId).isEqualTo(1)
            Assertions.assertThat(playerDetail?.lastMatchStats?.size).isEqualTo(66)
        }
    }

    @Test
    fun loadPlayerDetail_ErrorWhenObserved() {
        Mockito.`when`(repository.getPlayerDetailData(any(), any(), any(), any()))
            .thenReturn(Single.just(Resource.error(null)))
        val observer = mock<Observer<Resource<PlayerDetailUiModel>>>()
        playerDetailViewModel.uiModel.observeForever(observer)
        playerDetailViewModel.loadPlayerDetails(SERIES_ID, SEASON_ID, TEAM_ID, 1)

        testScheduler.triggerActions()
        Mockito.verify(repository).getPlayerDetailData(SERIES_ID, SEASON_ID, TEAM_ID, 1)

        val argumentCaptor = argumentCaptor<Resource<PlayerDetailUiModel>>()
        argumentCaptor.run {
            verify(observer, times(2)).onChanged(capture())
            val (loadingState, errorState) = allValues
            assertEquals(loadingState, Resource.loading(null))
            assertEquals(errorState, Resource.error(null))
        }
    }

    private fun getPlayerDetailWithData(): Single<Resource<PlayerDetail>> {
        val careerStats = CareerStats(40, 60, 15, 61.5f)
        val lastMatchStats = LastMatchStats(
            1, 2, 3, 3, 4, 5, 5, 6, 7, 8, 9, 0,
            1, 2, 3, 3, 4, 5, 5, 6, 7, 8, 9, 0,
            1, 2, 3, 3, 4, 5, 5, 6, 7, 8, 9, 0,
            1, 2, 3, 3, 4, 5, 5, 6, 7, 8, 9, 0,
            1, 2, 3, 3, 4, 5, 5, 6, 7, 8, 9, 0,
            1, 2, 3, 3, 4, 5
        )
        val playerDetail = PlayerDetail(
            1, "Player", "Fullback", "Top Player", "T Player", "1994-10-08",
            183, "T P", 100, "NRL20192802", careerStats, lastMatchStats
        )
        return Single.just(Resource.success(playerDetail))
    }

}