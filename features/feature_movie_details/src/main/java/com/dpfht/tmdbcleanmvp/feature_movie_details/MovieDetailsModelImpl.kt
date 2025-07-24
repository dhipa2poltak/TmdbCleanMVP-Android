package com.dpfht.tmdbcleanmvp.feature_movie_details

import com.dpfht.tmdbcleanmvp.domain.entity.MovieDetailsDomain
import com.dpfht.tmdbcleanmvp.domain.entity.Result
import com.dpfht.tmdbcleanmvp.domain.usecase.GetMovieDetailsUseCase

class MovieDetailsModelImpl(
  private val getMovieDetailsUseCase: GetMovieDetailsUseCase
): MovieDetailsContract.MovieDetailsModel {

  override suspend fun getMovieDetails(movieId: Int): Result<MovieDetailsDomain> {
    return getMovieDetailsUseCase(movieId)
  }
}
