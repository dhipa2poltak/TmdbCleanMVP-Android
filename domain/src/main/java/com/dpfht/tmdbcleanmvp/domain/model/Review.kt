package com.dpfht.tmdbcleanmvp.domain.model

data class Review(
  val author: String = "",
  val authorDetails: AuthorDetails? = null,
  val content: String = "",
)
