package com.dpfht.tmdbcleanmvp.feature_movies_by_genre

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dpfht.tmdbcleanmvp.feature_movies_by_genre.adapter.MoviesByGenreAdapter
import com.dpfht.tmdbcleanmvp.feature_movies_by_genre.databinding.FragmentMoviesByGenreBinding
import com.dpfht.tmdbcleanmvp.feature_movies_by_genre.di.DaggerMoviesByGenreComponent
import com.dpfht.tmdbcleanmvp.feature_movies_by_genre.di.MoviesByGenreModule
import com.dpfht.tmdbcleanmvp.framework.base.BaseFragmentNew
import com.dpfht.tmdbcleanmvp.framework.di.provider.ApplicationComponentProvider
import com.dpfht.tmdbcleanmvp.framework.di.provider.NavigationComponentProvider
import javax.inject.Inject

class MoviesByGenreFragment : BaseFragmentNew<FragmentMoviesByGenreBinding>(R.layout.fragment_movies_by_genre), MoviesByGenreContract.MoviesByGenreView {

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
    loadingView.visibility = View.VISIBLE
  }

  override fun hideLoadingDialog() {
    loadingView.visibility = View.GONE
  }

  override fun showErrorMessage(message: String) {}

  override fun showCanceledMessage() {}
}
