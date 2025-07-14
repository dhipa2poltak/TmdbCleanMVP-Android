package com.dpfht.tmdbcleanmvp.feature.moviesbygenre

import androidx.navigation.NavDirections
import com.dpfht.tmdbcleanmvp.feature.base.BasePresenter
import com.dpfht.tmdbcleanmvp.feature.base.BaseView
import com.dpfht.tmdbcleanmvp.domain.entity.DiscoverMovieByGenreDomain
import com.dpfht.tmdbcleanmvp.domain.entity.Result

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
