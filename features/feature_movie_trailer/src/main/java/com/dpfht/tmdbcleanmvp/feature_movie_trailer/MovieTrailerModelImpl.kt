package com.dpfht.tmdbcleanmvp.feature_movie_trailer

import com.dpfht.tmdbcleanmvp.domain.entity.Result
import com.dpfht.tmdbcleanmvp.domain.entity.TrailerDomain
import com.dpfht.tmdbcleanmvp.domain.usecase.GetMovieTrailerUseCase

class MovieTrailerModelImpl(
  private val getMovieTrailerUseCase: GetMovieTrailerUseCase
): MovieTrailerContract.MovieTrailerModel {

  override suspend fun getMovieTrailer(movieId: Int): Result<TrailerDomain> {
    return getMovieTrailerUseCase(movieId)
  }
}
