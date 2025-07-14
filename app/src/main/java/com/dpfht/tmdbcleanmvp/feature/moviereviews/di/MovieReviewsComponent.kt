package com.dpfht.tmdbcleanmvp.feature.moviereviews.di

import com.dpfht.tmdbcleanmvp.framework.di.ApplicationComponent
import com.dpfht.tmdbcleanmvp.framework.di.FragmentScope
import com.dpfht.tmdbcleanmvp.feature.moviereviews.MovieReviewsFragment
import com.dpfht.tmdbcleanmvp.framework.di.NavigationComponent
import dagger.Component

@Component(dependencies = [ApplicationComponent::class, NavigationComponent::class], modules = [MovieReviewsModule::class])
@FragmentScope
interface MovieReviewsComponent {

  fun inject(movieReviewsFragment: MovieReviewsFragment)
}
