package com.foxsports.nrlapp.playerdetail.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.foxsports.nrlapp.data.Resource
import com.foxsports.nrlapp.playerdetail.data.model.CareerStats
import com.foxsports.nrlapp.playerdetail.data.model.LastMatchStats
import com.foxsports.nrlapp.playerdetail.data.model.PlayerDetail
import com.foxsports.nrlapp.util.NetManager
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class PlayerDetailRepositoryTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val netManager = Mockito.mock(NetManager::class.java)
    private val dataSource = Mockito.mock(PlayerDetailRemoteDataSource::class.java)
    private var playerDetailRepository = PlayerDetailRepository(netManager, dataSource)
    private lateinit var playerDetail: PlayerDetail

    @Test
    fun getPlayerDetail_SuccessfulWhenNetworkConnected() {
        Mockito.`when`(netManager.isConnectedToInternet).thenReturn(true)
        Mockito.`when`(dataSource.getPlayerDetailData(any(), any(), any(), any()))
            .thenReturn(getPlayerDetailListWithData())
        val testObserver = TestObserver<Resource<PlayerDetail>>()
        playerDetailRepository.getPlayerDetailData(1, 117, 55015, 113187).subscribe(testObserver)

        verify(dataSource).getPlayerDetailData(1, 117, 55015, 113187)
        testObserver.assertResult(Resource.success(playerDetail))
    }

    @Test
    fun getPlayerDetail_ErrorWhenObserved() {
        Mockito.`when`(netManager.isConnectedToInternet).thenReturn(false)
        Mockito.`when`(dataSource.getPlayerDetailData(any(), any(), any(), any()))
            .thenReturn(getPlayerDetailListWithData())
        val testObserver = TestObserver<Resource<PlayerDetail>>()
        playerDetailRepository.getPlayerDetailData(1, 117, 55015, 113187).subscribe(testObserver)

        verify(dataSource, Mockito.never()).getPlayerDetailData(any(), any(), any(), any())
        testObserver.assertResult(Resource.error(null))
    }

    private fun getPlayerDetailListWithData(): Single<Resource<PlayerDetail>> {
        val careerStats = CareerStats(100, 50, 20, 50.5f)
        val lastMatchStats = LastMatchStats(
            1, 2, 3, 4, 5, 6, 7, 8, 9, 0,
            1, 2, 3, 4, 5, 6, 7, 8, 9, 0,
            1, 2, 3, 4, 5, 6, 7, 8, 9, 0,
            1, 2, 3, 4, 5, 6, 7, 8, 9, 0,
            1, 2, 3, 4, 5, 6, 7, 8, 9, 0,
            1, 2, 3, 4, 5, 6, 7, 8, 9, 0,
            1, 2, 3, 4, 5, 6
        )
        playerDetail = PlayerDetail(
            1, "Player", "Fullback", "Player A", "P A",
            "1994-10-08", 180, "P", 90, "NRL20192802", careerStats, lastMatchStats
        )
        return Single.just(Resource.success(playerDetail))
    }
}