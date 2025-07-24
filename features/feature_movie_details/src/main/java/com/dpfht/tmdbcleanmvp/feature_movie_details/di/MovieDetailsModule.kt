package com.dpfht.tmdbcleanmvp.feature_movie_details.di

import android.content.Context
import androidx.lifecycle.lifecycleScope
import com.dpfht.tmdbcleanmvp.framework.di.ActivityContext
import com.dpfht.tmdbcleanmvp.framework.di.module.FragmentModule
import com.dpfht.tmdbcleanmvp.framework.di.FragmentScope
import com.dpfht.tmdbcleanmvp.domain.repository.AppRepository
import com.dpfht.tmdbcleanmvp.domain.usecase.GetMovieDetailsUseCase
import com.dpfht.tmdbcleanmvp.domain.usecase.GetMovieDetailsUseCaseImpl
import com.dpfht.tmdbcleanmvp.feature_movie_details.MovieDetailsContract
import com.dpfht.tmdbcleanmvp.feature_movie_details.MovieDetailsFragment
import com.dpfht.tmdbcleanmvp.feature_movie_details.MovieDetailsModelImpl
import com.dpfht.tmdbcleanmvp.feature_movie_details.MovieDetailsPresenterImpl
import com.dpfht.tmdbcleanmvp.framework.navigation.NavigationService
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope

@Module(includes = [FragmentModule::class])
class MovieDetailsModule(private val movieDetailsFragment: MovieDetailsFragment) {

  @Provides
  @FragmentScope
  @ActivityContext
  fun getContext(): Context {
    return movieDetailsFragment.requireActivity()
  }

  @Provides
  @FragmentScope
  fun provideMovieDetailsView(): MovieDetailsContract.MovieDetailsView {
    return movieDetailsFragment
  }

  @Provides
  @FragmentScope
  fun provideCoroutineScope(): CoroutineScope {
    return movieDetailsFragment.lifecycleScope
  }

  @Provides
  @FragmentScope
  fun provideGetMovieDetailsUseCase(appRepository: AppRepository): GetMovieDetailsUseCase {
    return GetMovieDetailsUseCaseImpl(appRepository)
  }

  @Provides
  @FragmentScope
  fun provideMovieDetailsModel(getMovieDetailsUseCase: GetMovieDetailsUseCase): MovieDetailsContract.MovieDetailsModel {
    return MovieDetailsModelImpl(getMovieDetailsUseCase)
  }

  @Provides
  @FragmentScope
  fun provideMovieDetailsPresenter(
    movieDetailsView: MovieDetailsContract.MovieDetailsView,
    movieDetailsModel: MovieDetailsContract.MovieDetailsModel,
    scope: CoroutineScope,
    navigationService: NavigationService
  ): MovieDetailsContract.MovieDetailsPresenter {
    return MovieDetailsPresenterImpl(movieDetailsView, movieDetailsModel, scope, navigationService)
  }
}
