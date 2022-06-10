package com.dpfht.tmdbcleanmvp.framework.di

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.dpfht.tmdbcleanmvp.R
import dagger.Module
import dagger.Provides

@Module
class FragmentModule {

  @Provides
  @FragmentScope
  fun provideLoadingDialog(@ActivityContext context: Context): AlertDialog {
    return AlertDialog.Builder(context)
      .setCancelable(false)
      .setView(R.layout.dialog_loading)
      .create()
  }
}