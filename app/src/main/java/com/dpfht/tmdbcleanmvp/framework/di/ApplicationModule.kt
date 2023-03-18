package com.dpfht.tmdbcleanmvp.framework.di

import android.content.Context
import com.dpfht.tmdbcleanmvp.TheApplication
import com.dpfht.tmdbcleanmvp.core.data.repository.AppDataSource
import com.dpfht.tmdbcleanmvp.core.data.repository.AppRepository
import com.dpfht.tmdbcleanmvp.core.data.repository.AppRepositoryImpl
import com.dpfht.tmdbcleanmvp.framework.RemoteDataSourceImpl
import com.dpfht.tmdbcleanmvp.framework.rest.RestService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val theApplication: TheApplication) {

    @Provides
    @ApplicationContext
    fun provideContext(): Context {
        return theApplication
    }

    @Singleton
    @Provides
    fun provideAppDataSource(restService: RestService): AppDataSource {
        return RemoteDataSourceImpl(restService)
    }

    @Singleton
    @Provides
    fun provideAppRepository(remoteDataSource: AppDataSource): AppRepository {
        return AppRepositoryImpl(remoteDataSource)
    }
}
