package com.dpfht.tmdbcleanmvp.feature_movie_reviews.di

import android.content.Context
import androidx.lifecycle.lifecycleScope
import com.dpfht.tmdbcleanmvp.framework.di.ActivityContext
import com.dpfht.tmdbcleanmvp.framework.di.module.FragmentModule
import com.dpfht.tmdbcleanmvp.framework.di.FragmentScope
import com.dpfht.tmdbcleanmvp.domain.entity.ReviewEntity
import com.dpfht.tmdbcleanmvp.domain.repository.AppRepository
import com.dpfht.tmdbcleanmvp.domain.usecase.GetMovieReviewUseCase
import com.dpfht.tmdbcleanmvp.domain.usecase.GetMovieReviewUseCaseImpl
import com.dpfht.tmdbcleanmvp.feature_movie_reviews.MovieReviewsContract
import com.dpfht.tmdbcleanmvp.feature_movie_reviews.MovieReviewsFragment
import com.dpfht.tmdbcleanmvp.feature_movie_reviews.MovieReviewsModelImpl
import com.dpfht.tmdbcleanmvp.feature_movie_reviews.MovieReviewsPresenterImpl
import com.dpfht.tmdbcleanmvp.feature_movie_reviews.adapter.MovieReviewsAdapter
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
  fun provideMovieReviewsView(): MovieReviewsContract.MovieReviewsView {
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
  fun provideMovieReviewsModel(getMovieReviewUseCase: GetMovieReviewUseCase): MovieReviewsContract.MovieReviewsModel {
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
    movieReviewsView: MovieReviewsContract.MovieReviewsView,
    movieReviewsModel: MovieReviewsContract.MovieReviewsModel,
    reviews: ArrayList<ReviewEntity>,
    scope: CoroutineScope,
    navigationService: NavigationService
  ): MovieReviewsContract.MovieReviewsPresenter {
    return MovieReviewsPresenterImpl(movieReviewsView, movieReviewsModel, reviews, scope, navigationService)
  }

  @Provides
  @FragmentScope
  fun provideMovieReviewsAdapter(reviews: ArrayList<ReviewEntity>): MovieReviewsAdapter {
    return MovieReviewsAdapter(reviews)
  }
}
