package com.dpfht.tmdbcleanmvp.feature.movietrailer

import com.dpfht.tmdbcleanmvp.feature.base.BasePresenter
import com.dpfht.tmdbcleanmvp.domain.entity.Result
import com.dpfht.tmdbcleanmvp.domain.entity.TrailerDomain

interface MovieTrailerContract {

  interface MovieTrailerView {
    fun showTrailer(keyVideo: String)
    fun showErrorMessage(message: String)
    fun showCanceledMessage()
  }

  interface MovieTrailerPresenter: BasePresenter {
    fun setMovieId(movieId: Int)
  }

  interface MovieTrailerModel {
    suspend fun getMovieTrailer(movieId: Int): Result<TrailerDomain>
  }
}
