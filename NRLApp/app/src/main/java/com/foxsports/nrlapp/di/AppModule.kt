package com.foxsports.nrlapp.di

import android.content.Context
import com.foxsports.nrlapp.AppSchedulers
import com.foxsports.nrlapp.BuildConfig
import com.foxsports.nrlapp.NrlApp
import com.foxsports.nrlapp.USER_KEY
import com.foxsports.nrlapp.data.ClientAuthInterceptor
import com.foxsports.nrlapp.topplayerstats.data.PLAYER_STATS_ENDPOINT
import com.foxsports.nrlapp.topplayerstats.data.api.TopPlayerStatsService
import com.foxsports.nrlapp.topplayerstats.data.repository.TopPlayerStatsRemoteDataSource
import com.foxsports.nrlapp.topplayerstats.data.repository.TopPlayerStatsRepository
import com.foxsports.nrlapp.topplayerstats.di.TopPlayersModule
import com.foxsports.nrlapp.util.NetManager
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module(includes = [TopPlayersModule::class])
class AppModule {

    @Provides
    fun providesContext(application: NrlApp): Context = application.applicationContext

    @Singleton
    @Provides
    fun provideAppScheduler() = AppSchedulers()

    @Singleton
    @Provides
    fun provideTopPlayerStatsRepository(
        netManager: NetManager,
        topPlayerStatsDataSource: TopPlayerStatsRemoteDataSource
    ) = TopPlayerStatsRepository(netManager, topPlayerStatsDataSource)

    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(ClientAuthInterceptor(USER_KEY)).build()

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

    @Singleton
    @Provides
    fun provideTopPlayerStatsService(client: OkHttpClient): TopPlayerStatsService =
        Retrofit.Builder()
            .baseUrl(PLAYER_STATS_ENDPOINT)
            .callFactory(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(TopPlayerStatsService::class.java)

}