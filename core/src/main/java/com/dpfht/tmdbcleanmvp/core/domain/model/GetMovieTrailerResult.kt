package com.dpfht.tmdbcleanmvp.core.domain.model

import com.dpfht.tmdbcleanmvp.core.data.model.remote.Trailer

data class GetMovieTrailerResult(
  val trailers: List<Trailer>
)
