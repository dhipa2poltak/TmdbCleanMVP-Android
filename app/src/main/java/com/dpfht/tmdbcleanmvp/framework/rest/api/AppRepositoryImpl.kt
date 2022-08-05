package com.dpfht.tmdbcleanmvp.framework.rest.api

import com.dpfht.tmdbcleanmvp.core.data.repository.AppDataSource
import com.dpfht.tmdbcleanmvp.core.data.repository.AppRepository
import com.dpfht.tmdbcleanmvp.core.domain.model.GetMovieByGenreResult
import com.dpfht.tmdbcleanmvp.core.domain.model.GetMovieDetailsResult
import com.dpfht.tmdbcleanmvp.core.domain.model.GetMovieGenreResult
import com.dpfht.tmdbcleanmvp.core.domain.model.GetMovieReviewResult
import com.dpfht.tmdbcleanmvp.core.domain.model.GetMovieTrailerResult
import com.dpfht.tmdbcleanmvp.core.domain.model.ModelResultWrapper

class AppRepositoryImpl(private val remoteDataSource: AppDataSource): AppRepository {

  override suspend fun getMovieGenre(): ModelResultWrapper<GetMovieGenreResult> {
    return remoteDataSource.getMovieGenre()
  }

  override suspend fun getMoviesByGenre(genreId: String, page: Int):
      ModelResultWrapper<GetMovieByGenreResult> {
    return remoteDataSource.getMoviesByGenre(genreId, page)
  }

  override suspend fun getMovieDetail(movieId: Int): ModelResultWrapper<GetMovieDetailsResult> {
    return remoteDataSource.getMovieDetail(movieId)
  }

  override suspend fun getMovieReviews(
    movieId: Int,
    page: Int
  ): ModelResultWrapper<GetMovieReviewResult> {
    return remoteDataSource.getMovieReviews(movieId, page)
  }

  override suspend fun getMovieTrailer(movieId: Int): ModelResultWrapper<GetMovieTrailerResult> {
    return remoteDataSource.getMovieTrailer(movieId)
  }
}
