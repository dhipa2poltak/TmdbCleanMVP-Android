package com.dpfht.tmdbcleanmvp.feature_movie_reviews

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dpfht.tmdbcleanmvp.feature_movie_reviews.adapter.MovieReviewsAdapter
import com.dpfht.tmdbcleanmvp.feature_movie_reviews.databinding.FragmentMovieReviewsBinding
import com.dpfht.tmdbcleanmvp.feature_movie_reviews.di.DaggerMovieReviewsComponent
import com.dpfht.tmdbcleanmvp.feature_movie_reviews.di.MovieReviewsModule
import com.dpfht.tmdbcleanmvp.framework.base.BaseFragment
import com.dpfht.tmdbcleanmvp.framework.di.provider.ApplicationComponentProvider
import com.dpfht.tmdbcleanmvp.framework.di.provider.NavigationComponentProvider
import javax.inject.Inject
import com.dpfht.tmdbcleanmvp.framework.R as FrameworkR

class MovieReviewsFragment : BaseFragment(), MovieReviewsContract.MovieReviewsView {

  private lateinit var binding: FragmentMovieReviewsBinding

  @Inject
  lateinit var presenter: MovieReviewsContract.MovieReviewsPresenter

  @Inject
  lateinit var adapter: MovieReviewsAdapter

  override fun onAttach(context: Context) {
    super.onAttach(context)

    val movieReviewsComponent = DaggerMovieReviewsComponent
      .builder()
      .movieReviewsModule(MovieReviewsModule(this))
      .navigationComponent((requireActivity() as NavigationComponentProvider).provideNavigationComponent())
      .applicationComponent((requireActivity().application as ApplicationComponentProvider).provideApplicationComponent())
      .build()

    movieReviewsComponent.inject(this)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentMovieReviewsBinding.inflate(inflater, container, false)

    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val layoutManager = LinearLayoutManager(requireContext())
    layoutManager.orientation = LinearLayoutManager.VERTICAL

    binding.rvReview.layoutManager = layoutManager
    binding.rvReview.adapter = adapter

    binding.rvReview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
      override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        val xx = recyclerView.computeVerticalScrollRange()
        val xy = recyclerView.computeVerticalScrollOffset()
        val xz = recyclerView.computeVerticalScrollExtent()
        val zz = (xy.toFloat() / (xx - xz).toFloat() * 100).toInt()
        if (zz >= 75 && !presenter.isLoadingData()) {
          presenter.getMovieReviews()
        }
        super.onScrolled(recyclerView, dx, dy)
      }
    })

    arguments?.let {
      val movieId = it.getInt("movieId")
      val movieTitle = it.getString("movieTitle")

      binding.tvMovieName.text = movieTitle

      presenter.setMovieId(movieId)
      presenter.start()
    }
  }

  override fun notifyItemInserted(position: Int) {
    adapter.notifyItemInserted(position)
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
    //val navDirections = MovieReviewsFragmentDirections.actionMovieReviewsToErrorDialog(message)
    //Navigation.findNavController(requireView()).navigate(navDirections)
  }

  override fun showCanceledMessage() {
    showErrorMessage(getString(FrameworkR.string.canceled_message))
  }
}
