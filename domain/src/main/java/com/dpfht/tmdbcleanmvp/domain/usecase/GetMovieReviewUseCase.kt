package com.dpfht.tmdbcleanmvp.domain.usecase

import com.dpfht.tmdbcleanmvp.domain.model.ReviewModel
import com.dpfht.tmdbcleanmvp.domain.model.Result

interface GetMovieReviewUseCase {

  suspend operator fun invoke(
    movieId: Int,
    page: Int
  ): Result<ReviewModel>
}
