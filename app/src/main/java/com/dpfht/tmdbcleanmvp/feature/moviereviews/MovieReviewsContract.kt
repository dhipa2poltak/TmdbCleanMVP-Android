package com.dpfht.tmdbcleanmvp.feature.moviereviews

import com.dpfht.tmdbcleanmvp.feature.base.BasePresenter
import com.dpfht.tmdbcleanmvp.feature.base.BaseView
import com.dpfht.tmdbcleanmvp.core.domain.model.GetMovieReviewResult
import com.dpfht.tmdbcleanmvp.core.domain.model.ModelResultWrapper

interface MovieReviewsContract {

  interface MovieReviewsView: BaseView {
    fun notifyItemInserted(position: Int)
  }

  interface MovieReviewsPresenter: BasePresenter {
    fun isLoadingData(): Boolean
    fun setMovieId(movieId: Int)
    fun getMovieReviews()
  }

  interface MovieReviewsModel {
    suspend fun getMovieReviews(movieId: Int, page: Int): ModelResultWrapper<GetMovieReviewResult>
  }
}
