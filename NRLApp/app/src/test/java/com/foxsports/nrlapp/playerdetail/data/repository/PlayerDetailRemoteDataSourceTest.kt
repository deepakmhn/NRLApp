package com.foxsports.nrlapp.playerdetail.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.foxsports.nrlapp.data.Resource
import com.foxsports.nrlapp.playerdetail.data.api.PlayerDetailService
import com.foxsports.nrlapp.playerdetail.data.model.CareerStats
import com.foxsports.nrlapp.playerdetail.data.model.LastMatchStats
import com.foxsports.nrlapp.playerdetail.data.model.PlayerDetail
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import okhttp3.ResponseBody
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import retrofit2.Response

class PlayerDetailRemoteDataSourceTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val service = Mockito.mock(PlayerDetailService::class.java)
    private var dataSource = PlayerDetailRemoteDataSource(service)
    private lateinit var playerDetail: PlayerDetail


    @Test
    fun getPlayerDetail_SuccessfulWhenDataAvailable() {
        Mockito.`when`(service.getPlayerDetail(any(), any(), any(), any())).thenReturn(getPlayerDetailListWithData())
        val testObserver = TestObserver<Resource<PlayerDetail>>()
        dataSource.getPlayerDetailData(1, 117, 55015, 113187).subscribe(testObserver)

        verify(service).getPlayerDetail(1, 117, 55015, 113187)
        testObserver.assertNoErrors().assertResult(Resource.success(playerDetail))
    }

    @Test
    fun getTopPlayerStatDetail_ErrorWhenDataNotAvailable() {
        Mockito.`when`(service.getPlayerDetail(any(), any(), any(), any()))
            .thenReturn(Single.just(Response.error(404, Mockito.mock(ResponseBody::class.java))))
        val testObserver = TestObserver<Resource<PlayerDetail>>()
        dataSource.getPlayerDetailData(1, 117, 55015, 113187).subscribe(testObserver)

        verify(service).getPlayerDetail(1, 117, 55015, 113187)
        testObserver.assertNoErrors().assertResult(Resource.error(null))
    }

    private fun getPlayerDetailListWithData(): Single<Response<PlayerDetail>> {
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
        return Single.just(Response.success(playerDetail))
    }

}