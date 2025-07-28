package com.dpfht.tmdbcleanmvp.framework.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.dpfht.tmdbcleanmvp.framework.databinding.LayoutLoadingBinding

abstract class BaseFragmentNew<VDB: ViewDataBinding>(
  @LayoutRes protected val contentLayoutId: Int
): Fragment() {

  protected lateinit var binding: VDB

  protected lateinit var loadingView: View

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = DataBindingUtil.inflate(inflater, contentLayoutId, container, false)

    val tmpLoadingView = getCustomLoadingView()
    if (tmpLoadingView != null) {
      loadingView = tmpLoadingView
    } else {
      val loadingBinding = LayoutLoadingBinding.inflate(inflater, container, false)
      loadingView = loadingBinding.root
      loadingView.visibility = View.GONE
      (binding.root as ViewGroup).addView(loadingView)
    }

    return binding.root
  }

  open fun getCustomLoadingView(): View? {
    return null
  }
}
