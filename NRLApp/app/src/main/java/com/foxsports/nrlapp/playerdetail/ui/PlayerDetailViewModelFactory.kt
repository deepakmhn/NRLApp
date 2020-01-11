package com.foxsports.nrlapp.playerdetail.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.foxsports.nrlapp.AppSchedulers
import com.foxsports.nrlapp.playerdetail.data.repository.PlayerDetailRepository

/**
 * Factory for providing the TopPlayerStatsViewModel with parameters
 */
class PlayerDetailViewModelFactory(
    private val repository: PlayerDetailRepository,
    private var schedulers: AppSchedulers
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlayerDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PlayerDetailViewModel(repository, schedulers) as T
        }

        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}