package com.dpfht.tmdbcleanmvp.domain.entity

class AppException(
    override val message: String = ""
): Exception(message)
