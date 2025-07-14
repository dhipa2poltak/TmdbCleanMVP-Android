package com.dpfht.tmdbcleanmvp.framework.di.provider

import com.dpfht.tmdbcleanmvp.framework.di.ApplicationComponent

interface ApplicationComponentProvider {

  fun provideApplicationComponent(): ApplicationComponent
}
