package com.dpfht.tmdbcleanmvp.feature.moviesbygenre.di

import com.dpfht.tmdbcleanmvp.framework.di.ApplicationComponent
import com.dpfht.tmdbcleanmvp.framework.di.FragmentScope
import com.dpfht.tmdbcleanmvp.feature.moviesbygenre.MoviesByGenreFragment
import com.dpfht.tmdbcleanmvp.framework.di.NavigationComponent
import dagger.Component

@Component(dependencies = [ApplicationComponent::class, NavigationComponent::class], modules = [MoviesByGenreModule::class])
@FragmentScope
interface MoviesByGenreComponent {

  fun inject(moviesByGenreFragment: MoviesByGenreFragment)
}
