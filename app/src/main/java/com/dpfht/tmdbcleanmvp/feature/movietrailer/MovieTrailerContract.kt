package com.dpfht.tmdbcleanmvp.feature.movietrailer

import com.dpfht.tmdbcleanmvp.feature.base.BasePresenter
import com.dpfht.tmdbcleanmvp.core.domain.model.GetMovieTrailerResult
import com.dpfht.tmdbcleanmvp.core.domain.model.ModelResultWrapper

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
    suspend fun getMovieTrailer(movieId: Int): ModelResultWrapper<GetMovieTrailerResult>
  }
}
