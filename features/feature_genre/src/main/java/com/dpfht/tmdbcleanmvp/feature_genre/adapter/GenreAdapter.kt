package com.dpfht.tmdbcleanmvp.feature_genre.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dpfht.tmdbcleanmvp.domain.model.Genre
import com.dpfht.tmdbcleanmvp.feature_genre.databinding.RowGenreBinding

class GenreAdapter(private val genres: ArrayList<Genre>): RecyclerView.Adapter<GenreAdapter.GenreHolder>() {

    var onClickGenreListener: OnClickGenreListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreHolder {
        val binding = RowGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return GenreHolder(binding)
    }

    override fun getItemCount(): Int {
        return genres.size
    }

    override fun onBindViewHolder(holder: GenreHolder, position: Int) {
        holder.bindData(genres[position])
        holder.itemView.setOnClickListener {
            onClickGenreListener?.onClickGenre(position)
        }
    }

    class GenreHolder(private val binding: RowGenreBinding): RecyclerView.ViewHolder(binding.root) {

        fun bindData(genre: Genre) {
            binding.tvGenre.text = genre.name
        }
    }

    interface OnClickGenreListener {
        fun onClickGenre(position: Int)
    }
}
