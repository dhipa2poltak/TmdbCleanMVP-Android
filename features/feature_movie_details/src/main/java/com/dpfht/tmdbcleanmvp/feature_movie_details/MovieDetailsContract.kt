package com.dpfht.tmdbcleanmvp.feature_movie_details

import com.dpfht.tmdbcleanmvp.domain.entity.MovieDetailsDomain
import com.dpfht.tmdbcleanmvp.domain.entity.Result
import com.dpfht.tmdbcleanmvp.framework.base.BasePresenter
import com.dpfht.tmdbcleanmvp.framework.base.BaseView

interface MovieDetailsContract {

  interface MovieDetailsView: BaseView {
    fun showMovieDetails(title: String, overview: String, imageUrl: String)
  }

  interface MovieDetailsPresenter: BasePresenter {
    fun setMovieId(movieId: Int)
    fun getMovieId(): Int

    fun getMovieTitle(): String
    fun navigateToMovieReviews(movieId: Int, movieTitle: String)
    fun navigateToMovieTrailer(movieId: Int)
  }

  interface MovieDetailsModel {
    suspend fun getMovieDetails(movieId: Int): Result<MovieDetailsDomain>
  }
}
