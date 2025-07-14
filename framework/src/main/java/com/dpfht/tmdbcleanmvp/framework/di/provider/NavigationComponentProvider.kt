package com.dpfht.tmdbcleanmvp.framework.di.provider

import com.dpfht.tmdbcleanmvp.framework.di.NavigationComponent


interface NavigationComponentProvider {

  fun provideNavigationComponent(): NavigationComponent
}
