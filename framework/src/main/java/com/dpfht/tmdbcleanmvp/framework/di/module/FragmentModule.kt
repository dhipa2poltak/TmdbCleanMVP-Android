package com.dpfht.tmdbcleanmvp.framework.di.module

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.dpfht.tmdbcleanmvp.framework.R
import com.dpfht.tmdbcleanmvp.framework.di.ActivityContext
import com.dpfht.tmdbcleanmvp.framework.di.FragmentScope
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
