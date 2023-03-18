package com.dpfht.tmdbcleanmvp.core.domain.repository

import com.dpfht.tmdbcleanmvp.core.domain.model.GetMovieByGenreResult
import com.dpfht.tmdbcleanmvp.core.domain.model.GetMovieDetailsResult
import com.dpfht.tmdbcleanmvp.core.domain.model.GetMovieGenreResult
import com.dpfht.tmdbcleanmvp.core.domain.model.GetMovieReviewResult
import com.dpfht.tmdbcleanmvp.core.domain.model.GetMovieTrailerResult
import com.dpfht.tmdbcleanmvp.core.domain.model.ModelResultWrapper

interface AppRepository {

  suspend fun getMovieGenre(): ModelResultWrapper<GetMovieGenreResult>

  suspend fun getMoviesByGenre(genreId: String, page: Int): ModelResultWrapper<GetMovieByGenreResult>

  suspend fun getMovieDetail(movieId: Int): ModelResultWrapper<GetMovieDetailsResult>

  suspend fun getMovieReviews(movieId: Int, page: Int): ModelResultWrapper<GetMovieReviewResult>

  suspend fun getMovieTrailer(movieId: Int): ModelResultWrapper<GetMovieTrailerResult>
}
