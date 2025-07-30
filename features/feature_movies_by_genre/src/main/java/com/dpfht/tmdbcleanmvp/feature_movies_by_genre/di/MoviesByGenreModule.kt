package com.dpfht.tmdbcleanmvp.feature_movies_by_genre.di

import android.content.Context
import androidx.lifecycle.lifecycleScope
import com.dpfht.tmdbcleanmvp.framework.di.ActivityContext
import com.dpfht.tmdbcleanmvp.framework.di.module.FragmentModule
import com.dpfht.tmdbcleanmvp.framework.di.FragmentScope
import com.dpfht.tmdbcleanmvp.domain.model.Movie
import com.dpfht.tmdbcleanmvp.domain.repository.AppRepository
import com.dpfht.tmdbcleanmvp.domain.usecase.GetMovieByGenreUseCase
import com.dpfht.tmdbcleanmvp.domain.usecase.GetMovieByGenreUseCaseImpl
import com.dpfht.tmdbcleanmvp.feature_movies_by_genre.MoviesByGenreContract
import com.dpfht.tmdbcleanmvp.feature_movies_by_genre.MoviesByGenreFragment
import com.dpfht.tmdbcleanmvp.feature_movies_by_genre.MoviesByGenrePresenterImpl
import com.dpfht.tmdbcleanmvp.feature_movies_by_genre.adapter.MoviesByGenreAdapter
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
  fun provideMoviesByGenreView(): MoviesByGenreContract.MoviesByGenreView {
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
  fun provideMovies(): ArrayList<Movie> {
    return arrayListOf()
  }

  @Provides
  @FragmentScope
  fun provideMoviesByGenrePresenter(
      moviesByGenreView: MoviesByGenreContract.MoviesByGenreView,
      getMovieByGenreUseCase: GetMovieByGenreUseCase,
      movies: ArrayList<Movie>,
      scope: CoroutineScope,
      navigationService: NavigationService
  ): MoviesByGenreContract.MoviesByGenrePresenter {
    return MoviesByGenrePresenterImpl(moviesByGenreView, getMovieByGenreUseCase, movies, scope, navigationService)
  }

  @Provides
  @FragmentScope
  fun provideMoviesByGenreAdapter(movies: ArrayList<Movie>): MoviesByGenreAdapter {
    return MoviesByGenreAdapter(movies)
  }
}
