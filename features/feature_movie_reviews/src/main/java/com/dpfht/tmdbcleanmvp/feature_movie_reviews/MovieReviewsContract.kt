package com.dpfht.tmdbcleanmvp.feature_movie_reviews

import com.dpfht.tmdbcleanmvp.domain.entity.Result
import com.dpfht.tmdbcleanmvp.domain.entity.ReviewDomain
import com.dpfht.tmdbcleanmvp.framework.base.BasePresenter
import com.dpfht.tmdbcleanmvp.framework.base.BaseView

interface MovieReviewsContract {

  interface MovieReviewsView: BaseView {
    fun notifyItemInserted(position: Int)
    fun showEmptyReview(isEmpty: Boolean)
  }

  interface MovieReviewsPresenter: BasePresenter {
    fun isLoadingData(): Boolean
    fun setMovieId(movieId: Int)
    fun getMovieReviews()
  }

  interface MovieReviewsModel {
    suspend fun getMovieReviews(movieId: Int, page: Int): Result<ReviewDomain>
  }
}
