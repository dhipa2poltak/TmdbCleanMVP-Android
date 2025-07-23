package com.dpfht.tmdbcleanmvp.feature.moviedetails

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.dpfht.tmdbcleanmvp.R
import com.dpfht.tmdbcleanmvp.TheApplication
import com.dpfht.tmdbcleanmvp.databinding.FragmentMovieDetailsBinding
import com.dpfht.tmdbcleanmvp.feature.moviedetails.MovieDetailsContract.MovieDetailsPresenter
import com.dpfht.tmdbcleanmvp.feature.moviedetails.MovieDetailsContract.MovieDetailsView
import com.dpfht.tmdbcleanmvp.feature.moviedetails.di.DaggerMovieDetailsComponent
import com.dpfht.tmdbcleanmvp.feature.moviedetails.di.MovieDetailsModule
import com.dpfht.tmdbcleanmvp.framework.base.BaseFragment
import com.dpfht.tmdbcleanmvp.framework.di.provider.NavigationComponentProvider
import javax.inject.Inject

class MovieDetailsFragment : BaseFragment(), MovieDetailsView {

  private lateinit var binding: FragmentMovieDetailsBinding

  @Inject
  lateinit var presenter: MovieDetailsPresenter

  override fun onAttach(context: Context) {
    super.onAttach(context)

    val movieDetailsComponent = DaggerMovieDetailsComponent
      .builder()
      .movieDetailsModule(MovieDetailsModule(this))
      .navigationComponent((requireActivity() as NavigationComponentProvider).provideNavigationComponent())
      .applicationComponent(TheApplication.instance.applicationComponent)
      .build()

    movieDetailsComponent.inject(this)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)

    return binding.root
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
        .placeholder(R.mipmap.ic_launcher)
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
    prgDialog.show()
  }

  override fun hideLoadingDialog() {
    prgDialog.dismiss()
  }

  override fun showErrorMessage(message: String) {
    //val navDirections = MovieDetailsFragmentDirections.actionMovieDetailsToErrorDialog(message)
    //Navigation.findNavController(requireView()).navigate(navDirections)
  }

  override fun showCanceledMessage() {
    showErrorMessage(getString(R.string.canceled_message))
  }
}
