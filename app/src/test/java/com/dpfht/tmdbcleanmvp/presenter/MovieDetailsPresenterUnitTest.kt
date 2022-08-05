package com.dpfht.tmdbcleanmvp.presenter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dpfht.tmdbcleanmvp.BuildConfig
import com.dpfht.tmdbcleanmvp.Config
import com.dpfht.tmdbcleanmvp.MainCoroutineRule
import com.dpfht.tmdbcleanmvp.core.domain.model.GetMovieDetailsResult
import com.dpfht.tmdbcleanmvp.core.domain.model.ModelResultWrapper
import com.dpfht.tmdbcleanmvp.feature.moviedetails.MovieDetailsContract.MovieDetailsModel
import com.dpfht.tmdbcleanmvp.feature.moviedetails.MovieDetailsContract.MovieDetailsPresenter
import com.dpfht.tmdbcleanmvp.feature.moviedetails.MovieDetailsContract.MovieDetailsView
import com.dpfht.tmdbcleanmvp.feature.moviedetails.MovieDetailsPresenterImpl
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
class MovieDetailsPresenterUnitTest {

  @get:Rule
  val taskExecutorRule = InstantTaskExecutorRule()

  @get:Rule
  val coroutineRule = MainCoroutineRule()

  private lateinit var presenter: MovieDetailsPresenter

  @Mock
  private lateinit var movieDetailsView: MovieDetailsView

  @Mock
  private lateinit var movieDetailsModel: MovieDetailsModel

  private val scope = CoroutineScope(Job())

  @Before
  fun setup() {
    presenter = MovieDetailsPresenterImpl(movieDetailsView, movieDetailsModel, scope)
  }

  @Test
  fun `fetch movie details successfully`() = runBlocking {
    val movieId = 1
    val title = "title1"
    val overview = "overview1"
    val posterPath = "poster_path1"

    val getMovieDetailsResult = GetMovieDetailsResult(
      movieId, title, overview, posterPath
    )

    val result = ModelResultWrapper.Success(getMovieDetailsResult)

    whenever(movieDetailsModel.getMovieDetails(movieId)).thenReturn(result)

    presenter.setMovieId(movieId)
    presenter.start()

    var imageUrl = ""
    if (posterPath.isNotEmpty()) {
      imageUrl = BuildConfig.IMAGE_URL_BASE_PATH + posterPath
    }

    verify(movieDetailsView).showMovieDetails(title, overview, imageUrl)
    verify(movieDetailsView).hideLoadingDialog()
  }

  @Test
  fun `failed fetch movie details`() = runBlocking {
    val msg = "error fetch movie details"
    val result = ModelResultWrapper.ErrorResult(msg)

    val movieId = 1

    whenever(movieDetailsModel.getMovieDetails(movieId)).thenReturn(result)

    presenter.setMovieId(movieId)
    presenter.start()

    verify(movieDetailsView).hideLoadingDialog()
    verify(movieDetailsView).showErrorMessage(msg)
  }
}
