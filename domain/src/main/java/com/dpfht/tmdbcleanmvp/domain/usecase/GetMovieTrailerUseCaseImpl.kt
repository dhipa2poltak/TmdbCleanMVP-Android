package com.dpfht.tmdbcleanmvp.domain.usecase

import com.dpfht.tmdbcleanmvp.domain.model.AppException
import com.dpfht.tmdbcleanmvp.domain.model.Result
import com.dpfht.tmdbcleanmvp.domain.model.TrailerModel
import com.dpfht.tmdbcleanmvp.domain.repository.AppRepository

class GetMovieTrailerUseCaseImpl(
  private val appRepository: AppRepository
): GetMovieTrailerUseCase {

  override suspend operator fun invoke(
    movieId: Int
  ): Result<TrailerModel> {
    return try {
      Result.Success(appRepository.getMovieTrailer(movieId))
    } catch (e: AppException) {
      Result.Error(e.message)
    }
  }
}
