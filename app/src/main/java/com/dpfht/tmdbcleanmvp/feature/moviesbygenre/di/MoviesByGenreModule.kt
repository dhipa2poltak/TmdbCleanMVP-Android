package com.dpfht.tmdbcleanmvp.feature.moviesbygenre.di

import android.content.Context
import androidx.lifecycle.lifecycleScope
import com.dpfht.tmdbcleanmvp.framework.di.ActivityContext
import com.dpfht.tmdbcleanmvp.framework.di.module.FragmentModule
import com.dpfht.tmdbcleanmvp.framework.di.FragmentScope
import com.dpfht.tmdbcleanmvp.feature.moviesbygenre.MoviesByGenreContract.MoviesByGenreModel
import com.dpfht.tmdbcleanmvp.feature.moviesbygenre.MoviesByGenreContract.MoviesByGenrePresenter
import com.dpfht.tmdbcleanmvp.feature.moviesbygenre.MoviesByGenreContract.MoviesByGenreView
import com.dpfht.tmdbcleanmvp.feature.moviesbygenre.MoviesByGenreFragment
import com.dpfht.tmdbcleanmvp.feature.moviesbygenre.MoviesByGenreModelImpl
import com.dpfht.tmdbcleanmvp.feature.moviesbygenre.MoviesByGenrePresenterImpl
import com.dpfht.tmdbcleanmvp.feature.moviesbygenre.adapter.MoviesByGenreAdapter
import com.dpfht.tmdbcleanmvp.domain.entity.MovieEntity
import com.dpfht.tmdbcleanmvp.domain.repository.AppRepository
import com.dpfht.tmdbcleanmvp.domain.usecase.GetMovieByGenreUseCase
import com.dpfht.tmdbcleanmvp.domain.usecase.GetMovieByGenreUseCaseImpl
import com.dpfht.tmdbcleanmvp.framework.navigation.NavigationService
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope

@Module(includes = [FragmentModule::class])
class MoviesByGenreModule(private val moviesByGenreFragment: MoviesByGenreFragment) {

  @Provides
  @FragmentScope
  @ActivityContext
  fun getContext(): Context {
    return moviesByGenreFragment.requireActivity()
  }

  @Provides
  @FragmentScope
  fun provideMoviesByGenreView(): MoviesByGenreView {
    return moviesByGenreFragment
  }

  @Provides
  @FragmentScope
  fun provideCoroutineScope(): CoroutineScope {
    return moviesByGenreFragment.lifecycleScope
  }

  @Provides
  @FragmentScope
  fun provideGetMovieByGenreUseCase(appRepository: AppRepository): GetMovieByGenreUseCase {
    return GetMovieByGenreUseCaseImpl(appRepository)
  }

  @Provides
  @FragmentScope
  fun provideMoviesByGenreModel(getMovieByGenreUseCase: GetMovieByGenreUseCase): MoviesByGenreModel {
    return MoviesByGenreModelImpl(getMovieByGenreUseCase)
  }

  @Provides
  @FragmentScope
  fun provideMovies(): ArrayList<MovieEntity> {
    return arrayListOf()
  }

  @Provides
  @FragmentScope
  fun provideMoviesByGenrePresenter(
    moviesByGenreView: MoviesByGenreView,
    moviesByGenreModel: MoviesByGenreModel,
    movies: ArrayList<MovieEntity>,
    scope: CoroutineScope,
    navigationService: NavigationService
  ): MoviesByGenrePresenter {
    return MoviesByGenrePresenterImpl(moviesByGenreView, moviesByGenreModel, movies, scope, navigationService)
  }

  @Provides
  @FragmentScope
  fun provideMoviesByGenreAdapter(movies: ArrayList<MovieEntity>): MoviesByGenreAdapter {
    return MoviesByGenreAdapter(movies)
  }
}
