package com.dpfht.tmdbcleanmvp.feature.genre

import androidx.navigation.NavDirections
import com.dpfht.tmdbcleanmvp.domain.entity.GenreDomain
import com.dpfht.tmdbcleanmvp.domain.entity.Result
import com.dpfht.tmdbcleanmvp.feature.base.BasePresenter
import com.dpfht.tmdbcleanmvp.feature.base.BaseView

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
