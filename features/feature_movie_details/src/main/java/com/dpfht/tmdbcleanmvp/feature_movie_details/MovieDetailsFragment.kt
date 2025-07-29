package com.dpfht.tmdbcleanmvp.feature_movie_details

import android.content.Context
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.dpfht.tmdbcleanmvp.feature_movie_details.databinding.FragmentMovieDetailsBinding
import com.dpfht.tmdbcleanmvp.feature_movie_details.di.DaggerMovieDetailsComponent
import com.dpfht.tmdbcleanmvp.feature_movie_details.di.MovieDetailsModule
import com.dpfht.tmdbcleanmvp.framework.base.BaseFragment
import com.dpfht.tmdbcleanmvp.framework.di.provider.ApplicationComponentProvider
import com.dpfht.tmdbcleanmvp.framework.di.provider.NavigationComponentProvider
import javax.inject.Inject
import com.dpfht.tmdbcleanmvp.framework.R as FrameworkR

class MovieDetailsFragment : BaseFragment<FragmentMovieDetailsBinding>(R.layout.fragment_movie_details), MovieDetailsContract.MovieDetailsView {

  @Inject
  lateinit var presenter: MovieDetailsContract.MovieDetailsPresenter

  override fun onAttach(context: Context) {
    super.onAttach(context)

    val movieDetailsComponent = DaggerMovieDetailsComponent
      .builder()
      .movieDetailsModule(MovieDetailsModule(this))
      .navigationComponent((requireActivity() as NavigationComponentProvider).provideNavigationComponent())
      .applicationComponent((requireActivity().application as ApplicationComponentProvider).provideApplicationComponent())
      .build()

    movieDetailsComponent.inject(this)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.tvShowReview.setOnClickListener {
      onClickShowReview()
    }

    binding.tvShowTrailer.setOnClickListener {
      onClickShowTrailer()
    }

    arguments?.let {
      val movieId = it.getInt("movieId")

      presenter.setMovieId(movieId)
      presenter.start()
    }
  }

  override fun showMovieDetails(title: String, overview: String, imageUrl: String) {
    binding.tvTitleMovie.text = title
    binding.tvDescMovie.text = overview

    if (imageUrl.isNotEmpty()) {
      Glide.with(requireContext())
        .load(imageUrl)
        .placeholder(FrameworkR.mipmap.ic_launcher)
        .into(binding.ivImageMovie)
    }
  }

  private fun onClickShowReview() {
    presenter.navigateToMovieReviews(presenter.getMovieId(), presenter.getMovieTitle())
  }

  private fun onClickShowTrailer() {
    presenter.navigateToMovieTrailer(presenter.getMovieId())
  }

  override fun onDetach() {
    presenter.onDestroy()
    super.onDetach()
  }

  override fun showLoadingDialog() {
    loadingView.visibility = View.VISIBLE
  }

  override fun hideLoadingDialog() {
    loadingView.visibility = View.GONE
  }

  override fun showErrorMessage(message: String) {}

  override fun showCanceledMessage() {}
}
