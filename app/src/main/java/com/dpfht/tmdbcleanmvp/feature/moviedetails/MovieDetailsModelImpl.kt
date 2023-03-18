package com.dpfht.tmdbcleanmvp.feature.moviedetails

import com.dpfht.tmdbcleanmvp.core.domain.repository.AppRepository
import com.dpfht.tmdbcleanmvp.core.domain.model.GetMovieDetailsResult
import com.dpfht.tmdbcleanmvp.core.domain.model.ModelResultWrapper
import com.dpfht.tmdbcleanmvp.feature.moviedetails.MovieDetailsContract.MovieDetailsModel

class MovieDetailsModelImpl(
  private val appRepository: AppRepository
): MovieDetailsModel {

  override suspend fun getMovieDetails(movieId: Int): ModelResultWrapper<GetMovieDetailsResult> {
    return appRepository.getMovieDetail(movieId)
  }
}
