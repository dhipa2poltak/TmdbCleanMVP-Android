package com.dpfht.tmdbcleanmvp.feature_movies_by_genre

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dpfht.tmdbcleanmvp.feature_movies_by_genre.adapter.MoviesByGenreAdapter
import com.dpfht.tmdbcleanmvp.feature_movies_by_genre.databinding.FragmentMoviesByGenreBinding
import com.dpfht.tmdbcleanmvp.feature_movies_by_genre.di.DaggerMoviesByGenreComponent
import com.dpfht.tmdbcleanmvp.feature_movies_by_genre.di.MoviesByGenreModule
import com.dpfht.tmdbcleanmvp.framework.base.BaseFragment
import com.dpfht.tmdbcleanmvp.framework.di.provider.ApplicationComponentProvider
import com.dpfht.tmdbcleanmvp.framework.di.provider.NavigationComponentProvider
import javax.inject.Inject
import com.dpfht.tmdbcleanmvp.framework.R as FrameworkR;

class MoviesByGenreFragment : BaseFragment(), MoviesByGenreContract.MoviesByGenreView {

  private lateinit var binding: FragmentMoviesByGenreBinding

  @Inject
  lateinit var presenter: MoviesByGenreContract.MoviesByGenrePresenter

  @Inject
  lateinit var adapter: MoviesByGenreAdapter

  override fun onAttach(context: Context) {
    super.onAttach(context)

    val moviesByGenreComponent = DaggerMoviesByGenreComponent
      .builder()
      .moviesByGenreModule(MoviesByGenreModule(this))
      .navigationComponent((requireActivity() as NavigationComponentProvider).provideNavigationComponent())
      .applicationComponent((requireActivity().application as ApplicationComponentProvider).provideApplicationComponent())
      .build()

    moviesByGenreComponent.inject(this)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentMoviesByGenreBinding.inflate(inflater, container, false)

    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val layoutManager = LinearLayoutManager(requireContext())
    layoutManager.orientation = LinearLayoutManager.VERTICAL

    binding.rvMoviesByGenre.layoutManager = layoutManager
    binding.rvMoviesByGenre.adapter = adapter

    adapter.onClickMovieListener = object : MoviesByGenreAdapter.OnClickMovieListener {
      override fun onClickMovie(position: Int) {
        presenter.navigateToMovieDetails(position)
      }
    }

    binding.rvMoviesByGenre.addOnScrollListener(object : RecyclerView.OnScrollListener() {
      override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        val xx = recyclerView.computeVerticalScrollRange()
        val xy = recyclerView.computeVerticalScrollOffset()
        val xz = recyclerView.computeVerticalScrollExtent()
        val zz = (xy.toFloat() / (xx - xz).toFloat() * 100).toInt()
        if (zz >= 75 && !presenter.isLoadingData()) {
          presenter.getMoviesByGenre()
        }
        super.onScrolled(recyclerView, dx, dy)
      }
    })

    arguments?.let {
      val genreId = it.getInt("genreId")
      val genreName = it.getString("genreName")

      val title = "$genreName movies"
      binding.tvTitle.text = title

      presenter.setGenreId(genreId)
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
    //val navDirections = MoviesByGenreFragmentDirections.actionMovieByGenreToErrorDialog(message)
    //Navigation.findNavController(requireView()).navigate(navDirections)
  }

  override fun showCanceledMessage() {
    showErrorMessage(getString(FrameworkR.string.canceled_message))
  }
}
