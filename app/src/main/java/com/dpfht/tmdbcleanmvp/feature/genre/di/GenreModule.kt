package com.dpfht.tmdbcleanmvp.feature.genre.di

import android.content.Context
import androidx.lifecycle.lifecycleScope
import com.dpfht.tmdbcleanmvp.core.data.model.remote.Genre
import com.dpfht.tmdbcleanmvp.framework.di.ActivityContext
import com.dpfht.tmdbcleanmvp.framework.di.FragmentModule
import com.dpfht.tmdbcleanmvp.framework.di.FragmentScope
import com.dpfht.tmdbcleanmvp.feature.genre.GenreContract.GenreModel
import com.dpfht.tmdbcleanmvp.feature.genre.GenreContract.GenrePresenter
import com.dpfht.tmdbcleanmvp.feature.genre.GenreContract.GenreView
import com.dpfht.tmdbcleanmvp.feature.genre.GenreFragment
import com.dpfht.tmdbcleanmvp.feature.genre.GenreModelImpl
import com.dpfht.tmdbcleanmvp.feature.genre.GenrePresenterImpl
import com.dpfht.tmdbcleanmvp.feature.genre.adapter.GenreAdapter
import com.dpfht.tmdbcleanmvp.core.domain.repository.AppRepository
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
  fun provideGenreModel(appRepository: AppRepository): GenreModel {
    return GenreModelImpl(appRepository)
  }

  @Provides
  @FragmentScope
  fun provideGenres(): ArrayList<Genre> {
    return arrayListOf()
  }

  @Provides
  @FragmentScope
  fun provideGenrePresenter(
    genreView: GenreView,
    genreModel: GenreModel,
    genres: ArrayList<Genre>,
    scope: CoroutineScope
  ): GenrePresenter {
    return GenrePresenterImpl(genreView, genreModel, genres, scope)
  }

  @Provides
  @FragmentScope
  fun provideGenreAdapter(genres: ArrayList<Genre>): GenreAdapter {
    return GenreAdapter(genres)
  }
}
