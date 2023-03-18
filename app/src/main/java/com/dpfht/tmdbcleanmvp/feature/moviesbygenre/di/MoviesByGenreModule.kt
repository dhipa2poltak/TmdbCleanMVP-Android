package com.dpfht.tmdbcleanmvp.feature.moviesbygenre.di

import android.content.Context
import androidx.lifecycle.lifecycleScope
import com.dpfht.tmdbcleanmvp.core.data.model.remote.Movie
import com.dpfht.tmdbcleanmvp.framework.di.ActivityContext
import com.dpfht.tmdbcleanmvp.framework.di.FragmentModule
import com.dpfht.tmdbcleanmvp.framework.di.FragmentScope
import com.dpfht.tmdbcleanmvp.feature.moviesbygenre.MoviesByGenreContract.MoviesByGenreModel
import com.dpfht.tmdbcleanmvp.feature.moviesbygenre.MoviesByGenreContract.MoviesByGenrePresenter
import com.dpfht.tmdbcleanmvp.feature.moviesbygenre.MoviesByGenreContract.MoviesByGenreView
import com.dpfht.tmdbcleanmvp.feature.moviesbygenre.MoviesByGenreFragment
import com.dpfht.tmdbcleanmvp.feature.moviesbygenre.MoviesByGenreModelImpl
import com.dpfht.tmdbcleanmvp.feature.moviesbygenre.MoviesByGenrePresenterImpl
import com.dpfht.tmdbcleanmvp.feature.moviesbygenre.adapter.MoviesByGenreAdapter
import com.dpfht.tmdbcleanmvp.core.domain.repository.AppRepository
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
  fun provideMoviesByGenreModel(appRepository: AppRepository): MoviesByGenreModel {
    return MoviesByGenreModelImpl(appRepository)
  }

  @Provides
  @FragmentScope
  fun provideMovies(): ArrayList<Movie> {
    return arrayListOf()
  }

  @Provides
  @FragmentScope
  fun provideMoviesByGenrePresenter(
    moviesByGenreView: MoviesByGenreView,
    moviesByGenreModel: MoviesByGenreModel,
    movies: ArrayList<Movie>,
    scope: CoroutineScope
  ): MoviesByGenrePresenter {
    return MoviesByGenrePresenterImpl(moviesByGenreView, moviesByGenreModel, movies, scope)
  }

  @Provides
  @FragmentScope
  fun provideMoviesByGenreAdapter(movies: ArrayList<Movie>): MoviesByGenreAdapter {
    return MoviesByGenreAdapter(movies)
  }
}
