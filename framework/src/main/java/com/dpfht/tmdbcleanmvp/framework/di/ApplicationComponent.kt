package com.dpfht.tmdbcleanmvp.framework.di

import com.dpfht.tmdbcleanmvp.domain.repository.AppRepository
import com.dpfht.tmdbcleanmvp.framework.di.module.ApplicationModule
import com.dpfht.tmdbcleanmvp.framework.di.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class])
interface ApplicationComponent {

    //fun getRestApiService(): RestService
    fun getAppRepository(): AppRepository
}
