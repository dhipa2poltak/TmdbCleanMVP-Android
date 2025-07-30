package com.dpfht.tmdbcleanmvp.domain.usecase

import com.dpfht.tmdbcleanmvp.domain.model.Result
import com.dpfht.tmdbcleanmvp.domain.model.TrailerModel

interface GetMovieTrailerUseCase {

  suspend operator fun invoke(
    movieId: Int
  ): Result<TrailerModel>
}
