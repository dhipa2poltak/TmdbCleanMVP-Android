package com.dpfht.tmdbcleanmvp.feature.moviereviews

import com.dpfht.tmdbcleanmvp.domain.entity.Result
import com.dpfht.tmdbcleanmvp.domain.entity.ReviewDomain
import com.dpfht.tmdbcleanmvp.domain.usecase.GetMovieReviewUseCase
import com.dpfht.tmdbcleanmvp.feature.moviereviews.MovieReviewsContract.MovieReviewsModel

class MovieReviewsModelImpl(
  private val getMovieReviewUseCase: GetMovieReviewUseCase
): MovieReviewsModel {

  override suspend fun getMovieReviews(movieId: Int, page: Int): Result<ReviewDomain> {
    return getMovieReviewUseCase(movieId, page)
  }
}
