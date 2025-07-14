package com.dpfht.tmdbcleanmvp.feature.genre.di

import android.content.Context
import androidx.lifecycle.lifecycleScope
import com.dpfht.tmdbcleanmvp.domain.entity.GenreEntity
import com.dpfht.tmdbcleanmvp.domain.repository.AppRepository
import com.dpfht.tmdbcleanmvp.domain.usecase.GetMovieGenreUseCase
import com.dpfht.tmdbcleanmvp.domain.usecase.GetMovieGenreUseCaseImpl
import com.dpfht.tmdbcleanmvp.framework.di.ActivityContext
import com.dpfht.tmdbcleanmvp.framework.di.module.FragmentModule
import com.dpfht.tmdbcleanmvp.framework.di.FragmentScope
import com.dpfht.tmdbcleanmvp.feature.genre.GenreContract.GenreModel
import com.dpfht.tmdbcleanmvp.feature.genre.GenreContract.GenrePresenter
import com.dpfht.tmdbcleanmvp.feature.genre.GenreContract.GenreView
import com.dpfht.tmdbcleanmvp.feature.genre.GenreFragment
import com.dpfht.tmdbcleanmvp.feature.genre.GenreModelImpl
import com.dpfht.tmdbcleanmvp.feature.genre.GenrePresenterImpl
import com.dpfht.tmdbcleanmvp.feature.genre.adapter.GenreAdapter
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
  fun provideGenreView(): GenreView {
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
  fun provideGenreModel(getMovieGenreUseCase: GetMovieGenreUseCase): GenreModel {
    return GenreModelImpl(getMovieGenreUseCase)
  }

  @Provides
  @FragmentScope
  fun provideGenres(): ArrayList<GenreEntity> {
    return arrayListOf()
  }

  @Provides
  @FragmentScope
  fun provideGenrePresenter(
    genreView: GenreView,
    genreModel: GenreModel,
    genres: ArrayList<GenreEntity>,
    scope: CoroutineScope,
    navigationService: NavigationService
  ): GenrePresenter {
    return GenrePresenterImpl(genreView, genreModel, genres, scope, navigationService)
  }

  @Provides
  @FragmentScope
  fun provideGenreAdapter(genres: ArrayList<GenreEntity>): GenreAdapter {
    return GenreAdapter(genres)
  }
}
