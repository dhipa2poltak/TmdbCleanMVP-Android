package com.dpfht.tmdbcleanmvp.feature_movie_reviews

import com.dpfht.tmdbcleanmvp.domain.entity.Result
import com.dpfht.tmdbcleanmvp.domain.entity.ReviewDomain
import com.dpfht.tmdbcleanmvp.domain.usecase.GetMovieReviewUseCase

class MovieReviewsModelImpl(
  private val getMovieReviewUseCase: GetMovieReviewUseCase
): MovieReviewsContract.MovieReviewsModel {

  override suspend fun getMovieReviews(movieId: Int, page: Int): Result<ReviewDomain> {
    return getMovieReviewUseCase(movieId, page)
  }
}
