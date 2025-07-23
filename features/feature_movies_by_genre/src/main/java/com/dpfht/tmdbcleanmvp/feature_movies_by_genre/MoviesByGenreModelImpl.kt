package com.dpfht.tmdbcleanmvp.feature_movies_by_genre

import com.dpfht.tmdbcleanmvp.domain.entity.DiscoverMovieByGenreDomain
import com.dpfht.tmdbcleanmvp.domain.entity.Result
import com.dpfht.tmdbcleanmvp.domain.usecase.GetMovieByGenreUseCase

class MoviesByGenreModelImpl(
  private val getMovieByGenreUseCase: GetMovieByGenreUseCase
): MoviesByGenreContract.MoviesByGenreModel {

  override suspend fun getMoviesByGenre(genreId: Int, page: Int): Result<DiscoverMovieByGenreDomain> {
    return getMovieByGenreUseCase(genreId, page)
  }
}
