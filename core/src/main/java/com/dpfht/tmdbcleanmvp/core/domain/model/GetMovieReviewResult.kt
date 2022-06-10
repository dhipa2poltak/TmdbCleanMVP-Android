package com.dpfht.tmdbcleanmvp.core.domain.model

import com.dpfht.tmdbcleanmvp.core.data.model.remote.Review

data class GetMovieReviewResult(
  val reviews: List<Review>,
  val page: Int
)
