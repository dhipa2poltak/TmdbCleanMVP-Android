package com.dpfht.tmdbcleanmvp.core.data.model.remote

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Keep
@Suppress("unused")
data class ProductionCountry(
    @SerializedName("iso_3166_1")
    @Expose
    val iso31661: String? = null,

    @SerializedName("name")
    @Expose
    val name: String? = null
)
