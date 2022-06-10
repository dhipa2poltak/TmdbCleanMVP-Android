package com.dpfht.tmdbcleanmvp.core.data.model.remote.response

import com.dpfht.tmdbcleanmvp.core.data.model.remote.Genre
import com.dpfht.tmdbcleanmvp.core.domain.model.GetMovieGenreResult

data class GenreResponse(
    val genres: List<Genre>? = null
)

fun GenreResponse.toDomain() =
  GetMovieGenreResult(this.genres ?: arrayListOf())

