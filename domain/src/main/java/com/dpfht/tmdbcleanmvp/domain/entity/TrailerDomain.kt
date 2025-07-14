package com.dpfht.tmdbcleanmvp.domain.entity

data class TrailerDomain(
  val id: Int = -1,
  val results: List<TrailerEntity> = listOf()
)
