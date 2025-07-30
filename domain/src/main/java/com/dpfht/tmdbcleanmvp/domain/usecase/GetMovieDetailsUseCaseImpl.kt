package com.dpfht.tmdbcleanmvp.domain.usecase

import com.dpfht.tmdbcleanmvp.domain.model.AppException
import com.dpfht.tmdbcleanmvp.domain.model.MovieDetailsModel
import com.dpfht.tmdbcleanmvp.domain.model.Result
import com.dpfht.tmdbcleanmvp.domain.repository.AppRepository

class GetMovieDetailsUseCaseImpl(
  private val appRepository: AppRepository
): GetMovieDetailsUseCase {

  override suspend operator fun invoke(
    movieId: Int
  ): Result<MovieDetailsModel> {
    return try {
      Result.Success(appRepository.getMovieDetail(movieId))
    } catch (e: AppException) {
      Result.Error(e.message)
    }
  }
}
