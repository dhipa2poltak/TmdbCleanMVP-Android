package com.dpfht.tmdbcleanmvp.feature_movies_by_genre.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dpfht.tmdbcleanmvp.domain.model.Movie
import com.dpfht.tmdbcleanmvp.feature_movies_by_genre.databinding.RowMovieBinding
import com.dpfht.tmdbcleanmvp.framework.R as FrameworkR

class MoviesByGenreAdapter(private val movies: ArrayList<Movie>): RecyclerView.Adapter<MoviesByGenreAdapter.MovieByGenreHolder>() {

    var onClickMovieListener: OnClickMovieListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieByGenreHolder {
        val binding = RowMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MovieByGenreHolder(binding)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieByGenreHolder, position: Int) {
        holder.bindData(movies[position])
        holder.itemView.setOnClickListener {
            onClickMovieListener?.onClickMovie(position)
        }
    }

    class MovieByGenreHolder(private val binding: RowMovieBinding): RecyclerView.ViewHolder(binding.root) {

        fun bindData(movie: Movie) {
            binding.tvTitleMovie.text = movie.title
            binding.tvOverviewMovie.text = movie.overview

            if (movie.imageUrl.trim().isNotEmpty()) {
                Glide.with(binding.root.context)
                    .load(movie.imageUrl)
                    .placeholder(FrameworkR.drawable.loading)
                    .into(binding.ivMovie)
            }
        }
    }

    interface OnClickMovieListener {
        fun onClickMovie(position: Int)
    }
}
