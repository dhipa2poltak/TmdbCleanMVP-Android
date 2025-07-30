package com.dpfht.tmdbcleanmvp.data.model.remote.response

import androidx.annotation.Keep
import com.dpfht.tmdbcleanmvp.domain.model.GenreModel
import com.dpfht.tmdbcleanmvp.data.model.remote.GenreDTO
import com.dpfht.tmdbcleanmvp.data.model.remote.toDomain

@Keep
data class GenreResponse(
    val genres: List<GenreDTO>? = listOf()
)

fun GenreResponse.toDomain(): GenreModel {
    val genreEntities = genres?.map { it.toDomain() }

    return GenreModel(genreEntities?.toList() ?: listOf())
}
