package com.dpfht.tmdbcleanmvp.framework.di.module

import android.content.Context
import com.dpfht.tmdbcleanmvp.data.datasource.AppDataSource
import com.dpfht.tmdbcleanmvp.data.repository.AppRepositoryImpl
import com.dpfht.tmdbcleanmvp.domain.repository.AppRepository
import com.dpfht.tmdbcleanmvp.framework.data.datasource.remote.RemoteDataSourceImpl
import com.dpfht.tmdbcleanmvp.framework.data.datasource.remote.rest.RestService
import com.dpfht.tmdbcleanmvp.framework.di.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val context: Context) {

    @Provides
    @ApplicationContext
    fun provideContext(): Context {
        return context
    }

    @Singleton
    @Provides
    fun provideAppDataSource(@ApplicationContext context: Context, restService: RestService): AppDataSource {
        return RemoteDataSourceImpl(context, restService)
    }

    @Singleton
    @Provides
    fun provideAppRepository(remoteDataSource: AppDataSource): AppRepository {
        return AppRepositoryImpl(remoteDataSource)
    }
}
