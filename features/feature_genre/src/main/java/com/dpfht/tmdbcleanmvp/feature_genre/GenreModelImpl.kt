package com.dpfht.tmdbcleanmvp.feature_genre

import com.dpfht.tmdbcleanmvp.domain.entity.GenreDomain
import com.dpfht.tmdbcleanmvp.domain.entity.Result
import com.dpfht.tmdbcleanmvp.domain.usecase.GetMovieGenreUseCase

class GenreModelImpl(
  private val getMovieGenreUseCase: GetMovieGenreUseCase
): GenreContract.GenreModel {

  override suspend fun getMovieGenre(): Result<GenreDomain> {
    return getMovieGenreUseCase()
  }
}
