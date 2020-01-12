package com.foxsports.nrlapp.playerdetail.ui

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.foxsports.nrlapp.AppSchedulers
import com.foxsports.nrlapp.data.Resource
import com.foxsports.nrlapp.data.Status
import com.foxsports.nrlapp.playerdetail.data.model.PlayerDetail
import com.foxsports.nrlapp.playerdetail.data.repository.PlayerDetailRepository
import com.foxsports.nrlapp.util.serializeToMap
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PlayerDetailViewModel @Inject constructor(
    private var repository: PlayerDetailRepository,
    private var schedulers: AppSchedulers
) : ViewModel() {
    private val _uiModel = MutableLiveData<Resource<PlayerDetailUiModel>>()
    private val compositeDisposable = CompositeDisposable()
    private var playerDetailUiModel: PlayerDetailUiModel? = null

    val isLoading = ObservableBoolean(false)

    val uiModel: LiveData<Resource<PlayerDetailUiModel>>
        get() = _uiModel

    fun loadPlayerDetails(seriesId: Int, seasonId: Int, teamId: Int, playerId: Int) {
        isLoading.set(true)
        _uiModel.value = Resource.loading(playerDetailUiModel)
        compositeDisposable.add(
            repository.getPlayerDetailData(seriesId, seasonId, teamId, playerId)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.computation())
                .map { resource -> getPlayerDetailModelResource(resource) }
                .observeOn(schedulers.mainThread())
                .subscribe(
                    { resourceUiModel ->
                        isLoading.set(false)
                        resourceUiModel?.data?.let { playerDetailUiModel = it }
                        _uiModel.value = resourceUiModel
                    },
                    {
                        isLoading.set(false)
                        Resource.error(playerDetailUiModel)
                    })
        )
    }

    private fun getPlayerDetailModelResource(resource: Resource<PlayerDetail>): Resource<PlayerDetailUiModel> {
        return when (resource.status) {
            Status.SUCCESS -> if (resource.data != null) Resource.success(getPlayerDetailUiModel(resource.data)) else Resource.error(
                null
            )
            Status.LOADING -> Resource.loading(null)
            Status.ERROR -> Resource.error(null)
        }

    }

    private fun getPlayerDetailUiModel(playerDetail: PlayerDetail): PlayerDetailUiModel {
        val statUiModelList = arrayListOf<StatUiModel>()
        for ((key, value) in playerDetail.lastMatchStats.serializeToMap()) {
            statUiModelList.add(StatUiModel(key, (value as Double).toInt()))
        }
        return PlayerDetailUiModel(
            true,
            playerDetail.id,
            playerDetail.fullName ?: "",
            playerDetail.position ?: "",
            statUiModelList
        )
    }
}