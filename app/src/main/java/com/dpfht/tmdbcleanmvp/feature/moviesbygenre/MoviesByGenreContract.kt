package com.dpfht.tmdbcleanmvp.feature.moviesbygenre

import androidx.navigation.NavDirections
import com.dpfht.tmdbcleanmvp.feature.base.BasePresenter
import com.dpfht.tmdbcleanmvp.feature.base.BaseView
import com.dpfht.tmdbcleanmvp.core.domain.model.GetMovieByGenreResult
import com.dpfht.tmdbcleanmvp.core.domain.model.ModelResultWrapper

interface MoviesByGenreContract {

  interface MoviesByGenreView: BaseView {
    fun notifyItemInserted(position: Int)
  }

  interface MoviesByGenrePresenter: BasePresenter {
    fun isLoadingData(): Boolean
    fun setGenreId(genreId: Int)
    fun getMoviesByGenre()
    fun getNavDirectionsOnClickMovieAt(position: Int): NavDirections
  }

  interface MoviesByGenreModel {
    suspend fun getMoviesByGenre(genreId: Int, page: Int): ModelResultWrapper<GetMovieByGenreResult>
  }
}
