package com.dpfht.tmdbcleanmvp.domain.usecase

import com.dpfht.tmdbcleanmvp.domain.model.MovieDetailsModel
import com.dpfht.tmdbcleanmvp.domain.model.Result

interface GetMovieDetailsUseCase {

  suspend operator fun invoke(
    movieId: Int
  ): Result<MovieDetailsModel>
}
