package com.dpfht.tmdbcleanmvp.core.data.model.remote.response

import androidx.annotation.Keep
import com.dpfht.tmdbcleanmvp.core.data.model.remote.Review
import com.dpfht.tmdbcleanmvp.core.domain.model.GetMovieReviewResult
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Keep
@Suppress("unused")
data class ReviewResponse(
    val id: Int = 0,
    val page: Int = 0,
    val results: List<Review>? = null,

    @SerializedName("total_pages")
    @Expose
    val totalPages: Int = 0,

    @SerializedName("total_results")
    @Expose
    val totalResults: Int = 0
)

fun ReviewResponse.toDomain() = GetMovieReviewResult(
  this.results ?: arrayListOf(),
  this.page
)
