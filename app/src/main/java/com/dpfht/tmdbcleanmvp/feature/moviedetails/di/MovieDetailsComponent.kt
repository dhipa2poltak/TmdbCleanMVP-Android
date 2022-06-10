package com.dpfht.tmdbcleanmvp.feature.moviedetails.di

import com.dpfht.tmdbcleanmvp.framework.di.ApplicationComponent
import com.dpfht.tmdbcleanmvp.framework.di.FragmentScope
import com.dpfht.tmdbcleanmvp.feature.moviedetails.MovieDetailsFragment
import dagger.Component

@Component(dependencies = [ApplicationComponent::class], modules = [MovieDetailsModule::class])
@FragmentScope
interface MovieDetailsComponent {

  fun inject(movieDetailsFragment: MovieDetailsFragment)
}
