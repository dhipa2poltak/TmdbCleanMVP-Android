package com.dpfht.tmdbcleanmvp.data.model.remote

import androidx.annotation.Keep
import com.dpfht.tmdbcleanmvp.domain.model.Genre

@Keep
data class GenreDTO(
    val id: Int? = -1,
    val name: String? = ""
)

fun GenreDTO.toDomain(): Genre {
    return Genre(
        id ?: -1,
        name ?: "",
    )
}
