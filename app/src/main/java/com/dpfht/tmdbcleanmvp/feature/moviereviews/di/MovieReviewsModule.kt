package com.dpfht.tmdbcleanmvp.feature.moviereviews.di

import android.content.Context
import androidx.lifecycle.lifecycleScope
import com.dpfht.tmdbcleanmvp.core.data.model.remote.Review
import com.dpfht.tmdbcleanmvp.framework.di.ActivityContext
import com.dpfht.tmdbcleanmvp.framework.di.FragmentModule
import com.dpfht.tmdbcleanmvp.framework.di.FragmentScope
import com.dpfht.tmdbcleanmvp.feature.moviereviews.MovieReviewsContract.MovieReviewsModel
import com.dpfht.tmdbcleanmvp.feature.moviereviews.MovieReviewsContract.MovieReviewsPresenter
import com.dpfht.tmdbcleanmvp.feature.moviereviews.MovieReviewsContract.MovieReviewsView
import com.dpfht.tmdbcleanmvp.feature.moviereviews.MovieReviewsFragment
import com.dpfht.tmdbcleanmvp.feature.moviereviews.MovieReviewsModelImpl
import com.dpfht.tmdbcleanmvp.feature.moviereviews.MovieReviewsPresenterImpl
import com.dpfht.tmdbcleanmvp.feature.moviereviews.adapter.MovieReviewsAdapter
import com.dpfht.tmdbcleanmvp.core.data.repository.AppRepository
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
  fun provideMovieReviewsModel(appRepository: AppRepository): MovieReviewsModel {
    return MovieReviewsModelImpl(appRepository)
  }

  @Provides
  @FragmentScope
  fun provideReviews(): ArrayList<Review> {
    return arrayListOf()
  }

  @Provides
  @FragmentScope
  fun provideMovieReviewsPresenter(
    movieReviewsView: MovieReviewsView,
    movieReviewsModel: MovieReviewsModel,
    reviews: ArrayList<Review>,
    scope: CoroutineScope
  ): MovieReviewsPresenter {
    return MovieReviewsPresenterImpl(movieReviewsView, movieReviewsModel, reviews, scope)
  }

  @Provides
  @FragmentScope
  fun provideMovieReviewsAdapter(reviews: ArrayList<Review>): MovieReviewsAdapter {
    return MovieReviewsAdapter(reviews)
  }
}
