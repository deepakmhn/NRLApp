package com.foxsports.nrlapp.di

import android.content.Context
import com.foxsports.nrlapp.AppSchedulers
import com.foxsports.nrlapp.NrlApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    fun providesContext(application: NrlApp): Context = application.applicationContext

    @Singleton
    @Provides
    fun provideAppScheduler() = AppSchedulers()

}