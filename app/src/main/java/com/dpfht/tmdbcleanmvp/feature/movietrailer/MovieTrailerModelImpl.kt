package com.dpfht.tmdbcleanmvp.feature.movietrailer

import com.dpfht.tmdbcleanmvp.domain.entity.Result
import com.dpfht.tmdbcleanmvp.domain.entity.TrailerDomain
import com.dpfht.tmdbcleanmvp.domain.usecase.GetMovieTrailerUseCase
import com.dpfht.tmdbcleanmvp.feature.movietrailer.MovieTrailerContract.MovieTrailerModel

class MovieTrailerModelImpl(
  private val getMovieTrailerUseCase: GetMovieTrailerUseCase
): MovieTrailerModel {

  override suspend fun getMovieTrailer(movieId: Int): Result<TrailerDomain> {
    return getMovieTrailerUseCase(movieId)
  }
}
