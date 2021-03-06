package com.dpfht.tmdbcleanmvp.feature.genre.di

import com.dpfht.tmdbcleanmvp.framework.di.ApplicationComponent
import com.dpfht.tmdbcleanmvp.framework.di.FragmentScope
import com.dpfht.tmdbcleanmvp.feature.genre.GenreFragment
import dagger.Component

@Component(dependencies = [ApplicationComponent::class], modules = [GenreModule::class])
@FragmentScope
interface GenreComponent {

  fun inject(genreFragment: GenreFragment)
}
