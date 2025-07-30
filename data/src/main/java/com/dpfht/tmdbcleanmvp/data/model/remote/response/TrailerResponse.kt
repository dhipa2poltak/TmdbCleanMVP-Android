package com.dpfht.tmdbcleanmvp.data.model.remote.response

import androidx.annotation.Keep
import com.dpfht.tmdbcleanmvp.domain.model.TrailerModel
import com.dpfht.tmdbcleanmvp.data.model.remote.TrailerDTO
import com.dpfht.tmdbcleanmvp.data.model.remote.toDomain

@Keep
data class TrailerResponse(
    val id: Int? = -1,
    val results: List<TrailerDTO>? = listOf()
)

fun TrailerResponse.toDomain(): TrailerModel {
    val trailerEntities = results?.map { it.toDomain() }

    return TrailerModel(this.id ?: -1, trailerEntities?.toList() ?: listOf())
}
