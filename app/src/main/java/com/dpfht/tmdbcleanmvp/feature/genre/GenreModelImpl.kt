package com.dpfht.tmdbcleanmvp.feature.genre

import com.dpfht.tmdbcleanmvp.domain.entity.GenreDomain
import com.dpfht.tmdbcleanmvp.domain.entity.Result
import com.dpfht.tmdbcleanmvp.domain.usecase.GetMovieGenreUseCase
import com.dpfht.tmdbcleanmvp.feature.genre.GenreContract.GenreModel

class GenreModelImpl(
  private val getMovieGenreUseCase: GetMovieGenreUseCase
): GenreModel {

  override suspend fun getMovieGenre(): Result<GenreDomain> {
    return getMovieGenreUseCase()
  }
}
