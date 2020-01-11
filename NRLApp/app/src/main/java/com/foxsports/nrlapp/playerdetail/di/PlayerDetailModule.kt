package com.foxsports.nrlapp.playerdetail.di

import com.foxsports.nrlapp.AppSchedulers
import com.foxsports.nrlapp.playerdetail.data.repository.PlayerDetailRepository
import com.foxsports.nrlapp.playerdetail.ui.PlayerDetailFragment
import com.foxsports.nrlapp.playerdetail.ui.PlayerDetailViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class PlayerDetailModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        internal fun providesPlayerDetailViewModelFactory(repository: PlayerDetailRepository, schedulers: AppSchedulers)
                : PlayerDetailViewModelFactory {
            return PlayerDetailViewModelFactory(repository, schedulers)
        }
    }

    @ContributesAndroidInjector()
    internal abstract fun playerDetailFragment(): PlayerDetailFragment

}