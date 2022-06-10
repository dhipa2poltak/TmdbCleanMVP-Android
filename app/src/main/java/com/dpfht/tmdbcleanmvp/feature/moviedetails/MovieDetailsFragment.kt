package com.dpfht.tmdbcleanmvp.feature.moviedetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.dpfht.tmdbcleanmvp.R
import com.dpfht.tmdbcleanmvp.TheApplication
import com.dpfht.tmdbcleanmvp.feature.base.BaseFragment
import com.dpfht.tmdbcleanmvp.databinding.FragmentMovieDetailsBinding
import com.dpfht.tmdbcleanmvp.feature.moviedetails.MovieDetailsContract.MovieDetailsPresenter
import com.dpfht.tmdbcleanmvp.feature.moviedetails.MovieDetailsContract.MovieDetailsView
import com.dpfht.tmdbcleanmvp.feature.moviedetails.di.DaggerMovieDetailsComponent
import com.dpfht.tmdbcleanmvp.feature.moviedetails.di.MovieDetailsModule
import com.dpfht.tmdbcleanmvp.feature.movietrailer.MovieTrailerActivity
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

    val args = MovieDetailsFragmentArgs.fromBundle(requireArguments())
    val movieId = args.movieId

    presenter.setMovieId(movieId)
    presenter.start()
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
    val navDirections = presenter.getNavDirectionsToMovieReviews()
    Navigation.findNavController(requireView()).navigate(navDirections)
  }

  private fun onClickShowTrailer() {
    val itn = Intent(requireContext(), MovieTrailerActivity::class.java)
    itn.putExtra("movie_id", presenter.getMovieId())
    requireActivity().startActivity(itn)
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
    val navDirections = MovieDetailsFragmentDirections.actionMovieDetailsToErrorDialog(message)
    Navigation.findNavController(requireView()).navigate(navDirections)
  }

  override fun showCanceledMessage() {
    showErrorMessage(getString(R.string.canceled_message))
  }
}
