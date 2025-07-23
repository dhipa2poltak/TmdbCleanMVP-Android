package com.dpfht.tmdbcleanmvp.framework.base

interface BaseView {

  fun showLoadingDialog()
  fun hideLoadingDialog()
  fun showErrorMessage(message: String)
  fun showCanceledMessage()
}
