package com.dpfht.tmdbcleanmvp.framework.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetDialogFragment<VDB: ViewDataBinding>(
  @LayoutRes protected val contentLayoutId: Int
): BottomSheetDialogFragment() {

  protected lateinit var binding: VDB

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = DataBindingUtil.inflate(inflater, contentLayoutId, container, false)

    return binding.root
  }
}
