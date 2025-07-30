package com.dpfht.tmdbcleanmvp.feature_genre.di

import android.content.Context
import androidx.lifecycle.lifecycleScope
import com.dpfht.tmdbcleanmvp.domain.model.Genre
import com.dpfht.tmdbcleanmvp.domain.repository.AppRepository
import com.dpfht.tmdbcleanmvp.domain.usecase.GetMovieGenreUseCase
import com.dpfht.tmdbcleanmvp.domain.usecase.GetMovieGenreUseCaseImpl
import com.dpfht.tmdbcleanmvp.feature_genre.GenreContract
import com.dpfht.tmdbcleanmvp.feature_genre.GenreFragment
import com.dpfht.tmdbcleanmvp.feature_genre.GenrePresenterImpl
import com.dpfht.tmdbcleanmvp.feature_genre.adapter.GenreAdapter
import com.dpfht.tmdbcleanmvp.framework.di.ActivityContext
import com.dpfht.tmdbcleanmvp.framework.di.module.FragmentModule
import com.dpfht.tmdbcleanmvp.framework.di.FragmentScope
import com.dpfht.tmdbcleanmvp.framework.navigation.NavigationService
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope

@Module(includes = [FragmentModule::class])
class GenreModule(private val genreFragment: GenreFragment) {

  @Provides
  @ActivityContext
  @FragmentScope
  fun getContext(): Context {
    return genreFragment.requireActivity()
  }

  @Provides
  @FragmentScope
  fun provideGenreView(): GenreContract.GenreView {
    return genreFragment
  }

  @Provides
  @FragmentScope
  fun provideCoroutineScope(): CoroutineScope {
    return genreFragment.lifecycleScope
  }

  @Provides
  @FragmentScope
  fun provideGetMovieGenreUseCase(appRepository: AppRepository): GetMovieGenreUseCase {
    return GetMovieGenreUseCaseImpl(appRepository)
  }

  @Provides
  @FragmentScope
  fun provideGenres(): ArrayList<Genre> {
    return arrayListOf()
  }

  @Provides
  @FragmentScope
  fun provideGenrePresenter(
      genreView: GenreContract.GenreView,
      getMovieGenreUseCase: GetMovieGenreUseCase,
      genres: ArrayList<Genre>,
      scope: CoroutineScope,
      navigationService: NavigationService
  ): GenreContract.GenrePresenter {
    return GenrePresenterImpl(genreView, getMovieGenreUseCase, genres, scope, navigationService)
  }

  @Provides
  @FragmentScope
  fun provideGenreAdapter(genres: ArrayList<Genre>): GenreAdapter {
    return GenreAdapter(genres)
  }
}
