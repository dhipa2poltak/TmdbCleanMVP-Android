package com.dpfht.tmdbcleanmvp.feature.moviesbygenre.di

import com.dpfht.tmdbcleanmvp.framework.di.ApplicationComponent
import com.dpfht.tmdbcleanmvp.framework.di.FragmentScope
import com.dpfht.tmdbcleanmvp.feature.moviesbygenre.MoviesByGenreFragment
import dagger.Component

@Component(dependencies = [ApplicationComponent::class], modules = [MoviesByGenreModule::class])
@FragmentScope
interface MoviesByGenreComponent {

  fun inject(moviesByGenreFragment: MoviesByGenreFragment)
}
