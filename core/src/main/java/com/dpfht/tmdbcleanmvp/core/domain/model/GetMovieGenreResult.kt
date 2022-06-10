package com.dpfht.tmdbcleanmvp.core.domain.model

import com.dpfht.tmdbcleanmvp.core.data.model.remote.Genre

data class GetMovieGenreResult(
  val genres: List<Genre>
)
