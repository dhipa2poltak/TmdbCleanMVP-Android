package com.dpfht.tmdbcleanmvp.feature_movie_reviews

import com.dpfht.tmdbcleanmvp.domain.model.Result.Success
import com.dpfht.tmdbcleanmvp.domain.model.Result.Error
import com.dpfht.tmdbcleanmvp.domain.model.Review
import com.dpfht.tmdbcleanmvp.domain.usecase.GetMovieReviewUseCase
import com.dpfht.tmdbcleanmvp.framework.navigation.NavigationService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class MovieReviewsPresenterImpl(
    private var movieReviewsView: MovieReviewsContract.MovieReviewsView? = null,
    private val getMovieReviewUseCase: GetMovieReviewUseCase,
    private val reviews: ArrayList<Review>,
    private val scope: CoroutineScope,
    private val navigationService: NavigationService
): MovieReviewsContract.MovieReviewsPresenter {

  private var _isLoadingData = false
  private var _movieId = -1
  private var page = 0
  private var isNextEmptyDataResponse = false

  override fun start() {
    if (_movieId != -1 && reviews.isEmpty()) {
      getMovieReviews()
    }
  }

  override fun isLoadingData() = _isLoadingData

  override fun setMovieId(movieId: Int) {
    this._movieId = movieId
  }

  override fun getMovieReviews() {
    if (isNextEmptyDataResponse) return

    scope.launch(Dispatchers.Main) {
      movieReviewsView?.showLoadingDialog()
      _isLoadingData = true

      when (val result = getMovieReviewUseCase(_movieId, page + 1)) {
        is Success -> {
          onSuccess(result.value.results, result.value.page)
        }
        is Error -> {
          onError(result.message)
        }
      }
    }
  }

  private fun onSuccess(reviews: List<Review>, page: Int) {
    if (reviews.isNotEmpty()) {
      this.page = page

      for (review in reviews) {
        this.reviews.add(review)
        movieReviewsView?.notifyItemInserted(this.reviews.size - 1)
      }
    } else {
      if (this@MovieReviewsPresenterImpl.reviews.isEmpty()) {
        movieReviewsView?.showEmptyReview(true)
      }

      isNextEmptyDataResponse = true
    }

    movieReviewsView?.hideLoadingDialog()
    _isLoadingData = false
  }

  private fun onError(message: String) {
    movieReviewsView?.hideLoadingDialog()
    _isLoadingData = false
    navigationService.navigateToErrorMessage(message)
  }

  override fun onDestroy() {
    if (scope.isActive) {
      scope.cancel()
    }
    this.movieReviewsView = null
  }
}
