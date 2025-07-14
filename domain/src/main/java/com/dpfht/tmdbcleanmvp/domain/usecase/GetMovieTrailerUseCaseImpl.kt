package com.dpfht.tmdbcleanmvp.domain.usecase

import com.dpfht.tmdbcleanmvp.domain.entity.AppException
import com.dpfht.tmdbcleanmvp.domain.entity.Result
import com.dpfht.tmdbcleanmvp.domain.entity.TrailerDomain
import com.dpfht.tmdbcleanmvp.domain.repository.AppRepository

class GetMovieTrailerUseCaseImpl(
  private val appRepository: AppRepository
): GetMovieTrailerUseCase {

  override suspend operator fun invoke(
    movieId: Int
  ): Result<TrailerDomain> {
    return try {
      Result.Success(appRepository.getMovieTrailer(movieId))
    } catch (e: AppException) {
      Result.Error(e.message)
    }
  }
}
