package com.dpfht.tmdbcleanmvp.domain.usecase

import com.dpfht.tmdbcleanmvp.domain.model.AppException
import com.dpfht.tmdbcleanmvp.domain.model.Result
import com.dpfht.tmdbcleanmvp.domain.model.ReviewModel
import com.dpfht.tmdbcleanmvp.domain.repository.AppRepository

class GetMovieReviewUseCaseImpl(
  private val appRepository: AppRepository
): GetMovieReviewUseCase {

  override suspend operator fun invoke(
    movieId: Int,
    page: Int
  ): Result<ReviewModel> {
    return try {
      Result.Success(appRepository.getMovieReviews(movieId, page))
    } catch (e: AppException) {
      Result.Error(e.message)
    }
  }
}
