package com.dpfht.tmdbcleanmvp.feature_genre

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dpfht.tmdbcleanmvp.feature_genre.adapter.GenreAdapter
import com.dpfht.tmdbcleanmvp.feature_genre.databinding.FragmentGenreBinding
import com.dpfht.tmdbcleanmvp.feature_genre.di.DaggerGenreComponent
import com.dpfht.tmdbcleanmvp.feature_genre.di.GenreModule
import com.dpfht.tmdbcleanmvp.framework.base.BaseFragment
import com.dpfht.tmdbcleanmvp.framework.di.provider.ApplicationComponentProvider
import com.dpfht.tmdbcleanmvp.framework.di.provider.NavigationComponentProvider
import javax.inject.Inject
import com.dpfht.tmdbcleanmvp.framework.R as FrameworkR

class GenreFragment : BaseFragment(), GenreContract.GenreView {

  private lateinit var binding: FragmentGenreBinding

  @Inject
  lateinit var presenter: GenreContract.GenrePresenter
  
  @Inject
  lateinit var adapter: GenreAdapter

  override fun onAttach(context: Context) {
    super.onAttach(context)

    val genreComponent = DaggerGenreComponent
      .builder()
      .genreModule(GenreModule(this))
      .navigationComponent((requireActivity() as NavigationComponentProvider).provideNavigationComponent())
      .applicationComponent((requireActivity().application as ApplicationComponentProvider).provideApplicationComponent())
      .build()

    genreComponent.inject(this)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentGenreBinding.inflate(inflater, container, false)

    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val layoutManager = LinearLayoutManager(requireContext())
    layoutManager.orientation = LinearLayoutManager.VERTICAL

    binding.rvGenre.layoutManager = layoutManager
    binding.rvGenre.adapter = adapter

    adapter.onClickGenreListener = object : GenreAdapter.OnClickGenreListener {
      override fun onClickGenre(position: Int) {
        presenter.navigateToMovieByGenre(position)
      }
    }

    presenter.start()
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
    //val navDirections = GenreFragmentDirections.actionGenreFragmentToErrorDialog(message)
    //Navigation.findNavController(requireView()).navigate(navDirections)
  }

  override fun showCanceledMessage() {
    showErrorMessage(getString(FrameworkR.string.canceled_message))
  }
}
