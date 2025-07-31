package com.dpfht.tmdbcleanmvp.domain.usecase

import com.dpfht.tmdbcleanmvp.domain.repository.AppRepository
import org.mockito.Mock

open class BaseUseCaseTest {

  @Mock
  protected lateinit var appRepository: AppRepository
}
