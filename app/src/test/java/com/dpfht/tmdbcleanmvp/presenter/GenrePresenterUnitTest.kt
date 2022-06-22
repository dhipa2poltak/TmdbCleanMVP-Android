package com.dpfht.tmdbcleanmvp.presenter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dpfht.tmdbcleanmvp.MainCoroutineRule
import com.dpfht.tmdbcleanmvp.core.data.model.remote.Genre
import com.dpfht.tmdbcleanmvp.core.domain.model.GetMovieGenreResult
import com.dpfht.tmdbcleanmvp.core.domain.model.ModelResultWrapper
import com.dpfht.tmdbcleanmvp.feature.genre.GenreContract.GenreModel
import com.dpfht.tmdbcleanmvp.feature.genre.GenreContract.GenrePresenter
import com.dpfht.tmdbcleanmvp.feature.genre.GenreContract.GenreView
import com.dpfht.tmdbcleanmvp.feature.genre.GenrePresenterImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GenrePresenterUnitTest {

  @get:Rule
  val taskExecutorRule = InstantTaskExecutorRule()

  @get:Rule
  val coroutineRule = MainCoroutineRule()

  private lateinit var presenter: GenrePresenter

  @Mock
  private lateinit var genreView: GenreView

  @Mock
  private lateinit var genreModel: GenreModel

  private val listOfGenre = arrayListOf<Genre>()
  private val scope = CoroutineScope(Job())

  @Before
  fun setup() {
    presenter = GenrePresenterImpl(genreView, genreModel, listOfGenre, scope)
  }

  @Test
  fun `fetch genre successfully`() = runBlocking {
    val genre1 = Genre(1, "Cartoon")
    val genre2 = Genre(2, "Drama")
    val genre3 = Genre(3, "Horror")

    val genres = listOf(genre1, genre2, genre3)
    val getMovieGenreResult = GetMovieGenreResult(genres)
    val result = ModelResultWrapper.Success(getMovieGenreResult)

    whenever(genreModel.getMovieGenre()).thenReturn(result)

    presenter.start()

    verify(genreView).notifyItemInserted(listOfGenre.size - 1)
    verify(genreView).hideLoadingDialog()
  }

  @Test
  fun `failed fetch movie genre`() = runBlocking {
    val msg = "error fetch movie genre"
    val result = ModelResultWrapper.ErrorResult(msg)

    whenever(genreModel.getMovieGenre()).thenReturn(result)

    presenter.start()

    verify(genreView).hideLoadingDialog()
    verify(genreView).showErrorMessage(msg)
  }
}
