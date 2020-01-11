package com.foxsports.nrlapp.topplayerstats.ui.topplayerstats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.foxsports.nrlapp.AppSchedulers
import com.foxsports.nrlapp.topplayerstats.data.repository.TopPlayerStatsRepository

/**
 * Factory for providing the TopPlayerStatsViewModel with parameters
 */
class TopPlayerStatsViewModelFactory(
    private val repository: TopPlayerStatsRepository,
    private var schedulers: AppSchedulers
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TopPlayerStatsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TopPlayerStatsViewModel(
                repository,
                schedulers
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}