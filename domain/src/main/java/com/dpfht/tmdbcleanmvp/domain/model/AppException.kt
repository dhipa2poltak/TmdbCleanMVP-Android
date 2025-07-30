package com.dpfht.tmdbcleanmvp.domain.model

class AppException(
    override val message: String = ""
): Exception(message)
