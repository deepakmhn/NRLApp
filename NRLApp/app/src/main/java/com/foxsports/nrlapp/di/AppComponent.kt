package com.foxsports.nrlapp.di

import com.foxsports.nrlapp.NrlApp
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class])
interface AppComponent : AndroidInjector<NrlApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<NrlApp>()
}