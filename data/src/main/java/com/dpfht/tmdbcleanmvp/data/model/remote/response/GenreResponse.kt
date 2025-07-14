package com.dpfht.tmdbcleanmvp.data.model.remote.response

import androidx.annotation.Keep
import com.dpfht.tmdbcleanmvp.domain.entity.GenreDomain
import com.dpfht.tmdbcleanmvp.data.model.remote.Genre
import com.dpfht.tmdbcleanmvp.data.model.remote.toDomain

@Keep
data class GenreResponse(
    val genres: List<Genre>? = listOf()
)

fun GenreResponse.toDomain(): GenreDomain {
    val genreEntities = genres?.map { it.toDomain() }

    return GenreDomain(genreEntities?.toList() ?: listOf())
}
