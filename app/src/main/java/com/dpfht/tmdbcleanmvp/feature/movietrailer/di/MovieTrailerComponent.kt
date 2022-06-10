package com.dpfht.tmdbcleanmvp.feature.movietrailer.di

import com.dpfht.tmdbcleanmvp.framework.di.ActivityScope
import com.dpfht.tmdbcleanmvp.framework.di.ApplicationComponent
import com.dpfht.tmdbcleanmvp.feature.movietrailer.MovieTrailerActivity
import dagger.Component

@Component(dependencies = [ApplicationComponent::class], modules = [MovieTrailerModule::class])
@ActivityScope
interface MovieTrailerComponent {

  fun inject(movieTrailerActivity: MovieTrailerActivity)
}
