package com.dpfht.tmdbcleanmvp.feature.moviedetails

import androidx.navigation.NavDirections
import com.dpfht.tmdbcleanmvp.feature.base.BasePresenter
import com.dpfht.tmdbcleanmvp.feature.base.BaseView
import com.dpfht.tmdbcleanmvp.domain.entity.MovieDetailsDomain
import com.dpfht.tmdbcleanmvp.domain.entity.Result

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
