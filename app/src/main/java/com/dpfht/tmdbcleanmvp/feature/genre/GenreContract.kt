package com.dpfht.tmdbcleanmvp.feature.genre

import androidx.navigation.NavDirections
import com.dpfht.tmdbcleanmvp.feature.base.BasePresenter
import com.dpfht.tmdbcleanmvp.feature.base.BaseView
import com.dpfht.tmdbcleanmvp.core.domain.model.GetMovieGenreResult
import com.dpfht.tmdbcleanmvp.core.domain.model.ModelResultWrapper

interface GenreContract {

  interface GenreView: BaseView {
    fun notifyItemInserted(position: Int)
  }

  interface GenrePresenter: BasePresenter {
    fun getNavDirectionsOnClickGenreAt(position: Int): NavDirections
  }

  interface GenreModel {
    suspend fun getMovieGenre(): ModelResultWrapper<GetMovieGenreResult>
  }
}
