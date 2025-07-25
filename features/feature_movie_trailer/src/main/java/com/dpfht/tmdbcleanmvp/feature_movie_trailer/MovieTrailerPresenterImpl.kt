package com.dpfht.tmdbcleanmvp.feature_movie_trailer

import com.dpfht.tmdbcleanmvp.domain.entity.Result.Success
import com.dpfht.tmdbcleanmvp.domain.entity.Result.Error
import com.dpfht.tmdbcleanmvp.domain.entity.TrailerEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.Locale

class MovieTrailerPresenterImpl(
  private var movieTrailerView: MovieTrailerContract.MovieTrailerView? = null,
  private var movieTrailerModel: MovieTrailerContract.MovieTrailerModel? = null,
  private val scope: CoroutineScope
): MovieTrailerContract.MovieTrailerPresenter {

  private var _movieId = -1

  override fun setMovieId(movieId: Int) {
    this._movieId = movieId
  }

  override fun start() {
    if (_movieId != -1) {
      getMovieTrailer()
    }
  }

  private fun getMovieTrailer() {
    scope.launch(Dispatchers.Main) {
      movieTrailerModel?.let {
        when (val result = it.getMovieTrailer(_movieId)) {
          is Success -> {
            onSuccess(result.value.results)
          }
          is Error -> {
            onError(result.message)
          }
        }
      }
    }
  }

  private fun onSuccess(trailers: List<TrailerEntity>) {
    var keyVideo = ""
    for (trailer in trailers) {
      if (trailer.site.lowercase(Locale.ROOT).trim() == "youtube") {
        keyVideo = trailer.key
        break
      }
    }

    if (keyVideo.isNotEmpty()) {
      movieTrailerView?.showTrailer(keyVideo)
    }
  }

  private fun onError(message: String) {
    movieTrailerView?.showErrorMessage(message)
  }

  override fun onDestroy() {
    if (scope.isActive) {
      scope.cancel()
    }

    this.movieTrailerView = null
    this.movieTrailerModel = null
  }
}
