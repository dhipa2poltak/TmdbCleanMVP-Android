package com.dpfht.tmdbcleanmvp.data.repository

import com.dpfht.tmdbcleanmvp.data.datasource.AppDataSource
import com.dpfht.tmdbcleanmvp.domain.model.DiscoverMovieByGenreModel
import com.dpfht.tmdbcleanmvp.domain.model.GenreModel
import com.dpfht.tmdbcleanmvp.domain.model.MovieDetailsModel
import com.dpfht.tmdbcleanmvp.domain.model.ReviewModel
import com.dpfht.tmdbcleanmvp.domain.model.TrailerModel
import com.dpfht.tmdbcleanmvp.domain.repository.AppRepository

class AppRepositoryImpl(
  private val remoteDataSource: AppDataSource
): AppRepository {

  override suspend fun getMovieGenre(): GenreModel {
    return remoteDataSource.getMovieGenre()
  }

  override suspend fun getMoviesByGenre(genreId: String, page: Int): DiscoverMovieByGenreModel {
    return remoteDataSource.getMoviesByGenre(genreId, page)
  }

  override suspend fun getMovieDetail(movieId: Int): MovieDetailsModel {
    return remoteDataSource.getMovieDetail(movieId)
  }

  override suspend fun getMovieReviews(movieId: Int, page: Int): ReviewModel {
    return remoteDataSource.getMovieReviews(movieId, page)
  }

  override suspend fun getMovieTrailer(movieId: Int): TrailerModel {
    return remoteDataSource.getMovieTrailer(movieId)
  }
}
