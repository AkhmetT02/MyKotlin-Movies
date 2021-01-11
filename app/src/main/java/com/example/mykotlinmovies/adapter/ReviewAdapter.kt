package com.example.mykotlinmovies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mykotlinmovies.R
import com.example.mykotlinmovies.pojo.Review

class ReviewAdapter : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

    var reviews: ArrayList<Review> = ArrayList()
        get() = field
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.review_item, parent, false)
        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val review = reviews[position]
        holder.author.text = review.author
        holder.content.text = review.content
    }

    override fun getItemCount(): Int = reviews.size

    inner class ReviewViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val author: TextView = itemView.findViewById(R.id.review_author_tv_item)
        val content: TextView = itemView.findViewById(R.id.review_content_tv_item)
    }
}