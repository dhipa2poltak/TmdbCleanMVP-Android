package com.dpfht.tmdbcleanmvp.feature.moviedetails.di

import android.content.Context
import androidx.lifecycle.lifecycleScope
import com.dpfht.tmdbcleanmvp.framework.di.ActivityContext
import com.dpfht.tmdbcleanmvp.framework.di.module.FragmentModule
import com.dpfht.tmdbcleanmvp.framework.di.FragmentScope
import com.dpfht.tmdbcleanmvp.feature.moviedetails.MovieDetailsContract.MovieDetailsModel
import com.dpfht.tmdbcleanmvp.feature.moviedetails.MovieDetailsContract.MovieDetailsPresenter
import com.dpfht.tmdbcleanmvp.feature.moviedetails.MovieDetailsContract.MovieDetailsView
import com.dpfht.tmdbcleanmvp.feature.moviedetails.MovieDetailsFragment
import com.dpfht.tmdbcleanmvp.feature.moviedetails.MovieDetailsModelImpl
import com.dpfht.tmdbcleanmvp.feature.moviedetails.MovieDetailsPresenterImpl
import com.dpfht.tmdbcleanmvp.domain.repository.AppRepository
import com.dpfht.tmdbcleanmvp.domain.usecase.GetMovieDetailsUseCase
import com.dpfht.tmdbcleanmvp.domain.usecase.GetMovieDetailsUseCaseImpl
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
  fun provideMovieDetailsView(): MovieDetailsView {
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
  fun provideMovieDetailsModel(getMovieDetailsUseCase: GetMovieDetailsUseCase): MovieDetailsModel {
    return MovieDetailsModelImpl(getMovieDetailsUseCase)
  }

  @Provides
  @FragmentScope
  fun provideMovieDetailsPresenter(
    movieDetailsView: MovieDetailsView,
    movieDetailsModel: MovieDetailsModel,
    scope: CoroutineScope,
    navigationService: NavigationService
  ): MovieDetailsPresenter {
    return MovieDetailsPresenterImpl(movieDetailsView, movieDetailsModel, scope, navigationService)
  }
}
