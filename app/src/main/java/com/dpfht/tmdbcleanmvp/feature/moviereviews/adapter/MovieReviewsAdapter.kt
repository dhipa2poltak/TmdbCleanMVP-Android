package com.dpfht.tmdbcleanmvp.feature.moviereviews.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dpfht.tmdbcleanmvp.R
import com.dpfht.tmdbcleanmvp.databinding.RowReviewBinding
import com.dpfht.tmdbcleanmvp.domain.entity.ReviewEntity

class MovieReviewsAdapter(private val reviews: ArrayList<ReviewEntity>): RecyclerView.Adapter<MovieReviewsAdapter.ReviewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewHolder {
        val binding = RowReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ReviewHolder(binding)
    }

    override fun getItemCount(): Int {
        return reviews.size
    }

    override fun onBindViewHolder(holder: ReviewHolder, position: Int) {
        holder.bindData(reviews[position])
    }

    class ReviewHolder(private val binding: RowReviewBinding): RecyclerView.ViewHolder(binding.root) {

        fun bindData(review: ReviewEntity) {
            binding.tvAuthor.text = review.author
            binding.tvContent.text = review.content

            var imageUrl = review.authorDetails?.avatarPath
            if (imageUrl?.startsWith("/") == true) {
                imageUrl = imageUrl.replaceFirst("/", "")
            }
            if (!imageUrl.isNullOrEmpty()) {
                Glide.with(binding.ivAuthor.context)
                    .load(imageUrl)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(binding.ivAuthor)
            }
        }
    }
}
