package com.example.mykotlinmovies.screens.detailScreen

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mykotlinmovies.R
import com.example.mykotlinmovies.adapter.MovieAdapter
import com.example.mykotlinmovies.adapter.MovieAdapter.Companion.BASE_POSTER_URL
import com.example.mykotlinmovies.adapter.MovieAdapter.Companion.BIG_POSTER_SIZE
import com.example.mykotlinmovies.adapter.ReviewAdapter
import com.example.mykotlinmovies.adapter.VideoAdapter
import com.example.mykotlinmovies.database.DatabaseViewModel
import com.example.mykotlinmovies.pojo.Result
import com.example.mykotlinmovies.pojo.Review
import com.example.mykotlinmovies.pojo.Video
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), DetailListView {

    private lateinit var videoAdapter: VideoAdapter
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var reviewAdapter: ReviewAdapter

    private val BASE_YOUTUBE_URL = "https://www.youtube.com/watch?v="
    private lateinit var presenter: DetailPresenter

    private val viewModel: DatabaseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        if (!intent.hasExtra("movie")){
            finish()
        }
        val movie = intent.getParcelableExtra<Result>("movie")

        presenter = DetailPresenter(this)


        detail_title_tv.text = movie?.title
        detail_original_title_tv.text = movie?.original_title
        detail_original_language_tv.text = movie?.original_language
        detail_popularity_tv.text = movie?.popularity.toString()
        detail_vote_average_tv.text = movie?.vote_average.toString()
        detail_release_date_tv.text = movie?.release_date
        detail_overview_tv.text = movie?.overview

        Picasso.get().load(BASE_POSTER_URL + BIG_POSTER_SIZE + movie?.poster_path).into(detail_big_poster_iv)


        videoAdapter = VideoAdapter()
        recycler_videos_detail.adapter = videoAdapter
        recycler_videos_detail.layoutManager = LinearLayoutManager(this)

        movieAdapter = MovieAdapter(this)
        recycler_similar_movies.adapter = movieAdapter
        recycler_similar_movies.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        reviewAdapter = ReviewAdapter()
        recycler_reviews.adapter = reviewAdapter
        recycler_reviews.layoutManager = LinearLayoutManager(this)


        videoAdapter.setOnVideoClickListener(object: VideoAdapter.OnVideoClickListener{
            override fun onVideoClick(position: Int) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(BASE_YOUTUBE_URL + videoAdapter.videos[position].key))
                startActivity(intent)
            }
        })
        movieAdapter.setOnPosterClickListener(object: MovieAdapter.OnPosterClickListener{
            override fun onPosterClick(position: Int) {
                val intent = Intent(baseContext, DetailActivity::class.java)
                intent.putExtra("movie", movieAdapter.movies[position])
                startActivity(intent)
            }
        })

        presenter.getVideos(movie?.id!!)

    }

    override fun showSimilarMovie(similarMovies: List<Result>) {
        movieAdapter.addMovies(similarMovies)
    }

    override fun showVideo(videos: List<Video>) {
        videoAdapter.videos = videos as ArrayList<Video>
    }

    override fun showReview(reviews: List<Review>) {
        reviewAdapter.reviews = reviews as ArrayList<Review>
    }

}