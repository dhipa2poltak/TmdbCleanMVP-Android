package com.dpfht.tmdbcleanmvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.dpfht.tmdbcleanmvp.databinding.ActivityMainBinding
import com.dpfht.tmdbcleanmvp.framework.di.DaggerNavigationComponent
import com.dpfht.tmdbcleanmvp.framework.di.NavigationComponent
import com.dpfht.tmdbcleanmvp.framework.di.module.NavigationModule
import com.dpfht.tmdbcleanmvp.framework.di.provider.NavigationComponentProvider
import com.dpfht.tmdbcleanmvp.navigation.NavigationServiceImpl

class MainActivity : AppCompatActivity(), NavigationComponentProvider {

  private lateinit var binding: ActivityMainBinding
  private lateinit var navController: NavController

  private lateinit var navigationComponent: NavigationComponent

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val navHostFragment =
      supportFragmentManager.findFragmentById(R.id.demo_nav_host_fragment) as NavHostFragment
    navController = navHostFragment.navController

    /*
    val appBarConfiguration = AppBarConfiguration(
      setOf(navigationR.id.productListFragment)
    )
    */

    navigationComponent = DaggerNavigationComponent
      .builder()
      .navigationModule(NavigationModule(NavigationServiceImpl(this, navController)))
      .build()

    NavigationUI.setupActionBarWithNavController(this, navController)
    //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
  }

  override fun onSupportNavigateUp(): Boolean {
    return navController.navigateUp() || super.onSupportNavigateUp()
  }

  override fun provideNavigationComponent(): NavigationComponent {
    return navigationComponent
  }
}
