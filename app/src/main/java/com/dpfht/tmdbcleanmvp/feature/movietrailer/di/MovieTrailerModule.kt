package com.dpfht.tmdbcleanmvp.feature.movietrailer.di

import com.dpfht.tmdbcleanmvp.core.data.repository.AppRepository
import com.dpfht.tmdbcleanmvp.framework.di.ActivityScope
import com.dpfht.tmdbcleanmvp.feature.movietrailer.MovieTrailerActivity
import com.dpfht.tmdbcleanmvp.feature.movietrailer.MovieTrailerContract.MovieTrailerModel
import com.dpfht.tmdbcleanmvp.feature.movietrailer.MovieTrailerContract.MovieTrailerPresenter
import com.dpfht.tmdbcleanmvp.feature.movietrailer.MovieTrailerContract.MovieTrailerView
import com.dpfht.tmdbcleanmvp.feature.movietrailer.MovieTrailerModelImpl
import com.dpfht.tmdbcleanmvp.feature.movietrailer.MovieTrailerPresenterImpl
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

@Module
class MovieTrailerModule(private val movieTrailerActivity: MovieTrailerActivity) {

  @Provides
  @ActivityScope
  fun provideMovieTrailerView(): MovieTrailerView {
    return movieTrailerActivity
  }

  @Provides
  @ActivityScope
  fun provideJob(): Job {
    return Job()
  }

  @Provides
  @ActivityScope
  fun provideCoroutineScope(job: Job): CoroutineScope {
    return CoroutineScope(job)
  }

  @Provides
  @ActivityScope
  fun provideMovieTrailerModel(appRepository: AppRepository): MovieTrailerModel {
    return MovieTrailerModelImpl(appRepository)
  }

  @Provides
  @ActivityScope
  fun provideMovieTrailerPresenter(
    movieTrailerView: MovieTrailerView,
    movieTrailerModel: MovieTrailerModel,
    scope: CoroutineScope
  ): MovieTrailerPresenter {
    return MovieTrailerPresenterImpl(movieTrailerView, movieTrailerModel, scope)
  }
}
