package com.dpfht.tmdbcleanmvp.domain.usecase

import com.dpfht.tmdbcleanmvp.domain.model.AppException
import com.dpfht.tmdbcleanmvp.domain.model.GenreModel
import com.dpfht.tmdbcleanmvp.domain.model.Result
import com.dpfht.tmdbcleanmvp.domain.repository.AppRepository

class GetMovieGenreUseCaseImpl(
  private val appRepository: AppRepository
): GetMovieGenreUseCase {

  override suspend operator fun invoke(): Result<GenreModel> {
    return try {
      Result.Success(appRepository.getMovieGenre())
    } catch (e: AppException) {
      Result.Error(e.message)
    }
  }
}
