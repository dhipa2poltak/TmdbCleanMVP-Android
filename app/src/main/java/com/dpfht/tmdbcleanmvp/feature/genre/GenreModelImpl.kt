package com.dpfht.tmdbcleanmvp.feature.genre

import com.dpfht.tmdbcleanmvp.core.domain.repository.AppRepository
import com.dpfht.tmdbcleanmvp.core.domain.model.GetMovieGenreResult
import com.dpfht.tmdbcleanmvp.core.domain.model.ModelResultWrapper
import com.dpfht.tmdbcleanmvp.feature.genre.GenreContract.GenreModel

class GenreModelImpl(
  private val appRepository: AppRepository
): GenreModel {

  override suspend fun getMovieGenre(): ModelResultWrapper<GetMovieGenreResult> {
    return appRepository.getMovieGenre()
  }
}
