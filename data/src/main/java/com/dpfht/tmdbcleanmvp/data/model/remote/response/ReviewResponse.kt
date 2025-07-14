package com.dpfht.tmdbcleanmvp.data.model.remote.response

import androidx.annotation.Keep
import com.dpfht.tmdbcleanmvp.domain.entity.ReviewDomain
import com.dpfht.tmdbcleanmvp.data.model.remote.Review
import com.dpfht.tmdbcleanmvp.data.model.remote.toDomain
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Keep
@Suppress("unused")
data class ReviewResponse(
    val id: Int? = -1,
    val page: Int? = -1,
    val results: List<Review>? = listOf(),

    @SerializedName("total_pages")
    @Expose
    val totalPages: Int? = -1,

    @SerializedName("total_results")
    @Expose
    val totalResults: Int? = -1
)

fun ReviewResponse.toDomain(): ReviewDomain {
    val reviewEntities = results?.map { it.toDomain() }

    return ReviewDomain(reviewEntities?.toList() ?: listOf(), this.page ?: -1)
}
