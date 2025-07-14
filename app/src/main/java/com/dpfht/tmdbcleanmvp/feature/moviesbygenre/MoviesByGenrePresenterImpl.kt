package com.dpfht.tmdbcleanmvp.feature.moviesbygenre

import com.dpfht.tmdbcleanmvp.feature.moviesbygenre.MoviesByGenreContract.MoviesByGenreModel
import com.dpfht.tmdbcleanmvp.feature.moviesbygenre.MoviesByGenreContract.MoviesByGenrePresenter
import com.dpfht.tmdbcleanmvp.feature.moviesbygenre.MoviesByGenreContract.MoviesByGenreView
import com.dpfht.tmdbcleanmvp.domain.entity.MovieEntity
import com.dpfht.tmdbcleanmvp.domain.entity.Result.Success
import com.dpfht.tmdbcleanmvp.domain.entity.Result.Error
import com.dpfht.tmdbcleanmvp.framework.navigation.NavigationService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class MoviesByGenrePresenterImpl(
  private var moviesByGenreView: MoviesByGenreView? = null,
  private var moviesByGenreModel: MoviesByGenreModel? = null,
  private val movies: ArrayList<MovieEntity>,
  private val scope: CoroutineScope,
  private val navigationService: NavigationService
): MoviesByGenrePresenter {

  private var _genreId = -1
  private var page = 0
  private var _isLoadingData = false
  private var isNextEmptyDataResponse = false

  override fun start() {
    if (_genreId != -1 && movies.isEmpty()) {
      getMoviesByGenre()
    }
  }

  override fun isLoadingData() = _isLoadingData

  override fun setGenreId(genreId: Int) {
    this._genreId = genreId
  }

  override fun getMoviesByGenre() {
    if (isNextEmptyDataResponse) return

    scope.launch(Dispatchers.Main) {
      moviesByGenreView?.showLoadingDialog()
      _isLoadingData = true

      moviesByGenreModel?.let {
        when (val result = it.getMoviesByGenre(_genreId, page + 1)) {
          is Success -> {
            onSuccess(result.value.results, result.value.page)
          }
          is Error -> {
            onError(result.message)
          }
        }
      }
    }
  }

  private fun onSuccess(movies: List<MovieEntity>, page: Int) {
    if (movies.isNotEmpty()) {
      this.page = page

      for (movie in movies) {
        this.movies.add(movie)
        moviesByGenreView?.notifyItemInserted(this.movies.size - 1)
      }
    } else {
      isNextEmptyDataResponse = true
    }

    moviesByGenreView?.hideLoadingDialog()
    _isLoadingData = false
  }

  private fun onError(message: String) {
    moviesByGenreView?.hideLoadingDialog()
    _isLoadingData = false
    navigationService.navigateToErrorMessage(message)
  }

  override fun navigateToMovieDetails(position: Int) {
    val movie = movies[position]

    navigationService.navigateToMovieDetails(movie.id)
  }

  override fun onDestroy() {
    if (scope.isActive) {
      scope.cancel()
    }
    this.moviesByGenreView = null
  }
}
