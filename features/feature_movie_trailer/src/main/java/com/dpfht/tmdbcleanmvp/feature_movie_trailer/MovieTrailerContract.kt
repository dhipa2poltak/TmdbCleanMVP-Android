package com.dpfht.tmdbcleanmvp.feature_movie_trailer

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
}
