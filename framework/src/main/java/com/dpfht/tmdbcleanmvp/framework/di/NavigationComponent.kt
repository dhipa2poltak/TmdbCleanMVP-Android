package com.dpfht.tmdbcleanmvp.framework.di

import com.dpfht.tmdbcleanmvp.framework.di.module.NavigationModule
import com.dpfht.tmdbcleanmvp.framework.navigation.NavigationService
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NavigationModule::class])
interface NavigationComponent {

  fun getNavigationService(): NavigationService
}
