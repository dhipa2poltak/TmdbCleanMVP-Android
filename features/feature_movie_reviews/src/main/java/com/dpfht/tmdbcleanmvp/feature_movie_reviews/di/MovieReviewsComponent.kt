package com.dpfht.tmdbcleanmvp.feature_movie_reviews.di

import com.dpfht.tmdbcleanmvp.feature_movie_reviews.MovieReviewsFragment
import com.dpfht.tmdbcleanmvp.framework.di.ApplicationComponent
import com.dpfht.tmdbcleanmvp.framework.di.FragmentScope
import com.dpfht.tmdbcleanmvp.framework.di.NavigationComponent
import dagger.Component

@Component(dependencies = [ApplicationComponent::class, NavigationComponent::class], modules = [MovieReviewsModule::class])
@FragmentScope
interface MovieReviewsComponent {

  fun inject(movieReviewsFragment: MovieReviewsFragment)
}
