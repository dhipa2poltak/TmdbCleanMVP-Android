package com.dpfht.tmdbcleanmvp.feature_movies_by_genre

import com.dpfht.tmdbcleanmvp.domain.model.Movie
import com.dpfht.tmdbcleanmvp.domain.model.Result.Success
import com.dpfht.tmdbcleanmvp.domain.model.Result.Error
import com.dpfht.tmdbcleanmvp.domain.usecase.GetMovieByGenreUseCase
import com.dpfht.tmdbcleanmvp.framework.navigation.NavigationService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class MoviesByGenrePresenterImpl(
    private var moviesByGenreView: MoviesByGenreContract.MoviesByGenreView? = null,
    private val getMovieByGenreUseCase: GetMovieByGenreUseCase,
    private val movies: ArrayList<Movie>,
    private val scope: CoroutineScope,
    private val navigationService: NavigationService
): MoviesByGenreContract.MoviesByGenrePresenter {

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

      when (val result = getMovieByGenreUseCase(_genreId, page + 1)) {
        is Success -> {
          onSuccess(result.value.results, result.value.page)
        }
        is Error -> {
          onError(result.message)
        }
      }
    }
  }

  private fun onSuccess(movies: List<Movie>, page: Int) {
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
