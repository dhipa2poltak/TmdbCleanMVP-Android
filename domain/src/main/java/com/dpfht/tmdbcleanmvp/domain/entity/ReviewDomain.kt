package com.dpfht.tmdbcleanmvp.domain.entity

data class ReviewDomain(
  val results: List<ReviewEntity> = listOf(),
  val page: Int = -1,
)
