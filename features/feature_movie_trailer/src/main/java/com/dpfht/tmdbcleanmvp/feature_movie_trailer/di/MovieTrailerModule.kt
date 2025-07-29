package com.dpfht.tmdbcleanmvp.feature_movie_trailer.di

import com.dpfht.tmdbcleanmvp.domain.repository.AppRepository
import com.dpfht.tmdbcleanmvp.domain.usecase.GetMovieTrailerUseCase
import com.dpfht.tmdbcleanmvp.domain.usecase.GetMovieTrailerUseCaseImpl
import com.dpfht.tmdbcleanmvp.feature_movie_trailer.MovieTrailerActivity
import com.dpfht.tmdbcleanmvp.feature_movie_trailer.MovieTrailerContract
import com.dpfht.tmdbcleanmvp.feature_movie_trailer.MovieTrailerPresenterImpl
import com.dpfht.tmdbcleanmvp.framework.di.ActivityScope
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

@Module
class MovieTrailerModule(private val movieTrailerActivity: MovieTrailerActivity) {

  @Provides
  @ActivityScope
  fun provideMovieTrailerView(): MovieTrailerContract.MovieTrailerView {
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
  fun provideGetMovieTrailerUseCase(appRepository: AppRepository): GetMovieTrailerUseCase {
    return GetMovieTrailerUseCaseImpl(appRepository)
  }

  @Provides
  @ActivityScope
  fun provideMovieTrailerPresenter(
    movieTrailerView: MovieTrailerContract.MovieTrailerView,
    getMovieTrailerUseCase: GetMovieTrailerUseCase,
    scope: CoroutineScope
  ): MovieTrailerContract.MovieTrailerPresenter {
    return MovieTrailerPresenterImpl(movieTrailerView, getMovieTrailerUseCase, scope)
  }
}
