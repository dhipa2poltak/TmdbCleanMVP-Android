package com.dpfht.tmdbcleanmvp.framework.di

import com.dpfht.tmdbcleanmvp.core.domain.repository.AppRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class])
interface ApplicationComponent {

    //fun getRestApiService(): RestService
    fun getAppRepository(): AppRepository
}