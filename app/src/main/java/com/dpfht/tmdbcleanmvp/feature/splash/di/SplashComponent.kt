package com.dpfht.tmdbcleanmvp.feature.splash.di

import com.dpfht.tmdbcleanmvp.feature.splash.SplashFragment
import com.dpfht.tmdbcleanmvp.framework.di.FragmentScope
import com.dpfht.tmdbcleanmvp.framework.di.NavigationComponent
import dagger.Component

@Component(dependencies = [NavigationComponent::class])
@FragmentScope
interface SplashComponent {

  fun inject(splashFragment: SplashFragment)
}