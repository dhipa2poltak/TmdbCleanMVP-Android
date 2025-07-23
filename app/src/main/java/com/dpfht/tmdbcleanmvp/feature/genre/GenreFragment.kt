package com.dpfht.tmdbcleanmvp.feature.genre

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dpfht.tmdbcleanmvp.R
import com.dpfht.tmdbcleanmvp.TheApplication
import com.dpfht.tmdbcleanmvp.databinding.FragmentGenreBinding
import com.dpfht.tmdbcleanmvp.feature.genre.GenreContract.GenrePresenter
import com.dpfht.tmdbcleanmvp.feature.genre.GenreContract.GenreView
import com.dpfht.tmdbcleanmvp.feature.genre.adapter.GenreAdapter
import com.dpfht.tmdbcleanmvp.feature.genre.di.DaggerGenreComponent
import com.dpfht.tmdbcleanmvp.feature.genre.di.GenreModule
import com.dpfht.tmdbcleanmvp.framework.base.BaseFragment
import com.dpfht.tmdbcleanmvp.framework.di.provider.NavigationComponentProvider
import javax.inject.Inject

class GenreFragment : BaseFragment(), GenreView {

  private lateinit var binding: FragmentGenreBinding

  @Inject
  lateinit var presenter: GenrePresenter
  
  @Inject
  lateinit var adapter: GenreAdapter

  override fun onAttach(context: Context) {
    super.onAttach(context)

    val genreComponent = DaggerGenreComponent
      .builder()
      .genreModule(GenreModule(this))
      .navigationComponent((requireActivity() as NavigationComponentProvider).provideNavigationComponent())
      .applicationComponent(TheApplication.instance.applicationComponent)
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
    showErrorMessage(getString(R.string.canceled_message))
  }
}
