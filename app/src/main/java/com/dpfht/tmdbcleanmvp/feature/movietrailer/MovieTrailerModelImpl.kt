package com.dpfht.tmdbcleanmvp.feature.movietrailer

import com.dpfht.tmdbcleanmvp.core.data.repository.AppRepository
import com.dpfht.tmdbcleanmvp.core.domain.model.GetMovieTrailerResult
import com.dpfht.tmdbcleanmvp.core.domain.model.ModelResultWrapper
import com.dpfht.tmdbcleanmvp.feature.movietrailer.MovieTrailerContract.MovieTrailerModel

class MovieTrailerModelImpl(
  private val appRepository: AppRepository
): MovieTrailerModel {

  override suspend fun getMovieTrailer(movieId: Int): ModelResultWrapper<GetMovieTrailerResult> {
    return appRepository.getMovieTrailer(movieId)
  }
}
