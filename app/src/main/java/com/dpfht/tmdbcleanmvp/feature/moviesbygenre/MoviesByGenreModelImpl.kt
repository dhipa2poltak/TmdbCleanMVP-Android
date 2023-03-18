package com.dpfht.tmdbcleanmvp.feature.moviesbygenre

import com.dpfht.tmdbcleanmvp.core.domain.repository.AppRepository
import com.dpfht.tmdbcleanmvp.core.domain.model.GetMovieByGenreResult
import com.dpfht.tmdbcleanmvp.core.domain.model.ModelResultWrapper
import com.dpfht.tmdbcleanmvp.feature.moviesbygenre.MoviesByGenreContract.MoviesByGenreModel

class MoviesByGenreModelImpl(
  private val appRepository: AppRepository
): MoviesByGenreModel {

  override suspend fun getMoviesByGenre(genreId: Int, page: Int): ModelResultWrapper<GetMovieByGenreResult> {
    return appRepository.getMoviesByGenre(genreId.toString(), page)
  }
}
