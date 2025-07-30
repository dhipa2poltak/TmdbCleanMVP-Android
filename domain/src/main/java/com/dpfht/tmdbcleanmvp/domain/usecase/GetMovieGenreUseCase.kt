package com.dpfht.tmdbcleanmvp.domain.usecase

import com.dpfht.tmdbcleanmvp.domain.model.GenreModel
import com.dpfht.tmdbcleanmvp.domain.model.Result

interface GetMovieGenreUseCase {

  suspend operator fun invoke(): Result<GenreModel>
}
