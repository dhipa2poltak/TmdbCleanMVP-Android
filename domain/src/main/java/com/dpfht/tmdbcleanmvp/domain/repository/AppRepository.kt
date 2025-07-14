package com.dpfht.tmdbcleanmvp.domain.repository

import com.dpfht.tmdbcleanmvp.domain.entity.DiscoverMovieByGenreDomain
import com.dpfht.tmdbcleanmvp.domain.entity.GenreDomain
import com.dpfht.tmdbcleanmvp.domain.entity.MovieDetailsDomain
import com.dpfht.tmdbcleanmvp.domain.entity.ReviewDomain
import com.dpfht.tmdbcleanmvp.domain.entity.TrailerDomain

interface AppRepository {

  suspend fun getMovieGenre(): GenreDomain

  suspend fun getMoviesByGenre(genreId: String, page: Int): DiscoverMovieByGenreDomain

  suspend fun getMovieDetail(movieId: Int): MovieDetailsDomain

  suspend fun getMovieReviews(movieId: Int, page: Int): ReviewDomain

  suspend fun getMovieTrailer(movieId: Int): TrailerDomain
}
