package com.dpfht.tmdbcleanmvp.data.model.remote

import androidx.annotation.Keep
import com.dpfht.tmdbcleanmvp.domain.entity.TrailerEntity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Keep
@Suppress("unused")
data class Trailer(
    val id: String? = "",

    @SerializedName("iso_639_1")
    @Expose
    val iso6391: String? = "",

    @SerializedName("iso_3166_1")
    @Expose
    val iso31661: String? = "",

    val key: String? = "",
    val name: String? = "",
    val site: String? = "",
    val size: Int? = -1,
    val type: String? = ""
)

fun Trailer.toDomain(): TrailerEntity {
    return TrailerEntity(id ?: "", key ?: "", name ?: "", site ?: "")
}
