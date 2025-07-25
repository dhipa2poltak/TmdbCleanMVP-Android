package com.dpfht.tmdbcleanmvp.feature_movie_trailer

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dpfht.tmdbcleanmvp.feature_movie_trailer.databinding.ActivityMovieTrailerBinding
import com.dpfht.tmdbcleanmvp.feature_movie_trailer.di.DaggerMovieTrailerComponent
import com.dpfht.tmdbcleanmvp.feature_movie_trailer.di.MovieTrailerModule
import com.dpfht.tmdbcleanmvp.framework.di.provider.ApplicationComponentProvider
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import javax.inject.Inject
import com.dpfht.tmdbcleanmvp.framework.R as FrameworkR

class MovieTrailerActivity : AppCompatActivity(), MovieTrailerContract.MovieTrailerView {

  private lateinit var binding: ActivityMovieTrailerBinding

  @Inject
  lateinit var presenter: MovieTrailerContract.MovieTrailerPresenter

  private lateinit var youTubePlayer: YouTubePlayer

  override fun onCreate(savedInstanceState: Bundle?) {
    val movieTrailerComponent = DaggerMovieTrailerComponent
      .builder()
      .movieTrailerModule(MovieTrailerModule(this))
      .applicationComponent((this.application as ApplicationComponentProvider).provideApplicationComponent())
      .build()

    movieTrailerComponent.inject(this)

    super.onCreate(savedInstanceState)
    binding = ActivityMovieTrailerBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.youtubePlayerView.enableAutomaticInitialization = false
    lifecycle.addObserver(binding.youtubePlayerView)

    if (intent.hasExtra("movie_id")) {
      val movieId = intent.getIntExtra("movie_id", -1)

      presenter.setMovieId(movieId)
      presenter.start()
    }
  }

  override fun showTrailer(keyVideo: String) {
    val iFramePlayerOptions = IFramePlayerOptions.Builder()
      .controls(1)
      .build()

    binding.youtubePlayerView.initialize(object : AbstractYouTubePlayerListener() {
      override fun onReady(youTubePlayer: YouTubePlayer) {
        super.onReady(youTubePlayer)

        this@MovieTrailerActivity.youTubePlayer = youTubePlayer
        youTubePlayer.loadVideo(keyVideo, 0f)
      }
    }, iFramePlayerOptions)
  }

  /*
  override fun showTrailer(keyVideo: String) {
    binding.playerYoutube.initialize(
      PlayerConfig.API_KEY,
      object : YouTubePlayer.OnInitializedListener {

        override fun onInitializationSuccess(
          p0: YouTubePlayer.Provider?,
          youtubePlayer: YouTubePlayer?,
          p2: Boolean
        ) {
          youtubePlayer?.loadVideo(keyVideo)
          youtubePlayer?.play()
        }

        override fun onInitializationFailure(
          p0: YouTubePlayer.Provider?,
          p1: YouTubeInitializationResult?
        ) {
          Toast.makeText(
            baseContext,
            "error loading youtube video",
            Toast.LENGTH_SHORT
          ).show()
        }
      })
  }
  */

  override fun showErrorMessage(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
  }

  override fun showCanceledMessage() {
    showErrorMessage(getString(FrameworkR.string.canceled_message))
  }

  override fun onResume() {
    super.onResume()
    supportActionBar?.hide()
  }

  override fun onPause() {
    super.onPause()
    supportActionBar?.show()
  }

  override fun onDestroy() {
    presenter.onDestroy()
    super.onDestroy()
  }
}
