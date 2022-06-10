package com.dpfht.tmdbcleanmvp.core.domain.model

import com.dpfht.tmdbcleanmvp.core.data.model.remote.Movie

data class GetMovieByGenreResult(
  val movies: List<Movie>,
  val page: Int
)
