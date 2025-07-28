package com.dpfht.tmdbcleanmvp.feature_movie_details

import com.dpfht.tmdbcleanmvp.domain.entity.Result.Success
import com.dpfht.tmdbcleanmvp.domain.entity.Result.Error
import com.dpfht.tmdbcleanmvp.framework.navigation.NavigationService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class MovieDetailsPresenterImpl(
  private var movieDetailsView: MovieDetailsContract.MovieDetailsView? = null,
  private var movieDetailsModel: MovieDetailsContract.MovieDetailsModel? = null,
  private val scope: CoroutineScope,
  private val navigationService: NavigationService
): MovieDetailsContract.MovieDetailsPresenter {

  private var _movieId = -1
  private var title = ""
  private var overview = ""
  private var imageUrl = ""

  override fun setMovieId(movieId: Int) {
    this._movieId = movieId
  }

  override fun getMovieId(): Int {
    return _movieId
  }

  override fun getMovieTitle(): String {
    return title
  }

  override fun start() {
    if (title.isEmpty()) {
      getMovieDetails()
    } else {
      movieDetailsView?.showMovieDetails(title, overview, imageUrl)
    }
  }

  private fun getMovieDetails() {
    movieDetailsView?.showLoadingDialog()
    scope.launch(Dispatchers.Main) {
      movieDetailsModel?.let {
        when (val result = it.getMovieDetails(_movieId)) {
          is Success -> {
            onSuccess(result.value.id, result.value.title, result.value.overview, result.value.imageUrl)
          }
          is Error -> {
            onError(result.message)
          }
        }
      }
    }
  }

  private fun onSuccess(pMovieId: Int, pTitle: String, pOverview: String, pPosterPath: String) {
    imageUrl = pPosterPath
    _movieId = pMovieId
    title = pTitle
    overview = pOverview

    movieDetailsView?.showMovieDetails(title, overview, imageUrl)
    movieDetailsView?.hideLoadingDialog()
  }

  private fun onError(message: String) {
    movieDetailsView?.hideLoadingDialog()
    navigationService.navigateToErrorMessage(message)
  }

  override fun navigateToMovieReviews(movieId: Int, movieTitle: String) {
    navigationService.navigateToMovieReviews(movieId, movieTitle)
  }

  override fun navigateToMovieTrailer(movieId: Int) {
    navigationService.navigateToMovieTrailer(movieId)
  }

  override fun onDestroy() {
    if (scope.isActive) {
      scope.cancel()
    }
    this.movieDetailsView = null
  }
}
