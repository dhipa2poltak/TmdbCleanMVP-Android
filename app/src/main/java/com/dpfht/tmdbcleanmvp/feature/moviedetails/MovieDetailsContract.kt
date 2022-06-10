package com.dpfht.tmdbcleanmvp.feature.moviedetails

import androidx.navigation.NavDirections
import com.dpfht.tmdbcleanmvp.feature.base.BasePresenter
import com.dpfht.tmdbcleanmvp.feature.base.BaseView
import com.dpfht.tmdbcleanmvp.core.domain.model.GetMovieDetailsResult
import com.dpfht.tmdbcleanmvp.core.domain.model.ModelResultWrapper

interface MovieDetailsContract {

  interface MovieDetailsView: BaseView {
    fun showMovieDetails(title: String, overview: String, imageUrl: String)
  }

  interface MovieDetailsPresenter: BasePresenter {
    fun setMovieId(movieId: Int)
    fun getMovieId(): Int
    fun getNavDirectionsToMovieReviews(): NavDirections
  }

  interface MovieDetailsModel {
    suspend fun getMovieDetails(movieId: Int): ModelResultWrapper<GetMovieDetailsResult>
  }
}
