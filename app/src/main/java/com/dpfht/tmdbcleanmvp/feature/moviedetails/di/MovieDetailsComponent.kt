package com.dpfht.tmdbcleanmvp.feature.moviedetails.di

import com.dpfht.tmdbcleanmvp.framework.di.ApplicationComponent
import com.dpfht.tmdbcleanmvp.framework.di.FragmentScope
import com.dpfht.tmdbcleanmvp.feature.moviedetails.MovieDetailsFragment
import com.dpfht.tmdbcleanmvp.framework.di.NavigationComponent
import dagger.Component

@Component(dependencies = [ApplicationComponent::class, NavigationComponent::class], modules = [MovieDetailsModule::class])
@FragmentScope
interface MovieDetailsComponent {

  fun inject(movieDetailsFragment: MovieDetailsFragment)
}
