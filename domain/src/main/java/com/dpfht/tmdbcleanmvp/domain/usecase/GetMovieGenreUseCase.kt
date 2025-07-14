package com.dpfht.tmdbcleanmvp.domain.usecase

import com.dpfht.tmdbcleanmvp.domain.entity.GenreDomain
import com.dpfht.tmdbcleanmvp.domain.entity.Result

interface GetMovieGenreUseCase {

  suspend operator fun invoke(): Result<GenreDomain>
}
