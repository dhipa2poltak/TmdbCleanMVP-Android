package com.dpfht.tmdbcleanmvp.feature.movietrailer

import com.dpfht.tmdbcleanmvp.domain.entity.Result
import com.dpfht.tmdbcleanmvp.domain.entity.TrailerDomain
import com.dpfht.tmdbcleanmvp.framework.base.BasePresenter

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
