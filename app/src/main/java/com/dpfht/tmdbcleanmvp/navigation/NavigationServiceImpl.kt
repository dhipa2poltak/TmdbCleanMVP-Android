package com.dpfht.tmdbcleanmvp.navigation

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import com.dpfht.tmdbcleanmvp.R
import com.dpfht.tmdbcleanmvp.feature_movie_trailer.MovieTrailerActivity
import com.dpfht.tmdbcleanmvp.framework.navigation.NavigationService

class NavigationServiceImpl(
  private val activity: AppCompatActivity,
  private val navController: NavController
): NavigationService {

  override fun navigateToGender() {
    val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
    navGraph.setStartDestination(R.id.genreFragment)

    navController.graph = navGraph
  }

  override fun navigateToMoviesByGender(genreId: Int, genreName: String) {
    val builder = Uri.Builder()
    builder.scheme("android-app")
      .authority("tmdbcleanmvp.dpfht.com")
      .appendPath("movies_by_genre_fragment")
      .appendQueryParameter("genreId", "$genreId")
      .appendQueryParameter("genreName", genreName)

    navController.navigate(NavDeepLinkRequest.Builder
      .fromUri(builder.build())
      .build())
  }

  override fun navigateToMovieDetails(movieId: Int) {
    val builder = Uri.Builder()
    builder.scheme("android-app")
      .authority("tmdbcleanmvp.dpfht.com")
      .appendPath("movie_details_fragment")
      .appendQueryParameter("movieId", "$movieId")

    navController.navigate(NavDeepLinkRequest.Builder
      .fromUri(builder.build())
      .build())
  }

  override fun navigateToMovieReviews(movieId: Int, movieTitle: String) {
    val builder = Uri.Builder()
    builder.scheme("android-app")
      .authority("tmdbcleanmvp.dpfht.com")
      .appendPath("movie_reviews_fragment")
      .appendQueryParameter("movieId", "$movieId")
      .appendQueryParameter("movieTitle", movieTitle)

    navController.navigate(NavDeepLinkRequest.Builder
      .fromUri(builder.build())
      .build())
  }

  override fun navigateToMovieTrailer(movieId: Int) {
    val itn = Intent(activity, MovieTrailerActivity::class.java)
    itn.putExtra("movie_id", movieId)
    activity.startActivity(itn)
  }

  override fun navigateToErrorMessage(message: String) {
    val builder = Uri.Builder()
    builder.scheme("android-app")
      .authority("tmdbcleanmvp.dpfht.com")
      .appendPath("error_message_dialog_fragment")
      .appendQueryParameter("message", message)

    navController.navigate(NavDeepLinkRequest.Builder
      .fromUri(builder.build())
      .build())
  }
}
