package com.dpfht.tmdbcleanmvp.feature.base

interface BaseView {

  fun showLoadingDialog()
  fun hideLoadingDialog()
  fun showErrorMessage(message: String)
  fun showCanceledMessage()
}
