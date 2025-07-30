package com.dpfht.tmdbcleanmvp.domain.usecase

import com.dpfht.tmdbcleanmvp.domain.model.AppException
import com.dpfht.tmdbcleanmvp.domain.model.DiscoverMovieByGenreModel
import com.dpfht.tmdbcleanmvp.domain.model.Result
import com.dpfht.tmdbcleanmvp.domain.repository.AppRepository

class GetMovieByGenreUseCaseImpl(
  private val appRepository: AppRepository
): GetMovieByGenreUseCase {

  override suspend operator fun invoke(
    genreId: Int,
    page: Int
  ): Result<DiscoverMovieByGenreModel> {
    return try {
      Result.Success(appRepository.getMoviesByGenre(genreId.toString(), page))
    } catch (e: AppException) {
      Result.Error(e.message)
    }
  }
}
