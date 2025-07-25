package com.dpfht.tmdbcleanmvp.feature_movie_trailer.di

import com.dpfht.tmdbcleanmvp.feature_movie_trailer.MovieTrailerActivity
import com.dpfht.tmdbcleanmvp.framework.di.ActivityScope
import com.dpfht.tmdbcleanmvp.framework.di.ApplicationComponent
import dagger.Component

@Component(dependencies = [ApplicationComponent::class], modules = [MovieTrailerModule::class])
@ActivityScope
interface MovieTrailerComponent {

  fun inject(movieTrailerActivity: MovieTrailerActivity)
}
