package com.dpfht.tmdbcleanmvp.feature.moviereviews

import com.dpfht.tmdbcleanmvp.core.data.repository.AppRepository
import com.dpfht.tmdbcleanmvp.core.domain.model.GetMovieReviewResult
import com.dpfht.tmdbcleanmvp.core.domain.model.ModelResultWrapper
import com.dpfht.tmdbcleanmvp.feature.moviereviews.MovieReviewsContract.MovieReviewsModel

class MovieReviewsModelImpl(
  private val appRepository: AppRepository
): MovieReviewsModel {

  override suspend fun getMovieReviews(movieId: Int, page: Int): ModelResultWrapper<GetMovieReviewResult> {
    return appRepository.getMovieReviews(movieId, page)
  }
}
