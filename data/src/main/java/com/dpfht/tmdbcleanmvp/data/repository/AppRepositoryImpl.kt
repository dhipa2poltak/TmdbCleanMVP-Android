package com.dpfht.tmdbcleanmvp.data.repository

import com.dpfht.tmdbcleanmvp.data.datasource.AppDataSource
import com.dpfht.tmdbcleanmvp.domain.entity.DiscoverMovieByGenreDomain
import com.dpfht.tmdbcleanmvp.domain.entity.GenreDomain
import com.dpfht.tmdbcleanmvp.domain.entity.MovieDetailsDomain
import com.dpfht.tmdbcleanmvp.domain.entity.ReviewDomain
import com.dpfht.tmdbcleanmvp.domain.entity.TrailerDomain
import com.dpfht.tmdbcleanmvp.domain.repository.AppRepository

class AppRepositoryImpl(
  private val remoteDataSource: AppDataSource
): AppRepository {

  override suspend fun getMovieGenre(): GenreDomain {
    return remoteDataSource.getMovieGenre()
  }

  override suspend fun getMoviesByGenre(genreId: String, page: Int): DiscoverMovieByGenreDomain {
    return remoteDataSource.getMoviesByGenre(genreId, page)
  }

  override suspend fun getMovieDetail(movieId: Int): MovieDetailsDomain {
    return remoteDataSource.getMovieDetail(movieId)
  }

  override suspend fun getMovieReviews(movieId: Int, page: Int): ReviewDomain {
    return remoteDataSource.getMovieReviews(movieId, page)
  }

  override suspend fun getMovieTrailer(movieId: Int): TrailerDomain {
    return remoteDataSource.getMovieTrailer(movieId)
  }
}
