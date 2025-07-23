package com.dpfht.tmdbcleanmvp.feature_genre

import com.dpfht.tmdbcleanmvp.domain.entity.GenreDomain
import com.dpfht.tmdbcleanmvp.domain.entity.Result
import com.dpfht.tmdbcleanmvp.framework.base.BasePresenter
import com.dpfht.tmdbcleanmvp.framework.base.BaseView

interface GenreContract {

  interface GenreView: BaseView {
    fun notifyItemInserted(position: Int)
  }

  interface GenrePresenter: BasePresenter {
    fun navigateToMovieByGenre(position: Int)
  }

  interface GenreModel {
    suspend fun getMovieGenre(): Result<GenreDomain>
  }
}
