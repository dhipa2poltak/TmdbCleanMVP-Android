package com.dpfht.tmdbcleanmvp.data.model.remote.response

import androidx.annotation.Keep
import com.dpfht.tmdbcleanmvp.domain.entity.TrailerDomain
import com.dpfht.tmdbcleanmvp.data.model.remote.Trailer
import com.dpfht.tmdbcleanmvp.data.model.remote.toDomain

@Keep
data class TrailerResponse(
    val id: Int? = -1,
    val results: List<Trailer>? = listOf()
)

fun TrailerResponse.toDomain(): TrailerDomain {
    val trailerEntities = results?.map { it.toDomain() }

    return TrailerDomain(this.id ?: -1, trailerEntities?.toList() ?: listOf())
}
