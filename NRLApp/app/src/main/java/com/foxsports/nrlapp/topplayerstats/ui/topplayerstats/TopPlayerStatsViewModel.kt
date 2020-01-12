package com.foxsports.nrlapp.topplayerstats.ui.topplayerstats

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.foxsports.nrlapp.AppSchedulers
import com.foxsports.nrlapp.data.Resource
import com.foxsports.nrlapp.data.Status
import com.foxsports.nrlapp.topplayerstats.data.model.TopPlayerStatDetail
import com.foxsports.nrlapp.topplayerstats.data.repository.TopPlayerStatsRepository
import com.foxsports.nrlapp.topplayerstats.ui.statdetail.StatDetailUiModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class TopPlayerStatsViewModel @Inject constructor(
    private var repository: TopPlayerStatsRepository,
    private var schedulers: AppSchedulers
) : ViewModel() {
    private val _uiModel = MutableLiveData<Resource<TopPlayerUiModel>>()
    private val compositeDisposable = CompositeDisposable()
    private var topPlayerUiModel: TopPlayerUiModel? = null

    val isLoading = ObservableBoolean(false)

    val uiModel: LiveData<Resource<TopPlayerUiModel>>
        get() = _uiModel

    fun loadTopPlayerStats(matchId: String, types: List<String>, limit: Int) {
        isLoading.set(true)
        _uiModel.value = Resource.loading(topPlayerUiModel)
        compositeDisposable.add(
            repository.getMatchDetailData(matchId, types, limit)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.computation())
                .map { resource -> getTopPlayerUiModelResource(resource) }
                .observeOn(schedulers.mainThread())
                .subscribe(
                    { resourceUiModel ->
                        isLoading.set(false)
                        resourceUiModel?.data?.let { topPlayerUiModel = it }
                        _uiModel.value = resourceUiModel
                    },
                    {
                        isLoading.set(false)
                        Resource.error(topPlayerUiModel)
                    })
        )
    }

    private fun getTopPlayerUiModelResource(resource: Resource<List<TopPlayerStatDetail>>): Resource<TopPlayerUiModel> {
        return when (resource.status) {
            Status.SUCCESS -> if (resource.data != null) Resource.success(getTopPlayerUiModel(resource.data)) else Resource.error(
                null
            )
            Status.LOADING -> Resource.loading(null)
            Status.ERROR -> Resource.error(null)
        }
    }

    private fun getTopPlayerUiModel(topPlayerList: List<TopPlayerStatDetail>): TopPlayerUiModel {
        val statDetailList = arrayListOf<StatDetailUiModel>()
        for (topPlayerDetail in topPlayerList) {
            val statDetailUiModel = StatDetailUiModel(
                topPlayerDetail.statType ?: "",
                topPlayerDetail.teamA?.name ?: "",
                topPlayerDetail.teamB?.name ?: "",
                topPlayerDetail.teamA?.topPlayers ?: emptyList(),
                topPlayerDetail.teamB?.topPlayers ?: emptyList()
            )
            statDetailList.add(statDetailUiModel)
        }
        return TopPlayerUiModel(statDetailList)
    }
}