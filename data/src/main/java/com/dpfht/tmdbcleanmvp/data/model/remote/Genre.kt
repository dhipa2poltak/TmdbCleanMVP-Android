package com.dpfht.tmdbcleanmvp.data.model.remote

import androidx.annotation.Keep
import com.dpfht.tmdbcleanmvp.domain.entity.GenreEntity

@Keep
data class Genre(
    val id: Int? = -1,
    val name: String? = ""
)

fun Genre.toDomain(): GenreEntity {
    return GenreEntity(
        id ?: -1,
        name ?: "",
    )
}
