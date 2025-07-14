package com.dpfht.tmdbcleanmvp.feature.moviedetails

import com.dpfht.tmdbcleanmvp.domain.entity.MovieDetailsDomain
import com.dpfht.tmdbcleanmvp.domain.entity.Result
import com.dpfht.tmdbcleanmvp.domain.usecase.GetMovieDetailsUseCase
import com.dpfht.tmdbcleanmvp.feature.moviedetails.MovieDetailsContract.MovieDetailsModel

class MovieDetailsModelImpl(
  private val getMovieDetailsUseCase: GetMovieDetailsUseCase
): MovieDetailsModel {

  override suspend fun getMovieDetails(movieId: Int): Result<MovieDetailsDomain> {
    return getMovieDetailsUseCase(movieId)
  }
}
