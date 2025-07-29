package com.dpfht.tmdbcleanmvp.feature_genre

import com.dpfht.tmdbcleanmvp.domain.entity.GenreEntity
import com.dpfht.tmdbcleanmvp.domain.entity.Result.Error
import com.dpfht.tmdbcleanmvp.domain.entity.Result.Success
import com.dpfht.tmdbcleanmvp.domain.usecase.GetMovieGenreUseCase
import com.dpfht.tmdbcleanmvp.framework.navigation.NavigationService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class GenrePresenterImpl(
  private var genreView: GenreContract.GenreView? = null,
  private val getMovieGenreUseCase: GetMovieGenreUseCase,
  private val genres: ArrayList<GenreEntity>,
  private val scope: CoroutineScope,
  private val navigationService: NavigationService
): GenreContract.GenrePresenter {

  override fun start() {
    if (genres.isEmpty()) {
      getMovieGenre()
    }
  }

  private fun getMovieGenre() {
    genreView?.showLoadingDialog()
    scope.launch(Dispatchers.Main) {
      when (val result = getMovieGenreUseCase()) {
        is Success -> {
          onSuccess(result.value.genres)
        }
        is Error -> {
          onError(result.message)
        }
      }
    }
  }

  private fun onSuccess(genres: List<GenreEntity>) {
    for (genre in genres) {
      this.genres.add(genre)
      genreView?.notifyItemInserted(this.genres.size - 1)
    }
    genreView?.hideLoadingDialog()
  }

  private fun onError(message: String) {
    genreView?.hideLoadingDialog()
    navigationService.navigateToErrorMessage(message)
  }

  override fun navigateToMovieByGenre(position: Int) {
    val genre = genres[position]
    navigationService.navigateToMoviesByGender(genre.id, genre.name)
  }

  override fun onDestroy() {
    if (scope.isActive) {
      scope.cancel()
    }
    this.genreView = null
  }
}
