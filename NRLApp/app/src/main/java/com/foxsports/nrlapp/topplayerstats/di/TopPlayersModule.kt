package com.foxsports.nrlapp.topplayerstats.di

import com.foxsports.nrlapp.AppSchedulers
import com.foxsports.nrlapp.topplayerstats.data.repository.TopPlayerStatsRepository
import com.foxsports.nrlapp.topplayerstats.ui.topplayerstats.TopPlayerStatsFragment
import com.foxsports.nrlapp.topplayerstats.ui.topplayerstats.TopPlayerStatsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class TopPlayersModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        internal fun providesTopPlayerStatsViewModelFactory(
            repository: TopPlayerStatsRepository,
            schedulers: AppSchedulers
        )
                : TopPlayerStatsViewModelFactory {
            return TopPlayerStatsViewModelFactory(
                repository,
                schedulers
            )
        }
    }

    @ContributesAndroidInjector()
    internal abstract fun topPlayerStatsFragment(): TopPlayerStatsFragment

}