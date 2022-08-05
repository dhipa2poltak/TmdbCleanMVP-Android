package com.dpfht.tmdbcleanmvp.core.data.model.remote

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Keep
@Suppress("unused")
data class ProductionCompany(
    @SerializedName("name")
    @Expose
    val name: String? = null,

    @SerializedName("origin_country")
    @Expose
    val originCountry: String? = null,

    @SerializedName("logo_path")
    @Expose
    val logoPath: String? = null,

    @SerializedName("id")
    @Expose
    val id: Int = 0,
)
