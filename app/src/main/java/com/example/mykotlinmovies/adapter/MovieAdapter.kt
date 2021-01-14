package com.example.mykotlinmovies.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.mykotlinmovies.screens.mainScreen.MainActivity
import com.example.mykotlinmovies.R
import com.example.mykotlinmovies.pojo.Result
import com.example.mykotlinmovies.screens.favouriteScreen.FavouriteMoviesActivity
import com.squareup.picasso.Picasso

class MovieAdapter(private val activity: Activity) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    companion object {
        val BASE_POSTER_URL = "https://image.tmdb.org/t/p/"
        val POSTER_SIZE = "w185/"
        val POSTER_SIZE_FOR_SIMILAR = "w342/"
        val BIG_POSTER_SIZE = "w780/"
    }
    private var onReachEndListener: OnReachEndListener? = null
    private lateinit var onPosterClickListener: OnPosterClickListener

    var movies: ArrayList<Result> = ArrayList()
        get() = field
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    fun addMovies(m: List<Result>){
        movies.addAll(m)
        notifyDataSetChanged()
    }


    @JvmName("setOnReachEndListener1")
    fun setOnReachEndListener(onReachEndListener: OnReachEndListener){
        this.onReachEndListener = onReachEndListener
    }
    @JvmName("setOnPosterClickListener1")
    fun setOnPosterClickListener(onPosterClickListener: OnPosterClickListener){
        this.onPosterClickListener = onPosterClickListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.movie_item,
            parent,
            false
        )
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        if (activity is MainActivity) {
            if (position >= movies.size - 4 && movies.size % 20 == 0 && onReachEndListener != null) {
                onReachEndListener?.onReachEnd()
            }
        }

        val movie: Result = movies[position]
        if (activity is MainActivity || activity is FavouriteMoviesActivity) {
            Picasso.get().load(BASE_POSTER_URL + POSTER_SIZE + movie.poster_path)
                .into(holder.poster)
        } else{
            Picasso.get().load(BASE_POSTER_URL + POSTER_SIZE_FOR_SIMILAR + movie.poster_path).into(holder.poster)
            holder.poster.layoutParams = holder.poster.layoutParams.apply {
                width = RelativeLayout.LayoutParams.WRAP_CONTENT
                height = RelativeLayout.LayoutParams.WRAP_CONTENT
            }
        }
    }

    override fun getItemCount(): Int = movies.size

    interface OnReachEndListener{
        fun onReachEnd()
    }
    interface OnPosterClickListener{
        fun onPosterClick(position: Int)
    }

    inner class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val poster: ImageView = itemView.findViewById(R.id.poster_item)

        init {
            itemView.setOnClickListener {
                if (onPosterClickListener != null){
                    onPosterClickListener.onPosterClick(adapterPosition)
                }
            }
        }
    }
}