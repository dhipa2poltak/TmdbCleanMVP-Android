package com.dpfht.tmdbcleanmvp.domain.repository

import com.dpfht.tmdbcleanmvp.domain.model.DiscoverMovieByGenreModel
import com.dpfht.tmdbcleanmvp.domain.model.GenreModel
import com.dpfht.tmdbcleanmvp.domain.model.MovieDetailsModel
import com.dpfht.tmdbcleanmvp.domain.model.ReviewModel
import com.dpfht.tmdbcleanmvp.domain.model.TrailerModel

interface AppRepository {

  suspend fun getMovieGenre(): GenreModel

  suspend fun getMoviesByGenre(genreId: String, page: Int): DiscoverMovieByGenreModel

  suspend fun getMovieDetail(movieId: Int): MovieDetailsModel

  suspend fun getMovieReviews(movieId: Int, page: Int): ReviewModel

  suspend fun getMovieTrailer(movieId: Int): TrailerModel
}
