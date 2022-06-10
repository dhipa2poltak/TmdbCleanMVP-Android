package com.dpfht.tmdbcleanmvp.framework.rest.api

import com.dpfht.tmdbcleanmvp.core.data.model.remote.response.toDomain
import com.dpfht.tmdbcleanmvp.core.data.repository.AppDataSource
import com.dpfht.tmdbcleanmvp.core.domain.model.GetMovieByGenreResult
import com.dpfht.tmdbcleanmvp.core.domain.model.GetMovieDetailsResult
import com.dpfht.tmdbcleanmvp.core.domain.model.GetMovieGenreResult
import com.dpfht.tmdbcleanmvp.core.domain.model.GetMovieReviewResult
import com.dpfht.tmdbcleanmvp.core.domain.model.GetMovieTrailerResult
import com.dpfht.tmdbcleanmvp.core.domain.model.ModelResultWrapper
import com.dpfht.tmdbcleanmvp.core.domain.model.ModelResultWrapper.ErrorResult
import com.dpfht.tmdbcleanmvp.framework.rest.api.ResultWrapper.GenericError
import com.dpfht.tmdbcleanmvp.framework.rest.api.ResultWrapper.NetworkError
import com.dpfht.tmdbcleanmvp.framework.rest.api.ResultWrapper.Success
import com.dpfht.tmdbcleanmvp.util.safeApiCall
import kotlinx.coroutines.Dispatchers

class RemoteDataSourceImpl(private val restService: RestService): AppDataSource {

  override suspend fun getMovieGenre(): ModelResultWrapper<GetMovieGenreResult> {
    return when (val result = safeApiCall(Dispatchers.IO) { restService.getMovieGenre() }) {
      is Success -> {
        ModelResultWrapper.Success(result.value.toDomain())
      }
      is GenericError -> {
        if (result.code != null && result.error != null) {
          ErrorResult(result.error.statusMessage ?: "")
        } else {
          ErrorResult("error in conversion")
        }
      }
      is NetworkError -> {
        ErrorResult("error in connection")
      }
    }
  }

  override suspend fun getMoviesByGenre(genreId: String, page: Int): ModelResultWrapper<GetMovieByGenreResult> {
    return when (val result = safeApiCall(Dispatchers.IO) { restService.getMoviesByGenre(genreId, page) }) {
      is Success -> {
        ModelResultWrapper.Success(result.value.toDomain())
      }
      is GenericError -> {
        if (result.code != null && result.error != null) {
          ErrorResult(result.error.statusMessage ?: "")
        } else {
          ErrorResult("error in conversion")
        }
      }
      is NetworkError -> {
        ErrorResult("error in connection")
      }
    }
  }

  override suspend fun getMovieDetail(movieId: Int): ModelResultWrapper<GetMovieDetailsResult> {
    return when (val result = safeApiCall(Dispatchers.IO) { restService.getMovieDetail(movieId) }) {
      is Success -> {
        ModelResultWrapper.Success(result.value.toDomain())
      }
      is GenericError -> {
        if (result.code != null && result.error != null) {
          ErrorResult(result.error.statusMessage ?: "")
        } else {
          ErrorResult("error in conversion")
        }
      }
      is NetworkError -> {
        ErrorResult("error in connection")
      }
    }
  }

  override suspend fun getMovieReviews(movieId: Int, page: Int): ModelResultWrapper<GetMovieReviewResult> {
    return when (val result = safeApiCall(Dispatchers.IO) { restService.getMovieReviews(movieId, page) }) {
      is Success -> {
        ModelResultWrapper.Success(result.value.toDomain())
      }
      is GenericError -> {
        if (result.code != null && result.error != null) {
          ErrorResult(result.error.statusMessage ?: "")
        } else {
          ErrorResult("error in conversion")
        }
      }
      is NetworkError -> {
        ErrorResult("error in connection")
      }
    }
  }

  override suspend fun getMovieTrailer(movieId: Int): ModelResultWrapper<GetMovieTrailerResult> {
    return when (val result = safeApiCall(Dispatchers.IO) { restService.getMovieTrailers(movieId) }) {
      is Success -> {
        ModelResultWrapper.Success(result.value.toDomain())
      }
      is GenericError -> {
        if (result.code != null && result.error != null) {
          ErrorResult(result.error.statusMessage ?: "")
        } else {
          ErrorResult("error in conversion")
        }
      }
      is NetworkError -> {
        ErrorResult("error in connection")
      }
    }
  }
}