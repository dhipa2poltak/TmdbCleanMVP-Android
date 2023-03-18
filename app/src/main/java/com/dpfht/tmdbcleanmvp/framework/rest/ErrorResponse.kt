package com.dpfht.tmdbcleanmvp.framework.rest

import com.google.gson.annotations.SerializedName

data class ErrorResponse(

  val success: Boolean = false,
  @SerializedName("status_code")
  val statusCode: Int = 0,
  @SerializedName("status_message")
  val statusMessage: String? = null
)
