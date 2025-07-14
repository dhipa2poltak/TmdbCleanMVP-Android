package com.dpfht.tmdbcleanmvp.feature.moviereviews.di

import android.content.Context
import androidx.lifecycle.lifecycleScope
import com.dpfht.tmdbcleanmvp.framework.di.ActivityContext
import com.dpfht.tmdbcleanmvp.framework.di.module.FragmentModule
import com.dpfht.tmdbcleanmvp.framework.di.FragmentScope
import com.dpfht.tmdbcleanmvp.feature.moviereviews.MovieReviewsContract.MovieReviewsModel
import com.dpfht.tmdbcleanmvp.feature.moviereviews.MovieReviewsContract.MovieReviewsPresenter
import com.dpfht.tmdbcleanmvp.feature.moviereviews.MovieReviewsContract.MovieReviewsView
import com.dpfht.tmdbcleanmvp.feature.moviereviews.MovieReviewsFragment
import com.dpfht.tmdbcleanmvp.feature.moviereviews.MovieReviewsModelImpl
import com.dpfht.tmdbcleanmvp.feature.moviereviews.MovieReviewsPresenterImpl
import com.dpfht.tmdbcleanmvp.feature.moviereviews.adapter.MovieReviewsAdapter
import com.dpfht.tmdbcleanmvp.domain.entity.ReviewEntity
import com.dpfht.tmdbcleanmvp.domain.repository.AppRepository
import com.dpfht.tmdbcleanmvp.domain.usecase.GetMovieReviewUseCase
import com.dpfht.tmdbcleanmvp.domain.usecase.GetMovieReviewUseCaseImpl
import com.dpfht.tmdbcleanmvp.framework.navigation.NavigationService
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope

@Module(includes = [FragmentModule::class])
class MovieReviewsModule(private val movieReviewsFragment: MovieReviewsFragment) {

  @Provides
  @FragmentScope
  @ActivityContext
  fun getContext(): Context {
    return movieReviewsFragment.requireActivity()
  }

  @Provides
  @FragmentScope
  fun provideMovieReviewsView(): MovieReviewsView {
    return movieReviewsFragment
  }

  @Provides
  @FragmentScope
  fun provideCoroutineScope(): CoroutineScope {
    return movieReviewsFragment.lifecycleScope
  }

  @Provides
  @FragmentScope
  fun provideGetMovieReviewUseCase(appRepository: AppRepository): GetMovieReviewUseCase {
    return GetMovieReviewUseCaseImpl(appRepository)
  }

  @Provides
  @FragmentScope
  fun provideMovieReviewsModel(getMovieReviewUseCase: GetMovieReviewUseCase): MovieReviewsModel {
    return MovieReviewsModelImpl(getMovieReviewUseCase)
  }

  @Provides
  @FragmentScope
  fun provideReviews(): ArrayList<ReviewEntity> {
    return arrayListOf()
  }

  @Provides
  @FragmentScope
  fun provideMovieReviewsPresenter(
    movieReviewsView: MovieReviewsView,
    movieReviewsModel: MovieReviewsModel,
    reviews: ArrayList<ReviewEntity>,
    scope: CoroutineScope,
    navigationService: NavigationService
  ): MovieReviewsPresenter {
    return MovieReviewsPresenterImpl(movieReviewsView, movieReviewsModel, reviews, scope, navigationService)
  }

  @Provides
  @FragmentScope
  fun provideMovieReviewsAdapter(reviews: ArrayList<ReviewEntity>): MovieReviewsAdapter {
    return MovieReviewsAdapter(reviews)
  }
}
