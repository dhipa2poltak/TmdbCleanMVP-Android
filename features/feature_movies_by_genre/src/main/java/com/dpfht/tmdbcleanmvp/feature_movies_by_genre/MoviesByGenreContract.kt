package com.dpfht.tmdbcleanmvp.feature_movies_by_genre

import com.dpfht.tmdbcleanmvp.domain.entity.DiscoverMovieByGenreDomain
import com.dpfht.tmdbcleanmvp.domain.entity.Result
import com.dpfht.tmdbcleanmvp.framework.base.BasePresenter
import com.dpfht.tmdbcleanmvp.framework.base.BaseView

interface MoviesByGenreContract {

  interface MoviesByGenreView: BaseView {
    fun notifyItemInserted(position: Int)
  }

  interface MoviesByGenrePresenter: BasePresenter {
    fun isLoadingData(): Boolean
    fun setGenreId(genreId: Int)
    fun getMoviesByGenre()
    fun navigateToMovieDetails(position: Int)
  }

  interface MoviesByGenreModel {
    suspend fun getMoviesByGenre(genreId: Int, page: Int): Result<DiscoverMovieByGenreDomain>
  }
}
