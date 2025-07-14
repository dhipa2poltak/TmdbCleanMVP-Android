package com.dpfht.tmdbcleanmvp.feature.moviesbygenre

import com.dpfht.tmdbcleanmvp.domain.entity.DiscoverMovieByGenreDomain
import com.dpfht.tmdbcleanmvp.domain.entity.Result
import com.dpfht.tmdbcleanmvp.domain.usecase.GetMovieByGenreUseCase
import com.dpfht.tmdbcleanmvp.feature.moviesbygenre.MoviesByGenreContract.MoviesByGenreModel

class MoviesByGenreModelImpl(
  private val getMovieByGenreUseCase: GetMovieByGenreUseCase
): MoviesByGenreModel {

  override suspend fun getMoviesByGenre(genreId: Int, page: Int): Result<DiscoverMovieByGenreDomain> {
    return getMovieByGenreUseCase(genreId, page)
  }
}
